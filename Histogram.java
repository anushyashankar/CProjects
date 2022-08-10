import java.util.*;

public class Histogram
{
	private Map<String,Integer> histogram;

	public Histogram()
	{
		histogram = new HashMap();
	}

	public Histogram(String sent)
	{
		histogram = new HashMap();
		String[] thing = sent.split(" ");
		for (int i = 0; i < thing.length; i++) {
			if (histogram.containsKey(thing[i]))
				histogram.replace(thing[i], histogram.get(thing[i]) + 1);
			else
				histogram.put(thing[i], 1);
		}
	}
	
	public void setSentence(String sent)
	{
		String[] thing = sent.split(" ");
		for (int i = 0; i < thing.length; i++) {
			if (histogram.containsKey(thing[i]))
				histogram.replace(thing[i], histogram.get(thing[i]) + 1);
			else
				histogram.put(thing[i], 1);
		}
	}

	public String toString()
	{
		String output="";
		
		Iterator<String> it = histogram.keySet().iterator();
		while (it.hasNext()) {
			String c = it.next();
			String temp = "";
			
			for (int i = 0; i < histogram.get(c); i++) {
				temp += "*";
			}
			output += c + "\t" + temp + "\n";
		}
		
		return output;
		
		//String allStars="";
		
		//return histogram.toString();
		
		//return output+"\n\n";
	}
}