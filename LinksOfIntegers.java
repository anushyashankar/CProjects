
import java.util.LinkedList;
import java.util.ListIterator;

public class LinksOfIntegers {
	private LinkedList<Integer> list;

	public LinksOfIntegers() {
		list = new LinkedList<Integer>();
	}

	public LinksOfIntegers(int[] nums) {
		list = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
		}
	}

	public double getSum() {
		ListIterator<Integer> iter = list.listIterator();
		double sum = 0;
		while (iter.hasNext())
			sum += iter.next();
		return sum;
	}

	public double getAvg() {
		ListIterator<Integer> iter = list.listIterator();
		double avg = 0;
		int count = 0;
		
		avg = getSum();
		
		while (iter.hasNext()) {
			count++;
			iter.next();
		}
		return avg/count;
	}

	public int getLargest() {
		ListIterator<Integer> iter = list.listIterator();
		int largest = iter.next();
		int compare = 0;
		while (iter.hasNext()) {
			compare = iter.next();
			if (compare > largest)
				largest = compare;
		}
		return largest;
	}

	public int getSmallest() {
		ListIterator<Integer> iter = list.listIterator();
		int smallest = iter.next();
		int compare = 0;
		while (iter.hasNext()) {
			compare = iter.next();
			if (compare < smallest)
				smallest = compare;
		}
		return smallest;
	}

	public String toString() {
		return "SUM: " + getSum() + "\nAVERAGE: " + getAvg() + "\nLARGEST: " + getLargest() + "\nSMALLEST: " + getSmallest();
	}
}