
/* JULIAN TO (jcto)
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

		// counts number of lines in the input file
		int counter = 0;
		while (in.hasNextLine()) {
			counter++;
			in.nextLine();
		}

		in.close();
		in = null;
		String[] input = new String[counter];
		// open file again
		in = new Scanner(new File(args[0]));

		int koala = 0;
		while (in.hasNextLine()) {
			input[koala] = in.nextLine();
			koala++;
		}
		//System.out.println(counter + " strings read");
		for (int x = 0; x < counter; x++) {
			//System.out.println(input[x]);
		}

		// insertion sort
		List poi = new List();
		//start the list 
		poi.append(0);
		int i, j = 0;
		for (i = 1; i < counter; i++) {
			j = i - 1;
			String temp = input[i];
			poi.moveBack();
			//move the element backwards on the list
			//until it is lexicographically less than the one in front
			while (j >= 0 && temp.compareTo(input[poi.get()]) <= 0) {
				poi.movePrev();
				j--;
			}
			if (poi.index() >= 0) {
				poi.insertAfter(i);
			} else {
				poi.prepend(i);
			}
		}
		poi.moveFront();
		//System.out.println(poi);
		//System.out.println(input[poi.get()]);
		//System.out.println(poi.length());
		
		//write output to file
		for(int f = 0; f < poi.length(); f++)
		{
			//System.out.println(input[poi.get()]);
			out.println(input[poi.get()]);
			poi.moveNext();
		}
		// close files
		in.close();
		out.close();
	}
}
