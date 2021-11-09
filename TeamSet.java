

import java.util.HashSet;

public class TeamSet {
	private HashSet<Wrestler> wrestSet;
	public TeamSet() {
		wrestSet = new HashSet<Wrestler>();
	}
	public TeamSet(Wrestler w) {
		wrestSet = new HashSet<Wrestler>();
		if (!wrestSet.contains(w))
			wrestSet.add(w);
		else
			System.out.println("cannot add -- already exists");
	}
	
	public double avgWeight() {
		Object[] thing = wrestSet.toArray();
		int count = 0;
		
		for (int i = 0; i < thing.length; i++) {
			Wrestler temp = (Wrestler) thing[i];
			count += temp.getWeight();
		}
		
		return count/thing.length;
	}
	
	public void addWrestler(Wrestler w) {
		if (!wrestSet.contains(w))
			wrestSet.add(w);
		else
			System.out.println("cannot add -- already exists");
	}
	
	public HashSet<Wrestler> lightweight() {
		HashSet<Wrestler> light = new HashSet<Wrestler>();
		Object[] thing = wrestSet.toArray();
		
		for (int i = 0; i < thing.length; i++) {
			Wrestler temp = (Wrestler) thing[i];
			if (temp.getWeight() < 120)
				light.add(temp);
		}
		return light;
	}
	
	public HashSet<Wrestler> getWrestSet() {
		return wrestSet;
	}
	
	public String toString() {
		return wrestSet.toString();
	}

}
