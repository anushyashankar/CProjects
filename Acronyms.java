import java.util.*;

public class Acronyms
{
	private Map<String,String> acronymTable;

	public Acronyms()
	{
		acronymTable = new HashMap();
	}

	public void putEntry(String entry)
	{
		String acr = entry.substring(0, 3); //entry.indexOf(" ")
		String abbrev = entry.substring(entry.indexOf(" ") + 3);
		acronymTable.put(acr, abbrev);
	}

	public String convert(String sent)
	{
		Scanner chop = new Scanner(sent);
		String output ="";
		
		while (chop.hasNext()) {
			String temp = chop.next();
			if (acronymTable.containsKey(temp)) {
				output += " " + acronymTable.get(temp);
			}
			else {
				output += " " + temp;
			}
		}
		return output;
	}

	public String toString()
	{
		return acronymTable.toString();
	}
}