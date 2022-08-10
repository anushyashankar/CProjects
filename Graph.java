import java.util.Map;
import java.util.TreeMap;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Graph
{
	private TreeMap<String, String> map;
	private boolean found;

	public Graph(String line)
	{
		map = new TreeMap<String, String>();
		String[] links = line.split(" ");
		for (String one :links) {
			if (map.containsKey(one.substring(0, 1)))
				map.put(one.substring(0, 1), map.get(one.substring(0, 1) + one.substring(1)));
			else
				map.put(one.substring(0, 1), one.substring(0, 1));
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

	public void check(String first, String second, String beenThere)
	{
		String links = map.get(first);
		if(links.contains(second)) {
			found = true;
			return;
		}
		else {
			
			for(int i = 0; i < links.length(); i++) {
				if(!beenThere.contains(links.substring(i, i + 1))) {
					beenThere += links.substring(i, i + 1);
					check(links.substring(i, i + 1), second, beenThere);
				}
			}
		}
	}

	public String toString()
	{
		return (found ? "yes" : "no");
	}


	public static void main( String[] args ) throws IOException
	{
		Scanner file = new Scanner(new File("graph1.dat"));
		int howManyTimes = file.nextInt();
		file.nextLine();
		for(int x=0; x<howManyTimes; x++)
		{
			Graph graph = new Graph(file.nextLine());
			String check = file.nextLine();
			graph.check(check.substring(0, 1), check.substring(1), "");
			System.out.println(check.substring(0, 1) + " connects to" + check.substring(1) + " == " + graph.toString());
		}
	}



}