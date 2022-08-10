import java.util.*;

public class Deck {
	Stack<Card> deck;
	public Deck() {
		deck = new Stack<Card>();
		loadDeck();
	}

	public Stack<Card> getDeck() {
		return deck;
	}

	// Load the stack with 52 cards in order
	public void loadDeck() {
		for (int i = 1; i <= 4; i++)
			for (int j = 2; j <= 14; j++)
				deck.add(new Card(i, j));
	}

	public int size() {
		Stack<Card> temp = new Stack<Card>();
		int count = 0;

		while (!deck.isEmpty()) {
			temp.add(deck.pop());
			count++;
		}

		//String thing = "";

		while (!temp.isEmpty()) {
			deck.add(temp.pop());
		}

		return count;
	}

	// Print the stack - for uniformity,
	// make 13 rows in four columns, filling
	// in each row from left to right
	public String toString() {
		Stack<Card> temp = new Stack<Card>();
		//Stack<Card> temp2 = new Stack<Card>();

		while (!deck.isEmpty()) {
			temp.add(deck.pop());
		}

		String thing = "";

		for (int i = 1; !temp.isEmpty(); i++) {
			if (i % 4 == 0) {
				Card a = temp.pop();
				deck.add(a);
				thing += "" + a + "\n";
			}
			else {
				Card a = temp.pop();
				deck.add(a);
				thing += a + "\t";
			}
		}

		return thing;
	}

	// Return and remove the top card
	public Card deal() {
		return deck.pop();
	}

	// Take the top half of the deck (26 cards) and alternate card by card with
	// the bottom half
	public void bridgeShuffle() {
		Stack<Card> top = new Stack<Card>();
		Stack<Card> bottom = new Stack<Card>();
		Stack<Card> sorted = new Stack<Card>();

		int dSize = deck.size();


		for (int i = 0; i < dSize/2; i++) {
			bottom.add(deck.pop());
		}

		for (int i = dSize/2; i < dSize; i++) {
			top.add(deck.pop());
		}

		int bSize = bottom.size();
		int tSize = top.size();

		for (int i = 0; i < (bSize + tSize); i++) {
			if (bSize > tSize || bSize == tSize) {
				if (i % 2 == 0) {
					sorted.add(bottom.pop());
				}
				else {
					sorted.add(top.pop());
				}
			}
			else {
				if (i % 2 == 0) {
					sorted.add(top.pop());
				}
				else {
					sorted.add(bottom.pop());
				}
			}
		}

		deck = sorted;
	}

	// Split the deck at a random spot. Put the stack of cards above the random
	// spot below the other cards
	public void cut() {
		int rand = (int)(Math.random()* deck.size() + 1);
		Stack<Card> top = new Stack<Card>();
		Stack<Card> bottom = new Stack<Card>();
		Stack<Card> sorted = new Stack<Card>();

		int size = deck.size();

		for (int i = 0; i < rand; i++) {
			top.add(deck.pop());
		}

		for (int i = rand; i < size; i++) {
			bottom.add(deck.pop());
		}

		for (int i = 0; i < rand; i++) {
			sorted.add(top.pop());
		}

		for (int i = rand; i < size; i++) {
			sorted.add(bottom.pop());
		}

		deck = sorted;
	}

	// Complete a bridge shuffle and then cut the deck. Repeat 7 times
	public void completeShuffle() {
		for (int i = 0; i < 7; i++) {
			bridgeShuffle();
			cut();
		}
	}

	// Reverse the order of the cards in the deck
	private void reverse() {
		Stack<Card> reversed = new Stack<Card>();
		int size = deck.size();
		for (int i = 0; i < size; i++) {
			reversed.add(deck.pop());
		}

		deck = reversed;
	}

	// Given a Stack of cards as an explicit parameter,
	// reverse the order of the cards in the deck
	private Stack<Card> reverse(Stack<Card> x) {
		Stack<Card> reversed = new Stack<Card>();
		int size = x.size();
		for (int i = 0; i < size; i++) {
			reversed.add(x.pop());
		}

		x = reversed;
		return x;
	}

	// Combine two decks, one as the implicit
	// parameter, the other as the explicit parameter
	public void combineDecks(Stack<Card> other) {
		int size = other.size();
		for (int i = 0; i < size; i++) {
			deck.add(other.pop());
		}
	}

	public static void main(String[] args) {
		// example method calls - you should make your own

		Deck a = new Deck();
		//		System.out.println(a.getDeck());
		//		System.out.println(a);
		//		a.cut();
		//		System.out.println(a);
				a.bridgeShuffle();
				System.out.println(a);
		//		
		//
		//		a.completeShuffle();
		//		System.out.println(a);
		//		System.out.println("After 1 bridge shuffle");
		//		System.out.println(a);
		//		a.reverse();
		//		System.out.println(a);
//				for (int i = 1; i <= 5; i++)
//					System.out.println(a.deal());
		//		a.bridgeShuffle();
		//		System.out.println(a);
//		a.reverse();
//		System.out.println(a);
	}
}
