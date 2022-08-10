
public class ValueList {
	private ListNode front;

	public ValueList() {
		front = null;
	}

	public ValueList(ListNode sentIn) {
		front = sentIn;
	}

	public void addToFront(ListNode sentIn) {
		ListNode temp = new ListNode(sentIn.getValue(), front);
		front = temp;
	}

	public void addToEnd(ListNode sentIn) {
		ListNode temp = front;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		temp.setNext(new ListNode(sentIn.getValue(), null));
	}

	public static void print(ListNode list) {
		while (list != null) {
			if (list.getNext() == null)
				System.out.print(list.getValue());
			else
				System.out.print(list.getValue() + ", ");
			list = list.getNext();
		}
		System.out.println();
	}

	/**
	 *     IMPLEMENT
	 *     THE TWO
	 *     METHODS
	 *     BELOW
	 */
	
		// Method switchEnds will swap the values of the first and last nodes
	// in the linked list.  
	// EXPECTED OUTPUT:
	// Original: 1, 5, 3, 4, 7
    // After switch both ends:
	//           7, 5, 3, 4, 1
	public void switchEnds() {
		if (front == null)
			return;
		ListNode temp = new ListNode(front.getValue(), front.getNext());
		ListNode temp2 = front;
		while (temp2.getNext() != null) {
			temp2 = temp2.getNext();
			
		}
		front.setValue(temp2.getValue()); //last value in first node
		temp2.setValue(temp.getValue()); //first value in last node
		
		return;
	}

	// Method lessThanList() finds all the values smaller than
	// argument obj and creates / returns a new ValueList that
	// contains only those smaller values.
	// EXPECTED OUTPUT:
	// Original: 3, 5, 1, 4, 7
    // After calling lessThanList(5):
	//    (new list)  4, 1, 3   (order does not matter)
	public ValueList lessThanList(Comparable obj) {
		if (front == null)
			return null;
		ListNode temp = front;
		
		while (temp.getNext() != null) {
			if ((Integer) temp.getValue() < (Integer) obj) { //is sent in as String, but still getting an error
				this.addToEnd(new ListNode((Integer) temp.getValue(), null));
			}
			temp = temp.getNext();
		}
		
		return this;
	}
	

	
	
	/**
	 *     RUNNERS
	 *     BELOW
	 */
	
	public static void main(String[] args) {
		
		
		// runner switchEnds()
		// EXPECTED OUTPUT:
		// Original: 1, 5, 3, 4, 7
	    // After switch both ends:
		//           7, 5, 3, 4, 1
		ListNode f = new ListNode("1",
				new ListNode("5", new ListNode("3", new ListNode("4", new ListNode("7", null)))));
		ValueList two = new ValueList(f);
		System.out.print("Original: ");
		print(two.front);
		System.out.print("After switch both ends:\n\t  ");
		two.switchEnds();
		print(two.front);
		System.out.println();
		
		
	
		//runner lessThanList()
		// EXPECTED OUTPUT:
		// Original: 3, 5, 1, 4, 7
	    // After calling lessThanList(5):
		//           4, 1, 3   (order does not matter)
		ListNode g = new ListNode("3",
				new ListNode("5", new ListNode("1", new ListNode("4", new ListNode("7", null)))));
		ValueList lessList = new ValueList(g);
		System.out.print("Original: ");
		print(lessList.front);
		ValueList moreLess = lessList.lessThanList("5");
		System.out.println("After calling lessThanList(5):");
		System.out.print("\t  ");
		print(moreLess.front);
		
		
		
	}
}
