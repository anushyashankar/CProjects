
public class PhoneTesterB {
	public static void main(String[] args) {
		PhoneBook book = new PhoneBook();
		book.load();
		System.out.println(book);
		System.out.println("\nCapacity: " + book.getCapacity() + "\tSize: " + book.getSize()
		+ "\tloadFactor (capacity/size): " + threeDecs(book.getLoadFactor()) + "  (should be less than 0.75)"
		+ "\n\nLongest list length: " + book.getLongestList() + "\nNull entries: " + book.getNumberOfNulls());

		System.err.println(book.lookup("Lee"));
		System.err.println(book.lookup("Randolph"));
		System.err.println("Change Phone Number for Price...... " + book.changeNumber("Price", "1111111"));
		PhoneEntry h = new PhoneEntry("Randolph", "7165100");
		book.add(h);

		System.out.println(book);
		System.out.println("\nCapacity: " + book.getCapacity() + "\tSize: " + book.getSize()
		+ "\tloadFactor (capacity/size): " + threeDecs(book.getLoadFactor()) + "  (should be less than 0.75)"
		+ "\n\nLongest list length: " + book.getLongestList() + "\nNull entries: " + book.getNumberOfNulls());
		System.err.println(book.lookup("Randolph"));
		
	}
	
	public static double threeDecs(double num) {
		return ((int) (num * 1000) / 1000.0);
	}

}
