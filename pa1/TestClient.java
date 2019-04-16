/* JULIAN TO (jcto)
 * PA 1
 * CS 101
 * PROFESSOR TANTALO
 * SPRING 2019
 */
public class TestClient {
	  public static void main(String[] args){
		  /*
		  System.out.println("BEGIN THE TESTS!");

		  List test = new List();
		  //test.insertBefore(5);
		  //test.insertAfter(5);
		  for(int i = 0; i < 7 ; i++)
		  {
			  //System.out.println("appending: " + i);
			  test.append(i);
		  }
		  
		  for(int i = 5; i < 10 ; i++)
		  {
			  //System.out.println("appending: " + i);
			  test.prepend(i);
		  }
		  test.moveBack();
		  test.insertAfter(15);
		  System.out.println("tail is " + test.tail.data);
		  System.out.println("cursor is: " + test.get());
		  //System.out.println(test.cursor == test.tail);
		  
		  System.out.println(test.toString());
		  System.out.println("length: " + test.length());
		  System.out.println("head and tail" + test.head.data + test.tail.data);
		  test.deleteBack();
		  test.deleteFront();
		  System.out.println(test);
		  System.out.println("head and tail" + test.head.data + test.tail.data);
		  
		  //cursor test
		  test.moveFront();
		  System.out.println(test.cursor.data);
		  test.moveNext();
		  System.out.println(test.cursor.data);
		  
		  List test2 = new List();
		  for(int i = 10; i < 20 ; i++)
		  {
			  //System.out.println("appending: " + i);
			  test2.append(i);
		  }
		  System.out.println("List 2: " + test2);
		  test.concat(test2);
		  System.out.println("combine List 1 and 2: " + test);
		  test.moveFront();
		  for(int x = 0; x < 4; x++)
		  {
			  test.moveNext();
		  }
		  System.out.println(test.get());
		  */
		  //random tests
		  System.out.println("secondary tests");
		  List fudge = new List();
		  fudge.append(14);
		  fudge.append(2);
		  System.out.println(fudge + " tail is " + fudge.tail.data);
		  fudge.moveBack();
		  System.out.println(fudge.get() + " index is " + fudge.index());
		  fudge.delete();
		  System.out.println("tail is currently " + fudge.tail.data);
		  System.out.println(fudge.back());
		  fudge.moveFront();
		  System.out.println(fudge.get());
		  fudge.insertAfter(5);
		  System.out.println(fudge);
		  System.out.println(fudge.tail.data);
		  
	  }
}
