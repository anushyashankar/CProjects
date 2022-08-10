
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class PartList
{
	private TreeMap<Part, Integer> partsMap;
	
	public PartList()
	{
		partsMap = new TreeMap();

	}
	
	public PartList(String fileName)
	{
		this();
		try
		{
			Scanner file = new Scanner(new File("partinfo.dat"));
			//add code here to read from the file 
			//and add Parts to the map

			while (file.hasNextLine()) {
				String line = file.nextLine();
				
				if (line.length() > 1) {
					Part temp = new Part(line);
					if (partsMap.get(line) == null) {
						partsMap.put(temp, 1);
					}
					else {
						int count = partsMap.get(temp);
						partsMap.remove(temp);
						partsMap.put(temp, count + 1);
					}	
				}
			}

//			Iterator<Part> it = partsMap.keySet().iterator();
//			
//			while (it.hasNext()) {
//				Part temp = it.next();
//				Iterator<Part> iter = partsMap.keySet().iterator();
//				System.out.println(temp);
//				
//				while (iter.hasNext()) {
//					Part temp2 = iter.next();
//					if (temp.compareTo(temp2) == 0) {
//						partsMap.remove(temp2);
//						int count = partsMap.get(temp);
//						partsMap.remove(temp);
//						partsMap.put(temp2,  count + 1);
//					}
//				}
//				
//				iter = partsMap.keySet().iterator();
//			}
			
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
	
	public String toString()
	{
		String output="";

		Iterator<Part> it = partsMap.keySet().iterator();
		
		while (it.hasNext()) {
			Part temp = it.next();
			output += temp + " " +  partsMap.get(temp) + "\n";
		}

		return output;
	}
}