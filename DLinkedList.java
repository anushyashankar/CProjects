public class DLinkedList {
	private DListNode firstNode;
	private DListNode lastNode;
	/**
	 * Construct an empty list
	 */
	public DLinkedList() {
		firstNode = null;
		lastNode = null;
	}
	/**
	 * Returns true if the list contains no elements
	 */
	public boolean isEmpty() {
		return (firstNode == null);
	}
	/**
	 * Inserts the argument as the first element of this list.
	 */
	public void addFirst(Object o) {
//		firstNode = new DListNode(o, firstNode, null);
		
		if (firstNode == null) {//list is empty
			firstNode = new DListNode(o, null, null);
			lastNode = firstNode;
		}
		else {
			DListNode one = new DListNode(o, firstNode, null);
			one.setNext(firstNode);
			firstNode.setPrevious(one);
			firstNode = one;
		}
	}
	/**
	 * Inserts the argument as the last element of this list.
	 */
	public void addLast(Object o) {
		if (lastNode == null) { //list is empty
			lastNode = new DListNode(o, null, null);
			firstNode = lastNode;
		}
		else { //one or more nodes
			DListNode one = new DListNode(o, null, lastNode);
			one.setPrevious(lastNode);
			lastNode.setNext(one);
			lastNode = one;
		}
	}
	/**
	 * Removes and returns the first element of this list.
	 */
	public Object removeFirst() {
		if (firstNode == null) {
			System.out.println("List is already empty!");
			return null;
		}
		
		if (firstNode == lastNode) {
			Object one = firstNode.getValue();
			clear();
			return one;
		}
		
		//multiple nodes
		Object one = firstNode.getValue();
		firstNode = firstNode.getNext();
		firstNode.setPrevious(null);
		return one;
	}
	/**
	 * Removes and returns the last element of this list.
	 */
	public Object removeLast() {
//		DListNode temp = firstNode;
//		lastNode = lastNode.getPrevious();
//		return temp;
		
		if (firstNode == null) {
			System.out.println("List is already empty!");
			return null;
		}
		
		if (firstNode == lastNode) { //one node
			Object one = lastNode.getValue();
			clear();
			return one;
		}
		
		//multiple nodes
		Object one = lastNode.getValue();
		lastNode = lastNode.getPrevious();
		lastNode.setNext(null);
		return one;
	}
	/**
	 * Returns a String representation of the list.
	 */
	public String toString() {
		String output = "";
		DListNode temp = firstNode;
		DListNode temp2 = lastNode;

		while (temp != null) {
			output += temp.getValue() + " ";
			temp = temp.getNext();
		}
//		while (temp2 != null) {
//			//output += temp2.getValue() + " ";
//			temp2 = temp2.getPrevious();
//		}
//		while (temp2 != null) {
//			output += temp2.getValue() + " ";
//			temp2 = temp2.getNext();
//		}
		
		return output;
	}
	/**
	 * Returns the number of elements in the list as an int.
	 */
	public int size() {
		DListNode temp = firstNode;
		int count = 0;

		while (temp != null) {
			count ++;
			temp = temp.getNext();
		}

		return count;
	}
	/**
	 * Removes all of the elements from this list.
	 */
	private void clear() {
		firstNode = null;
		//garbage collector would remove each element once nothing is pointing to it
		//domino effect
	}
	public DListNode getFirstNode() {
		return firstNode;
	}
	public DListNode getLastNode() {
		return lastNode;
	}
}
	/**
	 * Returns a DListIterator.
	 */
//	public DListIterator iterator() {
//		return new DListIterator(this);
//	}