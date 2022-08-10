
public class Part implements Comparable<Part>
{
	private String make, model, rest;
	private int year;

	public Part(String line) 
	{
		if (line.length() > 1) {
			String[] list = line.split(" ");
			String number = "1234567890";
			int count = 0;
			rest = "";

			for(int i = 0; i<list.length; i++)
			{
				if(list[i].contains("1") || list[i].contains("2") || list[i].contains("3") ||list[i].contains("4") ||list[i].contains("5") ||list[i].contains("6") ||list[i].contains("7") ||list[i].contains("8") ||list[i].contains("9") ||list[i].contains("0") )
				{
					count = i;
					break;
				}

				rest += list[i] + " ";
			}

			rest += list[count];
			make = list[count+1];
			model = list[count+2];

			year = Integer.parseInt(list[list.length-1]);
		}
	}

	//have to have compareTo if implements Comparable
	public int compareTo( Part rhs )
	{
		if (this.make.compareTo(rhs.make) < 0)
			return -1;
		else if (this.make.compareTo(rhs.make) > 0)
			return 1;
		else if (this.model.compareTo(rhs.model) < 0)
			return -1;
		else if (this.model.compareTo(rhs.model) > 0)
			return 1;
		else if (this.year < rhs.year)
			return -1;
		else if (this.year > rhs.year)
			return 1;

		return 0;
	}

	public String toString()
	{
		return make + " " + model + " " + year + " " + rest;
	}
}