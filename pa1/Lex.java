
/* JULIAN TO
 * PA 1
 * CS 101
 * PROFESSOR TANTALO
 * SPRING 2019
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lex {
	public static void main(String[] args) throws IOException {

		// System.out.println("begin program");

		// checks for valid input files
		if (args.length < 2) {
			System.err.println("ERROR: BAD INPUT");
			System.exit(1);
		}

		// read in input file
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

		while (in.hasNextLine()) {
			// lineNumber++; //what does this even do

			// trim leading and trailing spaces, then add one trailing space so
			// split works on blank lines
			String line = in.nextLine().trim() + " ";

			// split line around white space
			// String[] token = line.split("\\s+");
			String[] token = line.split("\\r?\\n\"");

			int n = token.length;

			// prints out tokens for testing
			for (int i = 0; i < n; i++) {
				System.out.println(token[i]);
			}

		}
	}
}
