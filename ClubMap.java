import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class ClubMap {
	private HashMap<String, Set> clublist;

	public ClubMap() {
		clublist = new HashMap<String, Set>();
		clublist.put("Math", new HashSet<Student>());
		clublist.put("IPLE", new HashSet<Student>());
		clublist.put("Art", new HashSet<Student>());
		clublist.put("Orchestra", new HashSet<Student>());
		clublist.put("Programming", new HashSet<Student>());
		clublist.put("Band", new HashSet<Student>());
		clublist.put("Biology", new HashSet<Student>());
		clublist.put("History", new HashSet<Student>());
		clublist.put("RedCross", new HashSet<Student>());
		clublist.put("French", new HashSet<Student>());
		clublist.put("Drama", new HashSet<Student>());
		clublist.put("Latin", new HashSet<Student>());
		clublist.put("ModelUN", new HashSet<Student>());
		clublist.put("Spanish", new HashSet<Student>());
		clublist.put("ColorGuard", new HashSet<Student>());
	}

	public void addStudents() {
		try {
			Scanner file = new Scanner("student.txt");
			int count = 0;
			while (file.hasNextLine()) {
				String temp = file.next();
				count++;
			}

			file = new Scanner(new File("student.txt"));
			String[] words = new String[count];

			int counter = 0;
			while (file.hasNextLine()) {
				String temp = file.next();
				words[counter] = temp;
				counter++;
			}
			String f = "", l = "", ID = "", club = "";

			for (int i = 0; i < words.length; i++) {
				if (i % 4 == 0)
					l = words[i];
				if (i % 4 == 1)
					f = words[i];
				if (i % 4 == 2)
					ID = words[i];
				if (i % 4 == 3) {
					club = words[i];
					Student one = new Student(f, l, ID);
					clublist.get(club).add(one);
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

	public void getMath() {
		System.out.println("Math: " + clublist.get("Math"));
	}
	
	public String toString() {
		String output = " ";
		
		output+="Math: " + clublist.get("Math") + "\n";
		output+="IPLE: " + clublist.get("IPLE") + "\n";
		output+="Art: " + clublist.get("Orchestra") + "\n";
		output+="Programming: " + clublist.get("Programming") + "\n";
		output+="Band: " + clublist.get("Band") + "\n";
		output+="Biology: " + clublist.get("Biology") + "\n";
		output+="History: " + clublist.get("History") + "\n";
		output += "Red Cross: " + clublist.get("RedCross") + "\n";
		output += "French: " + clublist.get("French") + "\n";
		output += "Drama: " + clublist.get("Drama") + "\n";
		output += "Latin: " + clublist.get("Latin") + "\n";
		output += "Model UN: " + clublist.get("ModelUN") + "\n";
		output += "Spanish: " + clublist.get("Spanish") + "\n";
		output += "Color Guard: " + clublist.get("ColorGuard") + "\n";
		
		return output;
	}


}
