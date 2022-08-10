import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class AcroRunner
{
	public static void main( String args[] ) throws IOException
	{
		Acronyms one = new Acronyms();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("How many lines? ");
		int num = scan.nextInt();
		
		for (int i = 0; i < 1; i++) {
			System.out.println("Enter abbrev: ");
			String temp = scan.next();
			temp += scan.nextLine();
			one.putEntry(temp);
		}
		
		System.out.println("Enter paragraph: ");
		String para = scan.nextLine();
		para += scan.nextLine();
		System.out.println(one.convert(para));
		
		System.out.println(one.toString());
	}
}