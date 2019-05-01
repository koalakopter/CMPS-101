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
		
		//toString method to print out data
		public String toString()
		{
			String output = " value: " + this.value + " column: " + this.column; 
			return output;
		}
		//equals method to check equality
		public boolean equals(Object n)
		{
			return false;
		}
	}
	
	//matrix data
	private List[] row; //array of Lists "List of Lists"
	private int size;
	private int nnz; //keeps track of non zero entries
	
	//constructor
	Matrix(int input)
	{
		if(input <= 0)
		{
			throw new RuntimeException("SIZE OF MATRIX MUST BE LARGER THAN ONE");
		}
		//set up the outer list with size input+1
		row = new List[input+1];
		this.size = input;
		//set up inner lists
		for(int x = 0; x < input; x++)
		{
			row[x] = new List();
		}
	}
	//toString function
	//prints out the content of the matrix
	public String toString()
	{
		String output = "";
		for(int x = 0; x < size; x++)
		{
			//move the cursor to the front of the list
			row[x].moveFront(); 
			for(int y = 0; y < size; y++)
			{
				//check for an empty list
				if(row[x].length() == 0)
				{
					output = output + " 0";
					continue;
				}
				if(row[x].get() == null)
				{
					output = output + " 0"; //if nothing is there, its zero
				}
				else {
					output = output + " " + row[x].get();
				}
				row[x].moveNext();
			}
			output = output + "\n";
		}
		return output;
	}
	
	//ACCESS FUNCTIONS
	//returns the size of the matrix (aka how many rows/cols)
	int getSize()
	{
		int size = 0;
		//traverse outer list to the end
		size = row.length;
		return size;
	}
	
	//returns number of non zero entries
	int getNNZ()
	{
		return this.nnz;
	}
	
	//MANIPULATION PROCEDURES
	//changes the entry at the i-th row, j-th column of the matrix
	void changeEntry(int i, int j, double x)
	{
		//first check if the entry is in the matrix
		if(i > this.size || j > this.size)
		{
			throw new RuntimeException("ENTRY ATTEMPTED TO BEING CHANGED IS NOT IN THE MATRIX");
		}
		//navigate to proper position within the ith list
		//so we end up on the jth position
		row[i].moveFront();
		for(int n = 0; n < j-1; j++)
		{
			row[i].moveNext();
		}
		//SEVERAL CASES
	
	}
}
