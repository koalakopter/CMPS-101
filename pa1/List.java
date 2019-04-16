/* JULIAN TO (jcto)
 * PA 1
 * CS 101
 * PROFESSOR TANTALO
 * SPRING 2019
 */

//node object that forms the linked list
class Node {
	int data;
	int position;
	Node next;
	Node prev;

	// constructor: takes the first element in the array and stores it in the node
	public Node(int input) {
		this.data = input;
		// this.position = 0;
		this.next = null;
		this.prev = null;
	}
}

//Doubly-Linked List for use with Lex.java
class List {
	// front of the list
	Node head;
	// end of the list
	Node tail;
	// cursor element
	Node cursor;

	// creates a new LinkedList
	public List() {
		// front = new Node(piece);
		head = null;
		tail = null;
		cursor = null;
	}

	// returns the length of the list, 0 if empty
	public int length() {
		int length = 0;
		// if head is null, list must be empty
		if (head == null) {
			return length;
		}
		Node start = head;
		length++; // something is in the list, so it must be at least one long
		while (start.next != null) {
			start = start.next;
			length++;
		}
		return length;
	}

	// returns the index (position) of the cursor element
	public int index() {
		int cursorTrack = -1;
		// default state
		Node cursorRadar = cursor;
		if (cursorRadar == null) {
			return -1;
		}

		Node start = head;
		while (start != null) {
			start = start.next;
			cursorTrack++;
		}
		cursorTrack++;
		return cursorTrack;
	}

	// like the above function, but returns the element instead
	public int get() {
		if (this.length() <= 0 || this.index() <= 0) {
			throw new RuntimeException("LIST EMPTY OR CURSOR UNDEFINED");
		}
		// return this.cursor.position;
		Node start = head;
		// int position = 0;
		while (start != cursor) {
			start = start.next;
		}
		return start.data;
	}

	// returns the int at the front of the list, else returns null
	public int front() {
		if (this.length() <= 0) {
			throw new IllegalArgumentException("Length of List is zero!");
		}
		return head.data;
	}

	// traverse the list to the end and then get the end returns the string
	// null if length is zero or less
	public int back() {
		if (this.length() <= 0) {
			throw new IllegalArgumentException("Length of List is zero!");
		}
		Node start = head;
		while (start.next != null) {
			start = start.next;
		}
		return start.data;
	}

	// checks if two lists are equal
	boolean equals(List L) {
		// used to traverse the array and compare values
		Node start = head;
		Node start2 = L.head;
		// checks for same length
		if (this.length() != L.length()) {
			return false;
		}
		//two empty lists are equal, I presume
		if(this.length() == 0 && L.length() == 0)
		{
			return true;
		}
		while (start.next != null || start2.next != null) {
			start = start.next;
			start2 = start2.next;
			// checks for equality of elements
			if (start.data != start2.data) {
				return false;
			}
		}
		return true;
	}

	// List Manipulation
	// clears the list
	void clear() {
		// just set the head to null cause lazy
		this.head = null;
		this.tail = null;
		this.cursor = null; //cursor is null too
	}

	// puts cursor under front element
	void moveFront() {
		if (this.length() <= 0) {
			// do nothing!
		} else {
			cursor = head;
		}

	}

	// puts cursor under rear element
	void moveBack() {
		if (this.length() <= 0) {
			// throw new IllegalArgumentException("Length is zero!");
			// DO NOTHING
		} else {
			Node start = head;
			while (start.next != null) {
				start = start.next;
			}
			cursor = start;
		}
	}

	// puts cursor on the element one previous to the current
	void movePrev() {
		// if cursor at the front make it undefined
		if (cursor == head) {
			cursor = null;
		}
		// if cursor is undefined
		else if (this.index() == -1) {
			// do nothing
		} else {
			cursor = cursor.prev;
		}
	}

	// puts cursor on the element one after the current
	void moveNext() {
		// if cursor at the end, make it undefined too
		if (cursor.next == null) {
			cursor = null;
		}
		// if cursor is undefined
		else if (this.index() == -1) {
			// do nothing
			// System.out.println("am i here?");
		} else {
			cursor = cursor.next;
		}
	}

	// adds a node to the front of the list
	void prepend(int data) {
		// makes a new node
		Node freshNode = new Node(data);
		// if list is empty, make it the first element
		if (this.length() == 0) {
			head = freshNode;
			tail = freshNode;
		}
		// if list is non empty, put the element at the front
		else {
			// inserts into the front by making the current head point to the inserted node
			freshNode.next = head;
			this.head.prev = freshNode;
			// then, set head to the new Node
			head = freshNode;
		}
	}

	// adds a node to the end of the list
	void append(int data) {
		// make a new node
		Node freshNode = new Node(data);
		if (this.length() == 0) {
			// System.out.println("me append");
			head = freshNode;
			tail = freshNode;
		}
		// if not empty, traverse to the end and add to it
		else {
			// System.out.println("me append2");
			// pretty much the opposite of what happened above
			tail.next = freshNode;
			freshNode.prev = tail;
			tail = freshNode;
		}
	}

	// adds an element before the current cursor's position
	void insertBefore(int data) {
		// check for prereqs
		if (this.length() <= 0 || this.index() < 0) {
			throw new IllegalArgumentException("Cursor undefined or invalid List!");
		}
		Node freshNode = new Node(data);
		// if the cursor is on the head, make the inserted Node the head
		if (cursor == head) {
			// System.out.println("test");
			head.prev = freshNode;
			freshNode.next = head;
			// check if the list is length 1
			if (head == tail) {
				tail = head;
			}
			head = freshNode;
		}
		// otherwise insert normally
		else {
			Node temp = cursor.prev;
			cursor.prev.next = freshNode;
			cursor.prev = freshNode;

			freshNode.next = cursor;
			freshNode.prev = temp;
		}
	}

	// adds an element after the current cursor's position
	void insertAfter(int data) {
		if (this.length() <= 0 || this.index() < 0) {
			throw new IllegalArgumentException("Cursor undefined or invalid List!");
		}
		Node freshNode = new Node(data);
		// if the cursor is on the tail, make the inserted Node the tail
		if (cursor == tail) {
			// System.out.println("test");
			tail.next = freshNode;
			freshNode.prev = tail;
			// check if the list is length 1
			if (head == tail) {
				// head = freshNode.prev;
			}
			tail = freshNode;
		} else {
			// otherwise insert normally
			Node temp = cursor.next;
			cursor.next.prev = freshNode;
			cursor.next = freshNode;

			freshNode.prev = cursor;
			freshNode.next = temp;
		}
	}

	// delete the front element
	void deleteFront() {
		if (this.length() == 0) {
			throw new IllegalArgumentException("Length is zero!");
		}
		// if length 1, just delete the whole list
		if (this.length() == 1) {
			head = null;
			tail = null;
			return;
		}
		// simply move the head to the next element
		head.next.prev = null;
		head = head.next;
	}

	// delete the rear element
	void deleteBack() {
		if (this.length() == 0) {
			throw new IllegalArgumentException("Length is zero!");
		}
		// if length 1, just delete the whole list
		if (this.length() == 1) {
			head = null;
			tail = null;
			return;
		}
		// delete the tail
		tail.prev.next = null;
		tail = tail.prev;
	}

	// delete the cursor element
	void delete() {
		Node point = cursor;
		// delete the links in the list that connect cursor to the rest of the list
		if (point == head) {
			head = cursor.next;
			head.prev = null;
			cursor = null;
			return;
		}
		if (point == tail) {
			// System.out.println("here?");
			tail = cursor.prev;
			tail.next = null;
			cursor = null;
			return;
		}
		if (point != head && point != tail) {
			cursor.prev.next = point.next;
			cursor.next.prev = point.prev;
			cursor = null;
			return;
		}
	}

	// toString method for printing
	public String toString() {
		Node start = head;
		String output = "";
		// if list is empty, print nothing
		if (this.length() == 0) {
			// System.out.println("fugg");
			return "";
		}
		while (start.next != null) {
			// System.out.println(start.data);
			output = output + start.data + " ";
			start = start.next;
		}
		output = output + start.data + " ";
		return output;
	}

	// copy the list into a new list
	public List copy() {
		List copiedList = new List();
		//if length of current list is zero, do nothing
		if(this.length() == 0)
		{
			return copiedList;
		}
		Node start = head;
		// traverse the list, and copy the elements into a new list
		while (start.next != null) {
			copiedList.append(start.data);
			start = start.next;
		}
		copiedList.append(start.data);
		return copiedList;
	}

	// joins two lists together, with the input List being on the end
	// note: not tested
	List concat(List L) {
		// List newList = new List();
		tail.next = L.head;
		L.head.prev = tail;

		// change the tail to L's tail
		tail = L.tail;
		return this;
	}
}
