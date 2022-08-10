
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.*;

public class UniquesDupes
{
	public static Set<String> getUniques(String input)
	{
		Set<String> uniques = new TreeSet<String>();

		//add code
		String[] words = input.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			uniques.add(words[i]);
		}
		
		
		return uniques;
	}

	public static Set<String> getDupes(String input)
	{
		//add code
		
		Set<String> uniques = getUniques(input);
		String[] words = input.split(" ");
		Set<String> repeat = new TreeSet<String>();
		
		for (int i = 0; i < words.length; i++)
			if (!uniques.remove(words[i]))
				repeat.add(words[i]);
		
		return repeat;
		
//		while(uniques.size() != 0) {
//			String temp = uniques.remove();
//		}
		
		
	}
}