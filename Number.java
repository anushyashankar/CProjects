
public class Number
{
	private int theValue;
	
	public Number(int value)
	{
	//complete your constructor
		theValue = value;
	}	
	
	public int getValue()
	{
		return theValue;
	}
	
	public boolean equals(Object obj)
	{
	//number objects are equal if they contain equal values
	//and remember, your parameter is an object, so casting is necessary before comparison.
		int temp = (int)obj;
		if (temp == theValue)
			return true;
		return false;
	} 
	
	public int hashCode()
	{
	// based on the output, and specificially on how bucket indices relate to each number in the bucket,
 	// implement a proper hashcode
		return theValue % 10;
	}

	public String toString()
	{
		return "" + theValue;
	}	
}