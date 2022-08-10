
public class PhoneTesterA {

	public static void main(String[] args) {
		PhoneBook book = new PhoneBook(15);
		book.load();
		System.out.println(book);
		System.out.println("Capacity: " + book.getCapacity() + "\nSize: " + book.getSize() + "\nLongest list length: "
				+ book.getLongestList() + "\nNull entries: " + book.getNumberOfNulls());
		
		System.err.println(book.lookup("Lee"));
		System.err.println(book.lookup("Randolph"));
		System.err.println("Change Phone Number for Price... " + book.changeNumber("Price", "1111111"));
		PhoneEntry h = new PhoneEntry("Randolph", "7165100");
		book.add(h);

		System.out.println(book);
		System.out.println("Capacity: " + book.getCapacity() + "\nSize: " + book.getSize() + "\nLongest list length: "
				+ book.getLongestList() + "\nNull entries: " + book.getNumberOfNulls());
		System.err.println(book.lookup("Randolph"));
		// System.out.println("Capacity: " + b.getCapacity() + "\nSize: " + b.getSize()
		// + "\nNull entries: " + b.getNumberOfNulls());

	}

}