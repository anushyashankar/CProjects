
public class Node {
	public Object value;
	public Node next;
	
	public Node() {
		value = null;
		next = null;
	}
	
	public Node(Object v, Node n) {
		value = v;
		next = n;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
