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

		// equals method to check equality
		public boolean equals(Object x) {
			boolean eq = false;
			Entry that;
			if (x instanceof Entry) {
				that = (Entry) x;
				eq = (this.column == that.column && this.value == that.value);
			}
			return eq;
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
		for (int x = 0; x < input; x++) {
			row[x] = new List();
		}
		this.nnz = 0;
	}

	// toString function
	// prints out the content of the matrix
	public String toString() {
		String output = "";
		for (int x = 0; x < size; x++) {
			// move the cursor to the front of the list
			row[x].moveFront();
			// check for an empty list
			if (row[x].length() == 0) {
				continue; // if row is empty, break, since we don't care about non zero entries
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

	// checks for equality of two matrices
	public boolean equals(Object x) {
		return true;
	}

	// MANIPULATION PROCEDURES
	// changes the entry at the i-th row, j-th column of the matrix
	void changeEntry(int i, int j, double x) {
		// first check if the entry is in the matrix
		if (i > this.size || j > this.size || i < 1 || j < 1) {
			throw new RuntimeException("ENTRY ATTEMPTED TO BEING CHANGED IS NOT IN THE MATRIX");
		}
		// navigate to proper position within the ith list
		// so we end up on the jth position
		row[i].moveFront();

		// First, we check for empty list, if its empty, just make a new entry
		if (row[i].length() == 0) {
			// System.out.println("wheee");
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
			// System.out.println("cursor at: " + row[i].index());
			row[i].moveNext();
		}

		// if the column exists, just change the value of that entry
		if (row[i].index() != -1 && ((Entry) row[i].get()).column == j) {
			// check if you are trying to change the value to zero
			if (x == 0) {
				System.out.println("cursor at: " + row[i].index());
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
			return out;
		}
		double newVal;
		int newCol;
		for (int n = 0; n < size; n++) {
			// first check if there is anything in that row
			if (row[n].length() == 0) {
				continue;
			}
			// if stuff exists in that row, continue down the list
			row[n].moveFront();
			while (row[n].index() >= 0) {
				newCol = ((Entry) row[n].get()).column;
				newVal = ((Entry) row[n].get()).value*x;
				//((Entry) row[n].get()).value *= x;
				out.row[n].append(new Entry(newCol, newVal));
				row[n].moveNext();
			}
		}
		out.nnz = this.nnz;
		return out;
	}

	// adds two matrices together
	Matrix add(Matrix M) {
		Matrix out = new Matrix(this.size);
		if (M.size != this.size) {
			throw new RuntimeException("MATRICES MUST BE OF EQUAL SIZE");
		}
		// for loop, go line by line
		for (int x = 0; x < this.size; x++) {
			// if a row is empty in both matrices, skip that row
			if (row[x].length() == 0 && M.row[x].length() == 0) {
				continue;
			}
			row[x].moveFront();
			M.row[x].moveFront();
			// comparators for keeping track of columns
			int compare1;
			int compare2;
			//System.out.println("ROW:" + x + " indices " + row[x].index() + " " + M.row[x].index());
			//System.out.println(row[x].index() != -1 && M.row[x].index() != -1);
			// traverse forward in each list until end is reached in BOTH
			while (row[x].index() != -1 || M.row[x].index() != -1) {
				//System.out.println("ENTER " + x);
				compare1 = -1;
				compare2 = -1;
				
				if (row[x].index() != -1) {
					compare1 = ((Entry) row[x].get()).column;
				}
				if (M.row[x].index() != -1) {
					compare2 = ((Entry) M.row[x].get()).column;
				}
				
				//System.out.println("whee " + compare1 + " " + compare2);
				
				//LIST ONLY EXISTS IN ONE ROW
				//Matrix M lacks a row, append with this.Matrix entry
				if(row[x].index() != -1 && M.row[x].index() == -1)
				{
					out.row[x].append(new Entry(compare1, ((Entry) row[x].get()).value));
					row[x].moveNext();
					out.nnz++;
					continue;
				}
				//this.Matrix lacks a row, append with M.Matrix entry
				if(row[x].index() == -1 && M.row[x].index() != -1)
				{
					out.row[x].append(new Entry(compare2, ((Entry) M.row[x].get()).value));
					M.row[x].moveNext();
					out.nnz++;
					continue;
				}
				//LIST EXISTS IN BOTH ROWS
				// case 1: Entry only exists in first matrix (at certain position)
				if (compare1 < compare2) {
					out.row[x].append(new Entry(compare1, ((Entry) row[x].get()).value));
					row[x].moveNext();
					out.nnz++;
					continue;
				}
				// case 2: Entry only exists in second matrix
				else if (compare1 > compare2) {
					out.row[x].append(new Entry(compare2, ((Entry) M.row[x].get()).value));
					M.row[x].moveNext();
					out.nnz++;
					continue;
				}
				// case 3: Entry exists in both matrices
				else {
					// if the end result happens to be zero, we ignore it
					if (((Entry) row[x].get()).value + ((Entry) M.row[x].get()).value == 0.0) {
						row[x].moveNext();
						M.row[x].moveNext();
						continue;
					}
					// append with the combination of both entries
					out.row[x].append(new Entry(compare1, ((Entry) row[x].get()).value + ((Entry) M.row[x].get()).value));
					row[x].moveNext();
					M.row[x].moveNext();
					out.nnz++;
					continue;
				}
			}
		}
		return out;
	}
}
