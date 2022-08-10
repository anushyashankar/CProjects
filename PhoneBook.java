import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.util.*;
public class PhoneBook {

	private Node[] book;
	private int size;

	public PhoneBook() {
		book = new Node[10];
		book[0] = new Node();
		book[1] = new Node();
		book[2] = new Node();
		book[3] = new Node();
		book[4] = new Node();
		book[5] = new Node();
		book[6] = new Node();
		book[7] = new Node();
		book[8] = new Node();
		book[9] = new Node();

		size = 0;
	}

	public double getLoadFactor() {
		return (double)(size)/book.length;
	}

	public PhoneBook(int n) {
		book = new Node[n];
		for (int i = 0; i < n; i++) {
			book[i] = new Node();
		}

		size = 0;
	}
	public void load()
	{
		try
		{
			Scanner file = new Scanner(new File("phone.txt"));
			//add code here to read from the file 
			int count = 0;
			//and add Parts to the map
			while(file.hasNextLine()) {
				String temp = file.next();
				count++;
			}

			file = new Scanner(new File("phone.txt"));
			String[] words = new String[count];

			int counter = 0;
			while (file.hasNextLine()) {
				String temp = file.next();
				words[counter] = temp;
				counter++;
			}
			String n = "";

			String num = "";

			for (int i = 0; i < words.length; i++) {
				if (i%2 == 0) {
					n = words[i];
				}
				if (i%2 == 1) {
					num = words[i];
					PhoneEntry thing = new PhoneEntry(n, num);
					this.add(thing);
				}
			}

		}
		catch( IOException e )  //Most specific exceptions must be listed 1st
		{
			out.println(e);
		}
		catch( RuntimeException e )
		{
			out.println(e);
		}
		catch( Exception e )
		{
			out.println(e);
		}
		finally
		{
			//no code needed here
		}
	}

	public void add(Object obj) {
		int num = this.HashCode((PhoneEntry)obj);

		Node temp = book[num];

		if (this.getLoadFactor() > 0.75) {
			Node[] big = new Node[book.length*2];

			for (int i = 0; i < big.length; i++) {
				big[i] = new Node();
			}

			for (int i = 0; i < book.length; i++) {
				if (book[i] != null) {
					while (book[i].value != null) {
						PhoneEntry phone = (PhoneEntry) book[i].value;

						int nums = this.HashCode(phone);

						if (big[nums].value == null) {
							big[nums].setValue(phone);
						}
						else {
							Node x = big[nums];

							while(x.next != null) {
								x = x.next;
							}

							Node a = new Node(phone, null);
							x.setNext(a);
						}

						book[i] = book[i].next;
						if (book[i] == null)
							break;
					}
				}
			}
			
			book = big;
		}

		if (book[num].value == null) {
			book[num].setValue(obj);
			size++;
			return;
		}

		while(temp.next != null) {
			temp = temp.next;
		}

		Node thing = new Node(obj, null);
		temp.setNext(thing);
		size++;
	}

	public int HashCode(PhoneEntry temp) {
		int thing = (int)(Math.random()*1000)*temp.getName().length();

		return thing%book.length;
	}

	public int getCapacity() {
		return book.length;
	}

	public int getSize() {
		int count = 0;
		for (int i = 0; i < book.length; i++) {
			if (book[i] != null ) {
				Node temp = book[i];
				while (temp.value != null) {
					temp = temp.next;
					count++;
					if (temp == null)
						break;
				}
			}
		}
		return count;
	}

	public int getNumberOfNulls() {
		int count = 0;
		for (int i = 0; i < book.length; i++) {
			if (book[i].value == null) {
				count++;
			}

		}
		return count;
	}

	public int getLongestList() {
		int biggest = 0;
		for (int i = 0; i < book.length; i++) {
			int count = 0;
			if (book[i] != null) {
				Node temp = book[i];

				while (temp.value != null) {
					count++;
					temp = temp.next;
					if (temp == null)
						break;
				}
			}
			if (count > biggest) {
				biggest = count;
			}
		}

		if (biggest == 0) {
			return -1;
		}
		return biggest;
	}

	public boolean changeNumber(String lookfor, String number) {
		for (int i = 0; i < book.length; i++) {
			if (book[i] != null) {
				Node temp = book[i];
				while (temp.value != null) {
					PhoneEntry tem = (PhoneEntry)temp.value;
					if (tem.getName().equalsIgnoreCase(lookfor)) {
						tem.setNumber(number);
						return true;
					}
					temp = temp.next;
					if (temp == null)
						break;
				}
			}
		}
		return false;
	}

	public String lookup(String lookfor) {
		for (int i = 0; i < book.length; i++) {
			if (book[i] != null) {
				Node temp = book[i];
				while (temp.value != null) {
					PhoneEntry tem = (PhoneEntry)temp.value;
					if (tem.getName().equalsIgnoreCase(lookfor)) {
						return tem.getNumber();
					}
					temp = temp.next;
					if (temp == null)
						break;
				}
			}
		}
		return "Name doesn't exist";
	}

	public String toString() {
		String thing = "";
		for (int i = 0; i < book.length; i++) {
			thing+= "Row " + i;

			if (book[i] != null) {
				Node temp = book[i];
				while (temp.value != null) {
					thing += temp.value + "\t";
					temp = temp.next;
					if (temp == null)
						break;
				}
				thing+= "\n";
			}
		}

		return thing;
	}


}
