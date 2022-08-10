
public class CircularArrQueue {
	private Object[] arrayQ;
	private int front;
	private int count;
	public CircularArrQueue(int n) {
		arrayQ = new Object[n];
		front = 0;
		count = 0;
	}
	
	public void add(Object obj) {
		int test = 0;
		for (int i = 0; i < arrayQ.length; i++)
			if (arrayQ[i] == null) {
				arrayQ[i] = obj;
				count++;
				test++;
				break;
			}
		
		if (test == 0) {
			int thing = arrayQ.length; 
			this.doubleCapacity();
			arrayQ[thing] = obj;
		}
	}
	
	public Object remove() {
		for (int i = 0; i < arrayQ.length; i++)
			if (arrayQ[i] != null) {
				Object temp = arrayQ[i];
				arrayQ[i] = null;
				return temp;
			}
		front = (front + 1) % arrayQ.length;
		return null;
	}
	
	public int isEmpty() {
		for (int i = 0; i < arrayQ.length; i++)
			if (arrayQ[i] != null)
				return -1;
		return 1;
	}
	
	public void doubleCapacity() {
		Object[] temp = new Object[arrayQ.length*2];
		
		for (int i = 0; i < arrayQ.length; i++)
			temp[i] = arrayQ[i];
		
		arrayQ = temp;
	}
	
	public String toString() {
		String s = " ";
		for (int i = 0; i < arrayQ.length; i++)
			s += arrayQ[i] + " ";
		
		return s;
	}

	public static void main(String[] args) {

		CircularArrQueue a = new CircularArrQueue(10);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		System.out.println(a);
		System.out.println("\nREMOVE NEXT: " + a.remove() + " is removed");
		System.out.println("REMOVE NEXT: " + a.remove()+ " is removed");
		System.out.println("REMOVE NEXT: " + a.remove()+ " is removed");
		System.out.println(a);
		a.add(10);
		System.out.println(a);
		a.add(11);
		System.out.println(a);
		a.add(12);
		System.out.println(a);
		System.out.println("\nREMOVE NEXT: " + a.remove()+ " is removed");
		System.out.println(a);
		a.add(13);
		System.out.println(a);
		a.add(14);
		System.out.println(a);
		a.add(15);
		System.out.println(a);
		System.out.println("(Notice that adding 15 caused the array to double in size)\n");

		System.out.println("\nREMOVE NEXT: " + a.remove()+ " is removed");
		System.out.println(a); 
		}
}
