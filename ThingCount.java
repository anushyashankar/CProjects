
public class ThingCount implements Comparable
{
	private int count;
	private Object thing;
	
	public ThingCount()
	{
		thing = null;
		count = 0;
	}
	
	public ThingCount(Object thang, int cnt)
	{
		this.thing = thang;
		this.count = cnt;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int cnt)
	{
		count = cnt;
	}

	public void setThing(Object obj)
	{
		thing = obj;
	}	
	
	public Object getThing()
	{
		return thing;
	}
	
	public boolean equals(Object obj)
	{
		ThingCount other = (ThingCount)obj;
		if (this.thing.equals(other.thing))
			return true;
		return false;
	}
	
	public int compareTo(Object obj)
	{
		ThingCount other = (ThingCount)obj;		
		return ((ThingCount) this.thing).compareTo((ThingCount) other.thing);
	}
	
	public String toString()
	{
		return ""+ getThing() + " - " + getCount();
	}
}