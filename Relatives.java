import java.util.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Scanner;
import static java.lang.System.*;

public class Relatives
{
	private Map<String,Set<String>> map;

	public Relatives()
	{
		map = new HashMap();
	}

	public void setPersonRelative(String line)
	{
		String[] personRelative = line.split("\n");

		for (int i = 0; i < personRelative.length; i++) {
			if (i == personRelative.length - 1)
				System.out.println(this.getRelatives(personRelative[i]));

			if (personRelative[i].contains(" ")) {
				String p3 = personRelative[i].substring(0, personRelative[i].indexOf(" "));
				if (!map.containsKey(p3)) {
					if (personRelative[i].contains(" ")) { //excludes number at the beginning
						String p1 = personRelative[i].substring(0, personRelative[i].indexOf(" "));
						String p2 = personRelative[i].substring(personRelative[i].indexOf(" ") + 1);

						Set<String> thing = new TreeSet<String>();
						thing.add(p2);
						map.put(p1, thing);

						if (!map.containsKey(p2)) {
							Set<String> temp = new TreeSet<String>();
							thing.add(p1);
							map.put(p2, temp);
						}
						else {
							map.get(p2).add(p1);
						}
					}
				}
				else {
					String p1 = personRelative[i].substring(0, personRelative[i].indexOf(" "));
					String p2 = personRelative[i].substring(personRelative[i].indexOf(" ") + 1);
					map.get(p1).add(p2);
				}
			}

		}
	}


	public String getRelatives(String person)
	{
		if (map.containsKey(person)) {
			return map.get(person).toString();
		}
		return "key does not exist";
	}


	public String toString()
	{
		String output="";

		Iterator<String> it = map.keySet().iterator();

		while (it.hasNext()) {
			String temp = it.next();
			output += temp + "has relations with " + map.get(temp);
		}

		return output;
	}
}