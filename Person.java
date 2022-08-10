public class Person implements Comparable
{
	private String firstName;
	private String lastName;
	public Person(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String toString()
	{
		return "Name: " + firstName + " " + lastName;
	}

	public int compareTo(Object other) {
		Person o = (Person) other;
		
		// TODO Auto-generated method stub
		if (this.lastName.compareTo(o.getLastName()) < 0)
			return -1;
		if (this.lastName.compareTo(o.getLastName()) > 0)
			return 1;
		if (this.lastName.compareTo(o.getLastName()) == 0) {
			if (this.firstName.compareTo(o.getFirstName()) < 0)
				return -1;
			if (this.firstName.compareTo(o.getFirstName()) > 0)
				return 1;
		}

		return 0;
	}
}