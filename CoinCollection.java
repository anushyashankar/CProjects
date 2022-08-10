
public class CoinCollection {
	private Coin coincollection;
	
	public CoinCollection() {
		coincollection = new Coin();
	}
	
	public CoinCollection(Coin coincollection) {
		this.coincollection = coincollection;
	}

	public Coin getCoincollection() {
		return coincollection;
	}

	public boolean equals(CoinCollection thing) {
		if (coincollection.getCoins().size() != thing.coincollection.getCoins().size()) {
			System.out.println("here");
			return false;
		}

		for (int i = 0; i < coincollection.getCoins().size(); i++) {
			int count = 0;
			for (int j = 0; j < thing.coincollection.getCoins().size(); j++) {
				if (coincollection.getCoins().getList().get(i).equals(thing.coincollection.getCoins().getList().get(j))) {
					count++;
				}
			}
			if (count == 0) return false;
		}
		
		return true;
	}

	public boolean add(String add) {
		for (int i = 0; i < coincollection.getCoins().size(); i++) {
			if  (coincollection.equals(coincollection.getCoins().getList().get(i), add))
				return false;
		}
		coincollection.getCoins().add(add);
		return true;
	}

	public String toString() {
		String thing = "";

		for (int i = 0; i < coincollection.getCoins().size(); i++) {
			thing += "[" + coincollection.getCoins().getList().get(i) + "]\n";
		}

		return thing;
	}

	public static void main(String[] args) {
		CoinCollection one = new CoinCollection();
		one.add("1941, penny");
		one.add("2010, dime");
		one.add("2009, quarter");
		one.add("1945, penny");
		one.add("1952, nickel");

		System.out.println(one);
		
		CoinCollection two = new CoinCollection();
		two.add("1941, penny");
		two.add("2010, dime");
		two.add("2009, quarter");
		two.add("1945, penny");
		two.add("1952, nickel");
		System.out.println(one.equals(two));
	}

}
