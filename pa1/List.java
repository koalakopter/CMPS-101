//Doubly-Linked List for use with Lex.java
class List {
	Node head;
	
	//cursor element
	Node cursor;
	
	// creates a new LinkedList
	public List() {
		// front = new Node(piece);
		head = null;
	}
	
	//returns the length of the list, 0 if empty
	public int length()
	{
		int length = 0;
		Node start = head;
		while(start.next != null)
		{
			start = start.next;
			length++;
		}
		return length;
	}
	
	//returns the index (position) of the cursor element
	public int index()
	{
		int cursor = -1; //default state
		
		return cursor;
	}
	//like the above function, but returns the element instead
	public int get()
	{
		if (this.length() <= 0 && this.index() <= 0)
		{
			throw new IllegalArgumentException("Length is zero!");
		}
		return this.cursor.position;
	}
	
	//returns the string at the front of the list, else returns null
	public int front()
	{
		if(this.length() <= 0)
		{
			throw new IllegalArgumentException("Length is zero!");
		}
		return head.position;
	}
	
	//traverse the list to the end and then get the end returns the string
	//null if length is zero or less
	public int back()
	{
		if(this.length() <= 0)
		{
			throw new IllegalArgumentException("Length is zero!");
		}
		Node start = head;
		while(start.next != null)
		{
			start = start.next;
		}
		return start.position;
	}
	
	//checks if two lists are equal
	boolean equals (List L)
	{
		//used to traverse the array and compare values
		Node start = head;
		Node start2 = L.head;
		//checks for same length
		if(this.length() != L.length())
		{
			return false;
		}
		while(start.next != null && start2.next != null)
		{
			if(start.position != start2.position)
			{
				return false;
			}
		}
		return true;
	}
}
