import java.util.NoSuchElementException;

/**
 * A linked list is a sequence of nodes with efficient element insertion and
 * removal. This class contains a subset of the methods of the standard
 * java.util.LinkedList class.
 */
public class LinkedList {
	private Node first;

	/**
	 * Constructs an empty linked list.
	 */
	public LinkedList() {
		// complete code to initialize the instance variable
		first = null;
	}

	/**
	 * Returns the first element in the linked list.
	 * 
	 * @return the first element in the linked list
	 */
	public Object getFirst() {
		if (first == null) {throw new NoSuchElementException();
		}
		// complete code to return the data held in the first node
		return first.data;
	}

	/**
	 * Removes the first element in the linked list.
	 * 
	 * @return the removed element
	 */
	public Object removeFirst() {
		if (first == null) {throw new NoSuchElementException();
		}
		// complete code to delete the first node
		// remember, we know that the linked list is not empty
		// also remember to return the data held in that removed node
		LinkedListIterator iter = new LinkedListIterator();
		
		Node temp = new Node();
		iter.next();
		temp = iter.position;
		
		first = null;
		return temp.data;
	}

	/**
	 * Adds an element to the front of the linked list.
	 * 
	 * @param element the element to add
	 */
	public void addFirst(Object element) {
		// complete code to add a new node containing element
		// at the beginning of the linked list
		Node add = new Node(element, first);
		first = add;
	}

	/*Part B*/
	public void insertAlphabetical(String word) {
		Node temp = first;
		Node addWord = new Node(word, temp);
		
		if (temp != null && word.compareTo(temp.data.toString()) <= 0)
			addFirst(word);
		
		else {
			while (temp.next != null) {
				String fruit = temp.next.data.toString();
				if (word.compareTo(fruit) <= 0) {
					addWord.next = temp.next;
					temp.next = addWord;
					break;
				}
				temp = temp.next;
			}
			
			if (temp.next == null) {
				addWord.next = temp.next;
				temp.next = addWord;
			}
		}
	}
	
	public void removeLetter(String beginningLetter) {
		Node temp = first;
		
		if (first != null && first.data.toString().substring(0,1).equals(beginningLetter))
			first = first.next;
		
		else {
			while (temp.next != null) {
				String letter = temp.next.data.toString().substring(0,1);
				if (letter.equals(beginningLetter))
					temp.next = temp.next.next;
				temp = temp.next;
			}
		}
	}
	
	public String find(String inputWord) {
		Node temp = first;
		if (first == null)
			return "list is empty";
		else if (first.data.toString().equals(inputWord))
			return "word is found in the list";
		else {
			while (temp.next != null) {
				if (temp.next.data.toString().equals(inputWord))
					return "word is found in the list";
				temp = temp.next;
			}
		}
		return "is not found";
	}
	
	public void reverseList() {
		Node temp = first;
		Node previous = null;
		Node current = null;
		
		while (temp.next != null) {
			current = temp;
			current.next = previous;
			previous = current;
			
			temp = temp.next;
		}
		first = current;
	}
	
	public boolean checkPrime(int n) {
		for (int i = 2; i <= n/2; i++) {
			if (n % 1 == 0 && n != 2)
				return false;
		}
		return true;
	}
	
	
	public void removeCompositeNumbers() {
		if (first != null && !checkPrime((int) first.data)) {
			first = first.next;
		}
		
		Node temp = first;
		
		while (temp.next != null) {
			if (!checkPrime((int) temp.data)) {
				temp.next = temp.next.next;
			}
			else
				temp = temp.next;
		}
	}
	
	/**
	 * Returns an iterator for iterating through this list.
	 * 
	 * @return an iterator for iterating through this list
	 */
	public ListIterator listIterator() {
		return new LinkedListIterator();
	}
/**
 * We will use a private inner class for our Node class
 * as well as for our LinkedListIterator class
 * Notice that the class is private, but the 
 * instance variables are public (Why?)
 * 
 */
	private class Node {
		public Object data;
		public Node next;
		
		public Node() {

		}
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private class LinkedListIterator implements ListIterator {
		private Node position;
		private Node previous;
		private boolean isAfterNext; //this is a flag that tracks if next() has been called

		/**
		 * Constructs an iterator that points to the front of the linked list.
		 */
		public LinkedListIterator() {
			position = null;
			previous = null;
			isAfterNext = false;  // set to true after traversing node to allow for remove() and set()
		}

		/**
		 * Moves the iterator past the next element.
		 * 
		 * @return the traversed element
		 */
		public Object next() {
			if (!hasNext()) {throw new NoSuchElementException();}
			previous = position; // Used so that I can remember node for remove
			isAfterNext = true;

			if (position == null) {
				position = first;
			} else {
				position = position.next;
			}

			return position.data;
		}

		/**
		 * Tests if there is an element after the iterator position.
		 * 
		 * @return true if there is an element after the iterator position
		 */
		public boolean hasNext() {
			if (position == null) {
				return first != null;
			} else {
				return position.next != null;
			}
		}

		/**
		 * Adds an element before the iterator position and moves the iterator past the
		 * inserted element.
		 * 
		 * @param element the element to add
		 */
		public void add(Object element) {
			if (position == null) {
				addFirst(element);
				position = first;
			} else {
				// complete code to add a new node containing element
				// in the middle of the linked list
				element = this.next();
				previous = new Node(element, position);
			
			}

			isAfterNext = false;
		}

		/**
		 * Removes the last traversed element. This method may only be called after a
		 * call to the next method.
		 */
		public void remove() {
			if (!isAfterNext) {	throw new IllegalStateException();}

			if (position == first) {
				removeFirst();
			} else {
				// complete code to delete the node at 'position'
				previous = position.next;
			}
			position = previous;
			isAfterNext = false;
		}

		/**
		 * Sets the last traversed element to a different value.
		 * 
		 * @param element the element to set
		 */
		public void set(Object element) {
			if (!isAfterNext) {throw new IllegalStateException();
			}
			// complete code to overwrite the data at position with element
			position.data = element;
		}
		
		
		
	}
	
	
	
	public String toString() {
		
		String a = "";
		ListIterator iter = this.listIterator();
		while (iter.hasNext())
			a += iter.next() + "\n";
		return a;
	}
}
