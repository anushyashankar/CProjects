
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
import static java.lang.System.*;

public class BiDirectionalGraph
{
	private TreeMap<String, TreeSet<String>> map;
	private boolean yayOrNay;

	public BiDirectionalGraph(String line)
	{
		map = new TreeMap<String, TreeSet<String>>();
		String[] links = line.split(" ");
		for (int i = 0; i < links.length; i+= 2) {
			String str1 = links[i];
			String str2 = links[i + 1];
			
			if (map.containsKey(str1))
				map.get(str1).add(str2);
			else {
				TreeSet<String> related = new TreeSet<String>();
				related.add(str2);
				map.put(str1,  related);
			}
			
			if (map.containsKey(str2))
				map.get(str2).add(str1);
			else {
				TreeSet<String> related = new TreeSet<String>();
				related.add(str1);
				map.put(str2,  related);
			}
		}
	}

	public boolean contains(String name)
	{
		return map.containsKey(name);
	}

	public void check(String first, String second, TreeSet<String> placesUsed)
	{
		TreeSet<String> links = map.get(first);
		if (links.contains(second)) {
			yayOrNay = true;
			return;
		}
		else {
			for (String one : links) {
				if (!placesUsed.contains(one)) {
					placesUsed.add(one);
					check(one, second, placesUsed);
				}
			}
		}
	}

	public String toString()
	{
		return (yayOrNay ? " connects to " : " has no connection to ");
	}
}