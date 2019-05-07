
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
		read = first.split("\\s");
		
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
			read = in.nextLine().split("\\s");
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
			read = in.nextLine().split("\\s");
			//parse the values
			row = Integer.parseInt(read[0]);
			col = Integer.parseInt(read[1]);
			val = Double.parseDouble(read[2]);
			B.changeEntry(row, col, val);
		}
		
		//the part where the calculations are done
		//print out A and B
		out.println("A has " + aNNZ + " non-zero entries:");
		out.println(A);
		
		out.println("B has " + bNNZ + " non-zero entries:");
		out.println(B);
		
		//1.5*A
		out.println("(1.5)*A =");
		Matrix C = A.copy();
		C = A.scalarMult(1.5);
		out.println(C);
		
		//A+B
		out.println("A+B =");
		C.makeZero();
		C = A.add(B);
		out.println(C);
		
		//A+A
		out.println("A+A =");
		C.makeZero();
		C = A.add(A);
		out.println(C);
		
		//B-A
		out.println("B-A =");
		C.makeZero();
		C = B.sub(A);
		out.println(C);
		
		//A-A
		out.println("A-A =");
		C.makeZero();
		C = A.sub(A);
		out.println(C);
		
		//Transpose A
		out.println("Transpose(A) =");
		C.makeZero();
		C = A.transpose();
		out.println(C);
		
		//A*B
		out.println("A*B =");
		C.makeZero();
		C = A.mult(B);
		out.println(C);
		
		//B*B
		out.println("B*B =");
		C.makeZero();
		C = B.mult(B);
		out.println(C);
		
		// close the files
		in.close();
		out.close();
	}
}
