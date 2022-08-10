
public class PhoneEntry {
	private String name;
	private String number;
	
	public PhoneEntry() {
		name = "";
		number = "";
	}
	
	public PhoneEntry(String na, String nu) {
		name = na;
		number = nu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString() {
		return name + " " + number;
	}
}
