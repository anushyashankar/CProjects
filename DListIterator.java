public class DListIterator {
	private DListNode current;
	private DListNode previous;
	private DLinkedList myList;
	private boolean canRemove; // for remove() method. true if OK to call

	// remove()

	public DListIterator(DLinkedList list) {
		myList = list;
		current = list.getFirstNode();
		previous = null;
		canRemove = false;
	}
	public String toString() {
		DListNode temp = myList.getFirstNode();
		String output = "";

		while (temp != null) {
			output += temp.getValue() + " ";
			temp = temp.getNext();
		}

		return output;
	}

	public boolean hasPrevious() {
		if (current == null || current.getPrevious() == null)
			return false;
		return true;
	}

	public boolean hasNext() {
		if (current.getNext() == null)
			return false;
		return true;
	}

	public Object next() {
		if (current.getNext() != null) {
			previous = current;
			current = current.getNext();
			return current;
		}
		return null;
	}

	public Object previous() {
		if (previous != null) {
			current = previous;
			previous = previous.getPrevious();
			return current;
		}
		return null;
	}

	public void remove() {
		if (previous == null) //at the beginning
			current = null;
		else if (current.getNext() == null) { //at the end
			previous.setNext(null);
		}
		else {
			//current = current.getNext();
			previous = previous.getPrevious();
			current.setPrevious(previous);
			previous.setNext(current);
		}
	}

	public void add(Object element) {
		DListNode one = new DListNode(element, current, previous);
		if (current == null)
			current = one;
		else if (current != null && previous == null) {
			previous = one;
			current.setPrevious(previous);
		}
		else {
			current.setPrevious(one);
			previous.setNext(one);
			previous = one;
		}
	}

	public void set(Object element) {
		if (current != null)
			current.setValue(element);
	}
}