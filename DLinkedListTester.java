
public class DLinkedListTester {
	public static void main (String[] args) {
		DLinkedList one = new DLinkedList();
//		DListIterator iter = new DListIterator(one);
		
		one.addFirst(3);
		one.addFirst(2);
		one.addFirst(1);
		
		System.out.println(one); //1 2 3
		
		one.addLast(4);
		one.addLast(5);
		one.addLast(6);
		one.addLast(7);
		
		System.out.println(one); //1 2 3 4 5 6 7
		
		System.out.println(one.isEmpty()); //false
		
		System.out.println(one.removeFirst()); //1
		System.out.println(one); //2 3 4 5 6 7
		
		System.out.println(one.removeLast()); //7
		System.out.println(one); //2 3 4 5 6
		
		System.out.println("\nITERATOR --\n");
		
		DListIterator iter = new DListIterator(one);
		
		System.out.println(iter.hasPrevious()); //false
		iter.next();
		iter.next();
		iter.previous();
		System.out.println(iter.hasNext()); //true
		System.out.println(iter.hasPrevious()); //true
		
		iter.add(2.5);
		System.out.println(iter); //2 2.5 3 4 5 6	
		
		//iter.previous();
		iter.remove();
		System.out.println(iter); //2 3 4 5 6
		
		iter.set(20);
		System.out.println(iter); //2 20 4 5 6
	}
}
