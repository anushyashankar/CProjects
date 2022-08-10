import java.util.*;

public class LinkedListStack implements MyStack{
	private LinkedList<String> stackLL;
	
	public LinkedListStack() {
		stackLL = new LinkedList<String>();
	}
	
	public LinkedListStack(LinkedList<String> s) {
		stackLL = s;
	}
	
	public Object push(Object item) {
		stackLL.addLast((String) item);
		return item;
	}
	
	public Object pop() {
		String temp = stackLL.getLast();
		stackLL.removeLast();
		return temp;
	}
	
	public Object peek() {
		return stackLL.getLast();
	}
	
	public boolean isEmpty() {
		return stackLL.isEmpty();
	}
	
	public int size() {
		return stackLL.size();
	}
	public int search (Object a) {
		ListIterator<String> iter = stackLL.listIterator();
		
		while (iter.hasNext()) {
			if (iter.next().equals(a))
				return 1;
		}
		
		return -1;
	}
	public String toString() {
		ListIterator<String> iter = stackLL.listIterator();
		String output = "";
		
		while(iter.hasNext()) {
			iter.next();
		}
		while (iter.hasPrevious()) {
			output += iter.previous() + " ";
		}
		
		return output;
	}
}
