
public class Coin {
	private mySet coins;
	
	public Coin() {
		coins = new mySet();
	}

	public Coin(mySet coins) {
		this.coins = coins;
	}
	
	public mySet getCoins() {
		return coins;
	}
	
	public static String getDenom(String list) {
		return list.substring(list.indexOf(" "));
	}

	public static String getYear(String list) {
		return list.substring(0, list.indexOf(","));
	}
	
	public static boolean equals(Object add1, Object add2) {

		if (getDenom((String) add1).equals(getDenom((String) add2))) { //same denom.
			if (getYear((String) add1).substring(0,3).equals(getYear((String) add2).substring(0,3))) { //same year
				//same coin
				return true;
			}
		}
		return false;
	}
}
