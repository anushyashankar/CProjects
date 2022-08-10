
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class RelativesRunner
{
	public static void main( String args[] ) throws IOException
	{
		Relatives thing = new Relatives();
		String as = "12\nJim Sally\nFred Alice\nJim Tom\nJim Tammy\nFred";
		thing.setPersonRelative(as);
	}
}