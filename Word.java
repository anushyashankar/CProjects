public class Word
{
	private String theValue;
	
	//write a constructor method
	public Word(String word) {
		theValue = word;
	}

	//write the getValue method
	public String getValue() {
		return theValue;
	}
	
	//write the equals method
	public boolean equals(Word other) {
		if (this.theValue.equalsIgnoreCase(other.getValue()))
			return true;
		return false;
	}
	
	
	//write the hashCode method
	public int hashCode() {
		String vowels = "aeiou";
		int count = 0;
		
		for (int i = 0; i < theValue.length(); i++) {
			if (vowels.contains(theValue.substring(i, i + 1)))
				count++;
		}
		
		return (count * theValue.length())% 10;
	}
	
	
	//write the toString method
	public String toString() {
		return theValue;
	}

	
}