
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;
import java.util.*;

public class OddEvenSets
{
	private Set<Integer> odds;
	private Set<Integer> evens;

	public OddEvenSets()
	{
		odds = new TreeSet<Integer>();
		evens = new TreeSet<Integer>();
	}

	public OddEvenSets(String line)
	{
		odds = new TreeSet<Integer>();
		evens = new TreeSet<Integer>();
		
		//ArrayList<String> strings = new ArrayList<String>();
		String[] words = line.split(" ");
		ArrayList<Integer> parsed = new ArrayList<Integer>();
		
		for (int i = 0; i < words.length; i++) {
			parsed.add(Integer.parseInt(words[i]));
		}
		
		for (int i = 0; i < parsed.size(); i++) {
			if (parsed.get(i) % 2 == 0)
				evens.add(parsed.get(i));
			else
				odds.add(parsed.get(i));
		}
	}

	public String toString()
	{
		return "ODDS : " + odds + "\nEVENS : " + evens + "\n\n";
	}
}