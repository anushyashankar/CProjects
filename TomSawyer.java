import java.util.*;

import static java.lang.System.out;

import java.io.*;

public class TomSawyer {
	
	private Map<String, Integer> words;
	
	public TomSawyer() {
		words = new HashMap<String, Integer>();
		
		try
		{
			Scanner file = new Scanner(new File("tomSawyer.txt"));
			
			String text = "";
			
			while (file.hasNext()) {
				text += file.next() + " ";
			}
			
			String thing[] = text.split(" ");
			String punct = ",.?!;:-";
			
			for (int i = 0; i < thing.length; i++) {
				String temp = thing[i].toLowerCase();
				
				if (punct.contains(temp.substring(temp.length() - 1))) {
					temp = temp.substring(0, temp.length() - 1);
				}
				
				if (!words.containsKey(temp)) {
					words.put(temp, 1);
				}
				else {
					int num = words.get(temp);
					words.remove(temp);
					words.put(temp, num + 1);
				}
			}

		}
		catch( IOException e )  //Most specific exceptions must be listed 1st
		{
			out.println(e);
		}
		catch( RuntimeException e )
		{
			out.println(e);
		}
		catch( Exception e )
		{
			out.println(e);
		}
		finally
		{
			//no code needed here
		}
	}
	
	public Set once() {
		Set<String> one = new HashSet<String>();
		
		Iterator iter = words.keySet().iterator();
		
		while (iter.hasNext()) {
			String temp = (String) iter.next();
			if (words.get(temp) == 1) {
				one.add(temp);
			}
		}
		
		return one;
	}
	
	public Set ten() {
		Set<String> one = new HashSet<String>();
		
		Iterator iter = words.keySet().iterator();
		
		while (iter.hasNext()) {
			String temp = (String) iter.next();
			if (words.get(temp) > 10) {
				one.add(temp);
			}
		}
		
		return one;
	}
	
	public String toString() {
		return words.toString();
	}
	
	public static void main(String[] args) {
		TomSawyer one = new TomSawyer();
		System.out.println(one);
		
		System.out.println(one.once());
		System.out.println(one.ten());
	}
}
