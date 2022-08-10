import java.util.LinkedList;
import java.util.ListIterator;

public class Fruits {
	
	private LinkedList<String> bowl;
	
	public Fruits() {
		bowl = new LinkedList<String>(); //now you can begin to populate the list
		
		bowl.addLast("apple");
		bowl.addLast("banana");
		bowl.addLast("cherry");
		bowl.addLast("lemon");
		bowl.addLast("lime");
		bowl.addLast("orange");
		bowl.addLast("papaya");
		bowl.addLast("strawberry");
		bowl.addLast("watermelon");
		
	}
	
	public void displayFruit() {
		System.out.println(bowl); //uses toString()
	}
	
	public void displayFruit2() { //without toString()
		for (String t : bowl)
			System.out.println(t);
	}

	public void displayFruit3() { //list iterator
		ListIterator<String> iter = bowl.listIterator();
		while (iter.hasNext())
			System.out.println(iter.next());
	}
	
	public void displayEveryOtherFruit() {
		ListIterator<String> iter = bowl.listIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
			if (iter.hasNext())
				iter.next();
		}
	}

	public void removeEveryOtherFruit() {
		ListIterator<String> iter = bowl.listIterator();
		iter.next();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
			if (iter.hasNext())
				iter.next();
		}
	}
	
	public void displayReverse() {
		ListIterator<String> iter = bowl.listIterator();
		while (iter.hasNext()) {
			iter.next();
		}
		while(iter.hasPrevious()) {
			System.out.println(iter.previous());
		}
	}
	
	public void printLastElement() {
		ListIterator<String> iter = bowl.listIterator();
		while (iter.hasNext()) {
			iter.next();
		}
		System.out.println(iter.previous());
	}
	
	/*Part A*/
	
	public void insertAlphabetical(String newFruit) {
		ListIterator<String> iter = bowl.listIterator();
		while(iter.hasNext()) {
			String fruit = iter.next();
			if (newFruit.compareTo(fruit) <= 0) {
				iter.previous();
				iter.add(newFruit);
				break;
			}
		}
	}
	
	public void removeFruitStartingWithLetter(String beginningLetter) {
		ListIterator<String> iter = bowl.listIterator();
		
		while (iter.hasNext()) {
			if(iter.next().substring(0, 1).equals(beginningLetter))
				iter.remove();
		}
	}
	
	public String find(String inputWord) {
		ListIterator<String> iter = bowl.listIterator();
		while (iter.hasNext()) {
			if (iter.next().equals(inputWord))
				return "is indeed in the list";
		}
		return "is not found in the list";
	}
	
	public void reverseFruits() {
		ListIterator<String> iter = bowl.listIterator();
		while (iter.hasNext())
			iter.next();
		
		LinkedList<String> bowlReverse = new LinkedList<String>();
		while (iter.hasPrevious())
			bowlReverse.add(iter.previous());
		for (String t : bowlReverse)
			System.out.println(t);
	}
	
	public boolean checkPrime(int n) {
		for (int i = 2; i <= n/2; i++) {
			if (n % 1 == 0 && n != 2)
				return false;
		}
		return true;
	}
	
	
	public void removeNonPrimes(LinkedList<Integer> prime) {
		
		ListIterator<Integer> iter = prime.listIterator();

		for(int t : prime) {
			System.out.print(t + " ");
		}
		System.out.println();
		
		while(iter.hasNext()) {
			int temp = iter.next();
			if (!checkPrime(temp))
				iter.remove();
		}
		
		for (int t : prime) {
			System.out.print(t + " ");
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Fruits one = new Fruits();
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		
		for (int i = 0; i < 10; i++)
			numbers.add((int)(Math.random()*10) + 1);
		
		one.removeNonPrimes(numbers);
		
	}

}
