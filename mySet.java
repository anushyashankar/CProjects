import java.util.*;

public class mySet {
	private ArrayList<Object> list ;

	public mySet() {
		list = new ArrayList<Object>();
	}
	
	public mySet(ArrayList<Object> list) {
		this.list = list;
	}

	public ArrayList<Object> getList() {
		return list;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}
	
	public boolean add(Object x) {
		for (int i = 0; i < list.size(); i++)
			if (x.equals(list.get(i)))
				return false;
		
		list.add(x);
		return true;
	}
	
	public boolean remove(Object x) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).equals(x)) {
				list.remove(i);
				return true;
			}
		
		return false;
	}
	
	public void clear() {
		list = null;
	}
	
	public int size() {
		return list.size();
	}
	
	public String toString() {
		return list.toString();
	}
}
