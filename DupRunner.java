
import static java.lang.System.*;
import java.util.*;

public class DupRunner
{
	public static void main( String args[] )
	{
		System.out.println(UniquesDupes.getUniques("a b c d a a"));
		System.out.println(UniquesDupes.getDupes("a b c d a a"));
	}
}