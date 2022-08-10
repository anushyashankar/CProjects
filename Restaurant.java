
import static java.lang.System.*;

public class Restaurant
{
	
	ListNode list;
	ListNode temp;
	
	public Restaurant(ListNode one) {
		list = one;
		temp = one;
	}
	
//	//this method will print the entire list on the screen
//		public static void print(ListNode list) {
//			while (list != null) {
//				System.out.println(list.getValue() + " ");
//				list = list.getNext();
//			}
//		}		
//
//		//this method will return the number of nodes present in list
//		public static int nodeCount(ListNode list) {
//			int count = 0;
//
//			while (list != null && list.getNext() != null) {
//				count++;
//				list = list.getNext();
//			}
//
//			return count;
//		}
//
//		//this method will create a new node with the same value as the first node and add this
//		//new node to the list.  Once finished, the first node will occur twice.
//		public static void doubleFirst(ListNode list) {
//			ListNode add = new ListNode(list.getValue(), list.getNext());
//			list.setNext(add);
//		}
//
//		//this method will create a new node with the same value as the last node and add this
//		//new node at the end.  Once finished, the last node will occur twice.
//		public void doubleLast(ListNode list) {
//			while (list.getNext() != null)
//				list = list.getNext();
//			ListNode prev = new ListNode(list.getValue(), null);
//			list.setNext(prev);
//		}
//
//		//method skipEveryOther will remove every other node
//		public void skipEveryOther(ListNode list) {
//			while (list.getNext() != null && nodeCount() % 2 != 0) {
//				list.setNext(list.getNext().getNext());
//				list = list.getNext();
//			}
//			while (list != null && nodeCount() % 2 == 0) {
//				list.setNext(list.getNext().getNext());
//				list = list.getNext();
//			}
//		}
//
//		//this method will set the value of every xth node in the list
//		public void setXthNode(ListNode list, int x, Comparable value) {
//			int count = 0;
//			
//			while (list != null && nodeCount() % 2 != 0) {
//				while (list != null && count < x) {
//					list = list.getNext();
//					count++;
//					if (count == x - 1 && list != null)
//						list.setValue(value);
//				}
//				count = 0;
//			}
//			count = 0;
//			
//			while (list != null && nodeCount() % 2 == 0) {
//				while (list.getNext() != null && count < x) {
//					list = list.getNext();
//					count++;
//					if (count == x - 1 && list != null)
//						list.setValue(value);
//				}
//				count = 0;
//			}
//			
//		}	
//
//		//this method will remove every xth node in the list
//		public void removeXthNode(ListNode list, int x) {
//			setXthNode(x, null);
//			
//			while (list.getNext() != null && nodeCount() % 2 != 0) {
//				if (list.getNext().getValue() == null)
//					list.setNext(list.getNext().getNext());
//				list = list.getNext();
//			}
//			
//			while (list != null && nodeCount() % 2 == 0) {
//				if (list.getNext().getValue() == null)
//					list.setNext(list.getNext().getNext());
//				list = list.getNext();
//			}
//			
//		}
	
	//this method will print the entire list on the screen
	public void print()
	{
		list = temp;
		while (list != null) {
			System.out.println(list.getValue() + " ");
			list = list.getNext();
		}
	}		

	//this method will return the number of nodes present in list
	public int nodeCount()
	{
		list = temp;
		int count = 0;

		while (list != null && list.getNext() != null) {
			count++;
			list = list.getNext();
		}

		return count;
	}

	//this method will create a new node with the same value as the first node and add this
	//new node to the list.  Once finished, the first node will occur twice.
	public void doubleFirst()
	{
		list = temp;
		ListNode add = new ListNode(list.getValue(), list.getNext());
		list.setNext(add);
	}

	//this method will create a new node with the same value as the last node and add this
	//new node at the end.  Once finished, the last node will occur twice.
	public void doubleLast()
	{
		list = temp;
		while (list.getNext() != null)
			list = list.getNext();
		ListNode prev = new ListNode(list.getValue(), null);
		list.setNext(prev);
	}

	//method skipEveryOther will remove every other node
	public void skipEveryOther()
	{
		list = temp;
		while (list.getNext() != null && nodeCount() % 2 != 0) {
			list.setNext(list.getNext().getNext());
			list = list.getNext();
		}
		while (list != null && nodeCount() % 2 == 0) {
			list.setNext(list.getNext().getNext());
			list = list.getNext();
		}
	}

	//this method will set the value of every xth node in the list
	public void setXthNode(int x, Comparable value)
	{
		list = temp;
		int count = 0;
		
		while (list != null && nodeCount() % 2 != 0) {
			while (list != null && count < x) {
				list = list.getNext();
				count++;
				if (count == x - 1 && list != null)
					list.setValue(value);
			}
			count = 0;
		}
		count = 0;
		
		while (list != null && nodeCount() % 2 == 0) {
			while (list.getNext() != null && count < x) {
				list = list.getNext();
				count++;
				if (count == x - 1 && list != null)
					list.setValue(value);
			}
			count = 0;
		}
		
	}	

	//this method will remove every xth node in the list
	public void removeXthNode(int x)
	{
		list = temp;
		setXthNode(x, null);
		
		while (list.getNext() != null && nodeCount() % 2 != 0) {
			if (list.getNext().getValue() == null)
				list.setNext(list.getNext().getNext());
			list = list.getNext();
		}
		
		while (list != null && nodeCount() % 2 == 0) {
			if (list.getNext().getValue() == null)
				list.setNext(list.getNext().getNext());
			list = list.getNext();
		}
		
	}		
}