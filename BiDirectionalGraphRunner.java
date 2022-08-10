
import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;
import static java.lang.System.*;

public class BiDirectionalGraphRunner
{
	public static void main( String[] args ) throws IOException
	{
		Scanner file = new Scanner(new File("src//bidgraph.dat"));
		int numTimes = file.nextInt();
		file.nextLine();
		
		for (int i = 0; i < numTimes; i++) {
			BiDirectionalGraph graph = new BiDirectionalGraph(file.nextLine());
			String[] check = file.nextLine().split(" ");
			graph.check(check[0], check[1], new TreeSet<String>());
			System.out.println(check[0] + graph.toString() + check[1]);
		}
	}
}