import java.util.*;

public class ArrayStack {
	
	private String stackA[];
	
	public ArrayStack() {
		stackA = new String[10];
	}
	
	public ArrayStack(String[] s) {
		stackA = s;
	}
	
	public Object push(Object item) {
		for (int i = 0; i < stackA.length; i++) { //if there's space left in the array
			if (stackA[i] == null) {
				stackA[i] = (String) item;
				return item; //so that it exits here
			}
		}
		
		String temp[] = new String[stackA.length + 1]; //if array isn't big enough
		for (int i = 0; i < stackA.length; i++) {
			temp[i] = stackA[i];
		}
		
		temp[temp.length - 1] = (String) item; //last element
		stackA = temp;
		return item;
	}
	
	public Object pop() {
		int index = -1;
		for (int i = 0; i < stackA.length; i++) { //if there's space left in the array
			if (stackA[i] == null) {
				index = i - 1;
				break;
			}
		}
		if (index == -1)
			index = stackA.length - 1;
		
		String temp = stackA[index];
		stackA[index] = null;
		return temp;
	}
	
	public Object peek() {
		int index = -1;
		for (int i = 0; i < stackA.length; i++) { //if there's space left in the array
			if (stackA[i] == null) {
				index = i - 1;
				break;
			}
		}
		if (index == -1)
			index = stackA.length - 1;
		
		return stackA[index];
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < stackA.length; i++)
			if (stackA[i] != null)
				return false;
		
		return true;
	}
	
	public int size() {
		int index = -1;
		for (int i = 0; i < stackA.length; i++) { //if there's space left in the array
			if (stackA[i] == null) {
				index = i - 1;
				break;
			}
		}
		if (index == -1)
			index = stackA.length - 1;
		
		return index + 1;
	}
	
	public int search (Object a) {
		for (int i = 0; i < stackA.length; i++) 
			if (stackA[i] != null)
				if (stackA[i].equals(a))
					return 1;
		return -1;
	}
	
	public String toString() {
		String output = "";
		
		for (int i = 0; i < this.size(); i++) {
			output += stackA[i] + " ";
		}
		
		return output;
	}
}
