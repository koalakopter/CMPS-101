/* JULIAN TO (jcto)
 * PA 2
 * CS 101
 * PROFESSOR TANTALO
 * SPRING 2019
 */

public class Matrix {
	// data within the Matrix
	// the private inner parts of the Matrix
	// represents the Lists that the outer List contains
	private class Entry {

		double value;
		int column;

		Entry(int col, double val) {
			this.value = val;
			this.column = col;
		}

		// toString method to print out data
		public String toString() {
			String output = "(" + this.column + ", " + this.value + ")";
			return output;
		}

		// equals method to check equality (do I need this???)
		public boolean equals(Object x) {
			if (((Entry) x).column == this.column && ((Entry) x).value == this.value) {
				return true;
			}
			return false;
		}
	}

	// matrix data
	private List[] row; // array of Lists "List of Lists"
	private int size;
	private int nnz; // keeps track of non zero entries

	// constructor
	Matrix(int input) {
		if (input <= 0) {
			throw new RuntimeException("SIZE OF MATRIX MUST BE LARGER THAN ONE");
		}
		// set up the outer list with size input+1
		row = new List[input + 1];
		this.size = input;
		// set up inner lists
		for (int x = 1; x <= input; x++) {
			row[x] = new List();
		}
		this.nnz = 0;
	}

	// toString function
	// prints out the content of the matrix
	public String toString() {
		String output = "";
		for (int x = 1; x <= size; x++) {
			// move the cursor to the front of the list
			row[x].moveFront();
			// check for an empty list
			if (row[x].length() == 0) {
				continue; // if row is empty, continue, since we don't care about non zero entries
			}
			output = output + x + ": "; // row header
			output = output + row[x] + "\n";

		}
		return output;

	}

	// ACCESS FUNCTIONS
	// returns the size of the matrix (aka how many rows/cols)
	int getSize() {
		int size = 0;
		// traverse outer list to the end
		size = row.length;
		return size;
	}

	// returns number of non zero entries
	int getNNZ() {
		return this.nnz;
	}

	// checks for equality of two matrices (just in case I need it later maybe)
	public boolean equals(Object x) {
		Matrix compare = (Matrix) x;
		if (this.size != ((Matrix) x).size) {
			return false;
		}
		for (int i = 1; i <= this.size; i++) {
			if (this.row[i].equals(compare.row[i]) != true) {
				//System.out.println(i);
				return false;
			}
		}
		return true;
	}

	// MANIPULATION PROCEDURES
	// makes the matrix full of zeroes
	void makeZero() {
		// delete every entry in each list
		for (int x = 1; x < this.size; x++) {
			while (row[x].length() >= 1) {
				row[x].deleteFront();
			}
		}
		this.nnz = 0;
	}

	// copies this matrix into a new Matrix
	Matrix copy() {
		Matrix out = new Matrix(this.size);
		for (int x = 1; x <= this.size; x++) {
			row[x].moveFront();
			while (row[x].index() > -1) {
				// append the copied list with each entry from the original Matrix
				out.row[x].append(new Entry(((Entry) row[x].get()).column, ((Entry) row[x].get()).value));
				row[x].moveNext();
			}
		}
		// copy the paramters too
		out.size = this.size;
		out.nnz = this.nnz;
		return out;
	}

	// changes the entry at the i-th row, j-th column of the matrix
	void changeEntry(int i, int j, double x) {
		// first check if the entry is in the matrix
		if (i > this.size || j > this.size || i < 1 || j < 1) {
			throw new RuntimeException("ENTRY ATTEMPTED TO BEING CHANGED IS NOT IN THE MATRIX");
		}
		// navigate to proper position within the ith list
		// so we end up on the jth position
		row[i].moveFront();

		// First, we check for empty list, if its empty, just make a new entry (unless
		// its zero)
		if (row[i].length() == 0) {
			if (x == 0) {
				return;
			}
			row[i].prepend(new Entry(j, x));
			this.nnz++;
			return;
		}

		if (row[i].index() == -1) {
			row[i].append(new Entry(j, x));
			this.nnz++;
			return;
		}
		// if the row does exist, we must do other stuff
		// proceed down list until we reach the right column
		while (row[i].index() > -1 && ((Entry) row[i].get()).column < j) {
			row[i].moveNext();
		}

		// if the column exists, just change the value of that entry
		if (row[i].index() != -1 && ((Entry) row[i].get()).column == j) {
			// check if you are trying to change the value to zero
			if (x == 0) {
				row[i].delete();
				this.nnz--;
				return;
			}
			((Entry) row[i].get()).value = x;
			this.nnz++;
			return;
		}
		// ignore the rest if you try to change an empty spot to zero
		if (x == 0) {
			return;
		}
		// then check if you are on a valid spot
		if (row[i].index() >= 0) {
			row[i].insertBefore(new Entry(j, x));
			this.nnz++;
			return;
		}
		// if index is -1, then you must be off the edge of the list
		else {
			row[i].append(new Entry(j, x));
			this.nnz++;
			return;
		}
	}

	// multiplies a matrix by a scalar, returns a matrix
	Matrix scalarMult(double x) {
		Matrix out = new Matrix(this.size);
		// BUT: IF X = 0, return a blank, sad matrix
		if (x == 0.0) {
			this.makeZero();
			return out;
		}
		double newVal;
		int newCol;
		for (int n = 1; n <= size; n++) {
			// first check if there is anything in that row
			if (row[n].length() == 0) {
				continue;
			}
			// if stuff exists in that row, continue down the list
			row[n].moveFront();
			while (row[n].index() >= 0) {
				newCol = ((Entry) row[n].get()).column;
				newVal = ((Entry) row[n].get()).value * x;
				// ((Entry) row[n].get()).value *= x;
				out.row[n].append(new Entry(newCol, newVal));
				row[n].moveNext();
			}
		}
		// can never multiply a*b = 0 if b,a != 0
		// thus nnz is always the same
		out.nnz = this.nnz;
		return out;
	}

	// adds two matrices together
	Matrix add(Matrix M) {
		Matrix out = new Matrix(this.size);
		// temp Matrix
		Matrix temp = M.copy();
		if (temp.size != this.size) {
			throw new RuntimeException("MATRICES MUST BE OF EQUAL SIZE");
		}
		// for loop, go line by line
		for (int x = 1; x <= this.size; x++) {
			// if a row is empty in both matrices, skip that row
			if (row[x].length() == 0 && temp.row[x].length() == 0) {
				continue;
			}
			row[x].moveFront();
			temp.row[x].moveFront();
			// comparators for keeping track of columns
			int compare1;
			int compare2;
			// traverse forward in each list until end is reached in BOTH
			while (row[x].index() != -1 || temp.row[x].index() != -1) {
				compare1 = -1;
				compare2 = -1;

				if (row[x].index() != -1) {
					compare1 = ((Entry) row[x].get()).column;
				}
				if (temp.row[x].index() != -1) {
					compare2 = ((Entry) temp.row[x].get()).column;
				}

				// LIST ONLY EXISTS IN ONE ROW
				// Matrix M lacks a row, append with this.Matrix entry
				if (row[x].index() != -1 && temp.row[x].index() == -1) {
					out.row[x].append(new Entry(compare1, ((Entry) row[x].get()).value));
					row[x].moveNext();
					out.nnz++;
					continue;
				}

				// this.Matrix lacks a row, append with M.Matrix entry
				if (row[x].index() == -1 && temp.row[x].index() != -1) {
					out.row[x].append(new Entry(compare2, ((Entry) temp.row[x].get()).value));
					temp.row[x].moveNext();
					out.nnz++;
					continue;
				}
				// System.out.println("compare " + compare1 + " " + compare2);
				// LIST EXISTS IN BOTH ROWS
				// case 1: Entry only exists in first matrix (at certain position)
				if (compare1 < compare2) {
					out.row[x].append(new Entry(compare1, ((Entry) row[x].get()).value));
					row[x].moveNext();
					out.nnz++;
					continue;
				}
				// case 2: Entry only exists in second matrix
				else if (compare1 > compare2) {
					out.row[x].append(new Entry(compare2, ((Entry) temp.row[x].get()).value));
					temp.row[x].moveNext();
					out.nnz++;
					continue;
				}
				// case 3: Entry exists in both matrices
				else {
					// if the end result happens to be zero, we ignore it
					double koala = ((Entry) row[x].get()).value + ((Entry) temp.row[x].get()).value;
					if (koala == 0.0) {
						row[x].moveNext();
						temp.row[x].moveNext();
						continue;
					}
					// System.out.println("values of more fun: " + koala);
					// append with the combination of both entries
					out.row[x].append(new Entry(compare1, koala));
					row[x].moveNext();
					temp.row[x].moveNext();
					out.nnz++;
					continue;
				}
			}
		}
		return out;
	}

	// same thing as add, but do scalarMult M by -1
	Matrix sub(Matrix M) {
		if (M.size != this.size) {
			throw new RuntimeException("MATRICES MUST BE OF EQUAL SIZE");
		}
		Matrix out = new Matrix(this.size);
		Matrix temp = M.copy();
		temp = (temp.scalarMult(-1));
		out = this.add(temp);
		return out;
	}

	// returns the transpose of the matrix, which is the matrix with its row/cols
	// swapped
	Matrix transpose() {
		Matrix out = new Matrix(this.size);
		// like the copy function, but rows and columns are swapped
		for (int x = 1; x <= this.size; x++) {
			row[x].moveFront();
			while (row[x].index() > -1) {
				// append the copied list with each entry from the original Matrix
				//out.row[((Entry) row[x].get()).column].append(new Entry(x, ((Entry) row[x].get()).value));
				out.changeEntry(((Entry)row[x].get()).column, x, ((Entry) row[x].get()).value);
				row[x].moveNext();
			}
		}
		out.nnz = this.nnz;
		return out;
	}

	// multiplies two matrices together
	Matrix mult(Matrix M) {
		// input matrices must be of the same size
		if (M.size != this.size) {
			throw new RuntimeException("MATRICES MUST BE OF EQUAL SIZE");
		}
		Matrix out = new Matrix(this.size);
		// take the transpose of Matrix M to simplify the calculations
		M = M.transpose();
		// since the 2nd matrix has been transposed
		// we can just take the dot product of each row combination
		// ROW
		double dotProd;
		for (int x = 1; x <= this.size; x++) {
			// COLUMN
			for (int y = 1; y <= this.size; y++) {
				dotProd = dot(row[x], M.row[y]);
				if (dotProd != 0) {
					out.row[x].append(new Entry(y, dotProd));
					out.nnz++;
				}
			}
		}
		return out;
	}

	// returns the dot product of 2 vectors (or lists in this case)
	private static double dot(List P, List Q) {
		double result = 0;
		int colP, colQ;
		// dot product of a zero vector + any other vector is 0
		if (P.length() == 0 || Q.length() == 0) {
			return 0;
		}
		// function here is pretty similar to add, where we "walk" up both lists,
		// looking for matching columns
		P.moveFront();
		Q.moveFront();
		// if cursor reaches the end of either list, that must mean there are no more
		// matching columns
		while (P.index() != -1 && Q.index() != -1) {
			colP = ((Entry) P.get()).column;
			colQ = ((Entry) Q.get()).column;
			if (colP < colQ) {
				P.moveNext();
			} else if (colP > colQ) {
				Q.moveNext();
			}
			// if columns are equal, multiply the values and add to the running sum
			else {
				result = result + ((Entry) P.get()).value * ((Entry) Q.get()).value;
				P.moveNext();
				Q.moveNext();
			}
		}
		return result;
	}
}
