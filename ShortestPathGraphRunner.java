
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class ShortestPathGraphRunner
{
	public static void main( String[] args ) throws IOException
	{
		Scanner file = new Scanner(new File("src//graph2.dat"));
		int numTimes = file.nextInt();
		file.nextLine();
		
		for (int i = 0; i < numTimes; i++) {
			ShortestPathGraph graph = new ShortestPathGraph(file.nextLine());
			String check = file.nextLine();
			graph.check(check.substring(0, 1), check.substring(1), "", 1);
			System.out.println(check.substring(0, 1) + " connects to " + check.substring(1) + " == " + graph.toString());
		}
	}
}