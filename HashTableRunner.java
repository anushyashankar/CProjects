import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import static java.lang.System.*;

public class HashTableRunner
{
	public static void main ( String[] args )
	{
		try{
			//initialize a new hashtable			
			HashTable thing = new HashTable();
			//read from the dat file using a scanner object	
			//hint: the first number in the file tells you how many integers in your numbers.dat file		
			Scanner file = new Scanner(new File("words.dat"));
			while (file.hasNextLine())
			{
				String temp = file.next();

				Word n = new Word(temp);
				thing.add(n);
			}
			//load stuff into the table using your add method			

			//print out the table
			System.out.println(thing);
		}
		catch(Exception e)
		{
			System.out.println("Houston, we have a problem!");
		}
	}
}