
public class HistoList
{
	private ListNode front;

	public HistoList( )
	{
		front = null;
	}


	//ADDS NEW NODE TO THE FRONT OF THE LIST FOR LET IF IT DOES NOT EXIST.
	//IF IT EXISTS, IT BUMPS UP LET'S COUNT BY ONE
	public void add(Object obj)
	{
		//		//ListNode temp = this.front;
		//		ThingCount other = new ThingCount(obj, 1);
		//		ListNode one = new ListNode(other, null);
		//		if (front == null) {
		//			front = one;
		//			return;
		//		}
		//		else {
		//			ListNode temp = front;
		//			while (temp != null) {
		//				if (((ThingCount) (temp.getValue())).getThing().equals(other.getThing())) {
		//					other.setCount(other.getCount() + 1);
		//					return;
		//				}
		//				temp = temp.getNext();
		//			}
		//		}
		//		
		boolean found=false;
		ThingCount other = new ThingCount(obj, 1);
		ListNode one = new ListNode(other, null);
		if (front == null) {
			front = one;

		}
		else if (obj.getClass()!=(((ThingCount) (front.getValue())).getThing()).getClass())
			throw new TypeMismatchException ("can't compare different data types");

		else {
			ListNode temp = front;
			while (temp != null && !found) {
				if(((ThingCount)(temp.getValue())).getThing().equals(obj)) {
					other.setCount(((ThingCount) temp.getValue()).getCount()+1);
					temp.setValue(other);
					found=true;
				}
				temp = temp.getNext();
			}
			if(!found) {
				one.setNext(front);
				front=one;
			}
		}
	}


	//RETURNS THE LIST AS A BIG STRING
	public String toString()
	{
		ListNode temp = front;
		String output = "";
		while (temp != null) {
			output += ((ThingCount)temp.getValue()).getThing() + "-" + ((ThingCount)temp.getValue()).getCount();
			temp = temp.getNext();
			output += "  ";
		}
		return output;
	}
}