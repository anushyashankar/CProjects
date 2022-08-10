
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class SpanishToEnglish
{
	private Map<String,String> pairs;

	public SpanishToEnglish()
	{
		pairs = new HashMap();
	}

	public void putEntry(String entry)
	{
		String[] list = entry.split(" ");
		
		pairs.put(list[0], list[1]);

	}

	public String translate(String sent)
	{
		Scanner chop = new Scanner(sent);
		String output ="";

		while (chop.hasNext()) {
			output += pairs.get(chop.next()) + " ";
		}

		return output;
	}

	public String toString()
	{
		return pairs.toString().replaceAll("\\,","\n");
	}
}