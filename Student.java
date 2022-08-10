import java.util.*;

public class Student implements Comparable{
	private int id;
	private String fName;
	private String lName;
	public Student()
	{
		id = 0;
		fName = "";
		lName = "";
	}
	
	public Student(int i, String fn, String ln)
	{
		id = i;
		fName = fn;
		lName = ln;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
	
		this.id = id;
	}

	public String getfName()
	{
		return fName;
	}

	public void setfName(String fName)
	{
		this.fName = fName;
	}

	public String getlName()
	{
		return lName;
	}

	public void setlName(String lName)
	{
		this.lName = lName;
	}
	
	public String toString()
	{
		return lName + ", " + fName + ", " + id;
	}
	
	public int compareTo(Object two)
	{
		Student thing = (Student)two;
		if(this.getlName().compareTo(thing.getlName()) > 0)
		{
			return 1;
		}
		else if(this.getlName().compareTo(thing.getlName()) < 0)
		{
			return -1;
		}
		else if(this.getfName().compareTo(thing.getfName()) > 0)
		{
			return 1;
		}
		else if(this.getfName().compareTo(thing.getfName()) < 0)
		{
			return -1;
		}
		else if(this.getId() > thing.getId())
		{
			return 1;
		}
		else if(this.getId() < thing.getId())
		{
			return -1;
		}
		
		return 0;
	}
}
