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
	
	//constructor
	Matrix(int input)
	{
		if(input <= 0)
		{
			throw new RuntimeException("SIZE OF MATRIX MUST BE LARGER THAN ONE");
		}
		//set up the outer list with size input+1
		row = new List[input+1];
		//set up inner lists
		for(int x = 0; x < input; x++)
		{
			row[x] = new List();
		}
	}
}
