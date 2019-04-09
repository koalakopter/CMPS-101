//node object that forms the linked list
class Node {
	String data;
	int position;
	Node next;
	
	// constructor: takes the first element in the array and stores it in the node
	public Node(String input) {
		this.data = input;
		this.position = 0;
		next = null;
	}
}
