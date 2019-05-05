//for the tests
public class MatrixTest {
	public static void main(String[] args) {
		System.out.println("Hello world");
		int test = 2;
		switch (test) {
		case 1:
			// change entry tests
			Matrix koala = new Matrix(5);
			koala.changeEntry(2, 3, 5);

			// change entry test
			System.out.println(koala);
			koala.changeEntry(2, 3, 2);
			koala.changeEntry(2, 5, 3);
			koala.changeEntry(3, 1, 4);
			System.out.println(koala);
			// multiplication test
			koala = koala.scalarMult(2);
			System.out.println(koala);

			// add test
			Matrix koala2 = new Matrix(5);
			koala2.changeEntry(2, 3, 5);
			koala2.changeEntry(1, 1, 4);
			koala2.changeEntry(3, 3, 5);
			koala2.changeEntry(2, 5, -6);
			System.out.println(koala2);
			koala2 = koala2.add(koala);
			System.out.println("koala2\n" + koala2);
			// copy test
			Matrix koala3 = new Matrix(5);
			koala3 = koala2.copy();
			System.out.println("Should be identical to koala2\n" + koala3);

			// make zero test
			koala2.makeZero();
			System.out.println("should be a zero matrix\n" + koala2);

			// transpose test
			koala3 = koala3.transpose();
			System.out.println("koala3 but transpose\n" + koala3);
			break;

			// Matrix multiplication test
		case 2:
			//subtract test
			Matrix koala4 = new Matrix(4);
			int f = 1;
			for(int x = 1; x < 5; x++)
			{
				for(int y = 1; y < 5; y++)
				{
					koala4.changeEntry(x, y, f);
					f++;
				}
			}
			System.out.println("matrix koala4\n" + koala4);
			Matrix koala5 = new Matrix(4);
			koala5 = koala4.copy();
			System.out.println("matrix koala5\n" + koala5);
			koala5.changeEntry(4, 1, 2);
			koala4 = koala5.sub(koala5);
			System.out.println("matrix koala4 after subtracting\n" + koala5);
			
			//multiply test
			Matrix koala6 = koala5.copy();
			System.out.println("copied koala4 matrix to multiply by itself\n" + koala4);
			koala6 = koala6.mult(koala5);
			System.out.println("matrix koala6 after multiplying\n" + koala6);
			break;
		}
	}
}
