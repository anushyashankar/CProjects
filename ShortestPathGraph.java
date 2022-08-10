
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class ShortestPathGraph
{
	private TreeMap<String, String> map;
	private boolean yayOrNay;
	private int shortest;

	public ShortestPathGraph(String line)
	{
		map = new TreeMap<String, String>();
		String[] links = line.split(" ");
		for (String one :links) {
			if (map.containsKey(one.substring(0, 1)))
				map.put(one.substring(0, 1), map.get(one.substring(0, 1) + one.substring(1)));
			else
				map.put(one.substring(0, 1), one.substring(1));
			if (map.containsKey(one.substring(1)))
				map.put(one.substring(1), map.get(one.substring(1)) + one.substring(0, 1));
			else
				map.put(one.substring(1), one.substring(0, 1));
		}
	}

	public boolean contains(String letter)
	{
		return map.containsKey(letter);
	}

	public void check(String first, String second, String placesUsed, int steps)
	{
		String links = map.get(first);
		if(links.contains(second)) {
			yayOrNay = true;
			shortest = Math.min(shortest, steps);
			return;
		}
		else {
			
			for(int i = 0; i < links.length(); i++) {
				if(!placesUsed.contains(links.substring(i, i + 1))) {
					placesUsed += links.substring(i, i + 1);
					check(links.substring(i, i + 1), second, placesUsed, steps + 1);
				}
			}
		}
	}

	public String toString()
	{
		return (yayOrNay ? "yes in " + shortest + " steps" : "no");
	}
}