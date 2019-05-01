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
		if (i > this.size || j > this.size) {
			throw new RuntimeException("ENTRY ATTEMPTED TO BEING CHANGED IS NOT IN THE MATRIX");
		}
		// we don't care about zero entries
		if (x == 0.0) {
			return;
		}
		// navigate to proper position within the ith list
		// so we end up on the jth position
		row[i].moveFront();

		// First, we check for empty list, if its empty, just make a new entry
		if (row[i].length() == 0) {
			row[i].prepend(new Entry(j, x));
			return;
		}

		// if the row does exist, we must do other stuff
		// proceed down list until we reach the right column
		while (((Entry) row[i].get()).column < j) {
			row[i].moveNext();
		}

		// then check if you are on a valid spot
		if (row[i].index() >= 0) {
			row[i].insertBefore(new Entry(j, x));
			return;
		}
		// if index is -1, then you must be off the edge of the list
		else {
			row[i].append(new Entry(j, x));
			return;
		}
	}
}
