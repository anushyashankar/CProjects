/**
 * Represents an item stored in a warehouse. An item has
 * a name, count, and a price.
 */
public class Item
{
	private String name;
	private int count;
	private double price;
	/**
	 * When an item is created. It is the initial occurrence
	 * The count is set to 1.
	 */
	public Item(String theName, double thePrice)
	{
		name = theName;
		price = thePrice;
		count = 1;
	}
	/**
	 * An items unique barcode is created by adding up the digits of the price
	 * That sum is multiplied by the number of characters in the name
	 * that product is multiplied by 31
	 * for example Item (hat 1.99) (1+9+9) * 3 * 31 ==&gt; 1767
	 */
	
	public boolean equals(Object o) {
		Item other = (Item) o;
		if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.price != other.price) {
			return false;
		}
		return true;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getBarCode() { //29.99 9.99
		int barcode = 0;
		
		double priceTemp = price;
		
		double digit = 0;
		
		int sum = 0;
		
		int count = 0;
		
//		while (priceTemp != 0) {
//			if (priceTemp < 1) {
//				if (count == 1) break;
//				priceTemp *= 100;
//				priceTemp = (int) priceTemp;
//				count ++;
//			}
//			System.out.println(priceTemp);
//			sum += priceTemp % 10;
//			System.out.println(sum);
//			priceTemp = priceTemp / 10;
//			System.out.println(priceTemp);
//		}
//		
//		System.out.println("sum: " + sum);
//		
		String s = "" + price;
		for (int i = 0; i < s.length(); i++) {
			if (!s.substring(i, i+1).equals("."))
				sum += Integer.parseInt(s.substring(i, i+1));
		}
		
		barcode = sum * name.length();
		
		//System.out.println("sum*name length: " + barcode);
		
		barcode *= 31;
		
		//System.out.println("barcode: " + barcode);
		
		return barcode;
	}
}