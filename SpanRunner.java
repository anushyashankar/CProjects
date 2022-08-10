
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class SpanRunner
{
	public static void main( String args[] ) throws IOException
	{
		SpanishToEnglish one = new SpanishToEnglish();
		
		one.putEntry("mi my");
		one.putEntry("casa house");
		one.putEntry("es is");
		one.putEntry("bonita beautiful");
		
		System.out.println(one.translate("mi casa es bonita"));
	}
}