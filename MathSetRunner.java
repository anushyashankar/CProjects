
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class MathSetRunner
{
	public static void main(String args[]) throws IOException
	{
		MathSet one = new MathSet("1 2 3 4 5", "4 5 6 7 8");
		
		System.out.println(one.union());
		System.out.println(one.intersection());
		System.out.println(one.differenceAMinusB());
		System.out.println(one.differenceBMinusA());
		System.out.println(one.symmetricDifference());
		System.out.println(one);
	}
}
