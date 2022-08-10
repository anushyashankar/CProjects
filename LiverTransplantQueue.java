import java.util.*;

public class LiverTransplantQueue implements PriorityQueue {
	
	private ArrayList<Patient> list;
	
	public LiverTransplantQueue() {
		list = new ArrayList<Patient>();
	}

	public boolean isEmpty() {
		if (list.size() == 0)
			return true;
		return false;
	}

	public void add(Object x) {
		list.add((Patient) x);
	}

	public Object poll() {
		Patient thing = list.get(0);
		int remove = 0;
		
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getPriority() < thing.getPriority()) {
				thing = list.get(i);
				remove = i;
			}
		}
		
		list.remove(remove);
		return thing;
	}

	public Object peek() {
		Patient thing = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getPriority() < thing.getPriority()) {
				thing = list.get(i);
			}
		}
		
		return thing;
	}

	public int size() {
		return list.size();
	}
	
	public String toString() {
		return list.toString();
	}

	public static void main(String[] args)
	{
		LiverTransplantQueue pq= new LiverTransplantQueue();
		pq.add(new Patient("Smith",3));
		pq.add(new Patient("Chen",2));
		pq.add(new Patient("Jones",3));
		pq.add(new Patient("Wong",3));
		pq.add(new Patient("Gupta",2));
		pq.add(new Patient("Garcia",1));
		pq.add(new Patient("Brown",3));
		System.out.println("The list is");
		System.out.println(pq);
		while(pq.size() > 0)
		{
			System.out.println("The next patient for the liver transplant is");
					System.out.println(pq.poll());
		}
	}
}
