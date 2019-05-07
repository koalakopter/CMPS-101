
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
	public static void main(String[] args) throws IOException {
		
		if (args.length < 2) {
			System.err.println("ERROR: BAD INPUT");
			System.exit(1);
		}
		
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
	}
}
