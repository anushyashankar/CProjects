import java.util.*;

public class ArrayListStack implements MyStack{

	private ArrayList<String> stackAL;

	public ArrayListStack() {
		stackAL = new ArrayList<String>();
	}

	public ArrayListStack(ArrayList<String> s) {
		stackAL = s;
	}

	public Object push(Object item) {
		stackAL.add((String) item);
		return item;
	}

	public Object pop() {
		String temp = stackAL.get(stackAL.size() - 1);
		stackAL.remove(stackAL.size() - 1);
		return temp;
	}

	public Object peek() {
		return stackAL.get(stackAL.size() - 1);
	}
	
	public boolean isEmpty() {
		return stackAL.isEmpty();
	}
	
	public int size() {
		return stackAL.size();
	}
	
	public int search (Object a) {
		for (int i = 0; i < stackAL.size(); i++) 
			if (stackAL.get(i).equals(a))
				return 1;
		
		return -1;
	}
	
	public String toString() {
		String output = "";
		for (int i = stackAL.size() - 1; i >= 0; i--) {
			output += stackAL.get(i) + " ";
		}
		
		return output;
	}
}
