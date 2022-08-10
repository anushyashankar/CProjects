
public class KidsBookCollection {

	public static void main(String[] args) {
//		LinkedList books = new LinkedList();
//		System.out.println("Testing AddFirst");
//		books.addFirst("Goodnight Moon");
//		books.addFirst("Bagel Mouse");
//		books.addFirst("Charlotte's Web");
//		books.addFirst("Brown Bear, Brown Bear");
//		System.out.println(books);
//
//		System.out.println("\nRemove One");
//		books.addFirst("Cat in the Hat");
//		books.addFirst("Three Blind Mice");
//		System.out.println(books.removeFirst());
//		System.out.println();
//		System.out.println(books);
		
		LinkedList withPrime = new LinkedList();
		for (int i = 1; i <= 20; i++) {
			int random = (int)(Math.random()*20) +1;
			withPrime.addFirst(random);
		}
		System.out.println(withPrime);
		
		
		

//		System.out.println("\nRemove 2");
//		books.addFirst("The Little Prince");
//		books.addFirst("Little Engine that could");
//		books.addFirst("Charlie and the Chocolate Factory");
//		System.out.println(books.removeFirst());
//		System.out.println(books.removeFirst());
//		System.out.println();
//		System.out.println(books);
//
//		ListIterator iter = books.listIterator();
//		System.out.println("\nTesting Add");
//		iter.next();
//		iter.add("Pippi Longstockings");
//		System.out.println("");
//		System.out.println(books);
//		iter.add("Where the Wild Things Are");
//		System.out.println();
//		System.out.println(books);
//
//		System.out.println("\nTesting Remove");
//		iter.next();
//		iter.next();
//		iter.remove();
////		iter.remove();  //calling this should throw an exception
//		System.out.println();
//		System.out.println(books);
//		iter.next();
//		iter.remove();
//		System.out.println();
//		System.out.println(books);

		System.out.println("\nTesting set");
		iter.next();
		iter.set("Green Eggs and Ham");
		System.out.println(books);
	}

}
/*
 *    You can install the following toString() method in
 *    your LinkedLink class - this will allow us to work 
 *    with an object without needing any BookCollection
 *    methods or instance variables.
 */

//public String toString() {
//
//	String a = "";
//	ListIterator iter = this.listIterator();
//	while (iter.hasNext())
//		a += iter.next() + "\n";
//	return a;
//}
