//for the tests
public class MatrixTest {
	public static void main(String[] args)
	{
		System.out.println("Hello world");
		//change entry tests
		Matrix koala = new Matrix(5);
		koala.changeEntry(2, 3, 5);
		
		//change entry test
		System.out.println(koala);
		koala.changeEntry(2, 3, 2);
		koala.changeEntry(2, 5, 3);
		koala.changeEntry(3, 1, 4);
		System.out.println(koala);
		//multiplication test
		koala = koala.scalarMult(2);
		System.out.println(koala);
		
		//add test
		Matrix koala2 = new Matrix(5);
		koala2.changeEntry(2, 3, 5);
		koala2.changeEntry(1, 1, 4);
		koala2.changeEntry(3, 3, 5);
		koala2.changeEntry(2, 5, -6);
		System.out.println(koala2);
		koala2 = koala2.add(koala);
		System.out.println(koala2);
		//copy test
		Matrix koala3 = new Matrix(5);
		koala3 = koala2.copy();
		System.out.println("Should be identical to koala2\n" + koala3);
		
		//make zero test
		koala2.makeZero();
		System.out.println("should be a zero matrix\n" + koala2);
	
		
	}
}
