
/* JULIAN TO (jcto)
 * PA 3
 * CS 101
 * PROFESSOR TANTALO
 * SPRING 2019
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sparse {
	//returns a tokenized string split around white spaces
	public static String[] tokenize(String s)
	{
		String[] output = new String[3];
		output = s.split("\\s");
		return output;
	}
	//main function
	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.err.println("ERROR: BAD INPUT");
			System.exit(1);
		}

		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		// begin reading the files!
		
		//read the first line to establish parameters
		String first = in.nextLine();
		
		//tokenized string for easy reading
		String[] read = new String[3]; 
		read = tokenize(first);
		
		//convert tokens to ints
		int size = Integer.parseInt(read[0]);
		//number of nonzero lines
		int aNNZ = Integer.parseInt(read[1]);
		int bNNZ = Integer.parseInt(read[2]);
		
		//make new Matrices
		Matrix A = new Matrix(size);
		Matrix B = new Matrix(size);
		
		//loop to create Matrix A
		in.nextLine(); //skip the blank space
		int row, col;
		double val;
		//make matrix A
		for(int x = 1; x <= aNNZ; x++)
		{
			read = tokenize(in.nextLine());
			//parse the values
			row = Integer.parseInt(read[0]);
			col = Integer.parseInt(read[1]);
			val = Double.parseDouble(read[2]);
			A.changeEntry(row, col, val);
		}
		in.nextLine();
		//make Matrix B
		for(int x = 1; x <= bNNZ; x++)
		{
			read = tokenize(in.nextLine());
			//parse the values
			row = Integer.parseInt(read[0]);
			col = Integer.parseInt(read[1]);
			val = Double.parseDouble(read[2]);
			B.changeEntry(row, col, val);
		}
		System.out.println(B);
		
		// close the files
		in.close();
		out.close();
	}
}
