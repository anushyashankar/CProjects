

import java.util.HashMap;
import java.util.Set;

public class TeamMap {

	private HashMap<Integer, TeamSet> wrestMap;

	public TeamMap() {
		wrestMap = new HashMap<Integer, TeamSet>();
		for (int i = 0; i < 10; i++) {
			wrestMap.put(i,  new TeamSet());
		}
	}

	public int getWeightGroup(Wrestler w) {
		int hash = -1;
		for (int i = 0; i < 10; i++ ) {
			if (wrestMap.get(i).getWrestSet().contains(w)) {
				hash = i;
				break;
			}
		}

		if (hash == -1 ) {
			String temp = "" + w.getWeight();
			temp = temp.substring(1, 2);
			return Integer.parseInt(temp);
		}
		return hash;
	}

	public int hashcode(Wrestler w) {
		String temp = "" + w.getWeight();
		temp = temp.substring(1, 2);
		return Integer.parseInt(temp);
	}
	
	public void addWrestler(Wrestler w) {
		int hash = this.getWeightGroup(w);
		wrestMap.get(hash).addWrestler(w);
	}
	
	public void removeWrestler(Wrestler w) {
		if (!wrestMap.containsKey(w.getWeight())) {
			System.out.println("This wrestler does not exist");
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			if (wrestMap.get(i).getWrestSet().contains(w)) {
				wrestMap.get(i).getWrestSet().remove(w);
				return;
			}
		}
	}

	public void changeWeight(Wrestler w, int weight) {
		for (int i = 0; i < 10; i++) {
			if (wrestMap.get(i).getWrestSet().contains(w)) {
				wrestMap.get(i).getWrestSet().remove(w);
				w.setWeight(weight);
				wrestMap.get(i).getWrestSet().add(w);
				
				this.changeWeightGroup(w,  this.hashcode(w));
				return;
			}
		}
		System.out.println(w + " does not exist");
	}

	public void changeWeightGroup(Wrestler w, int group) {
		int x = 0;
		int hash = 0;
		for (int i = 0; i < 10; i++) {
			if (wrestMap.get(i).getWrestSet().contains(w)) {
				x++;
				hash = i;
			}
		}
		if (x != 1) {
			System.out.println("wrestler does not exist!");
			return;
		}

		if (hash < group) { 
			wrestMap.get(hash).getWrestSet().remove(w);
			wrestMap.get(group).getWrestSet().add(w);
			return;
		}
		if (hash > group) {
			String temp = "" + w.getWeight();
			temp = temp.substring(1, 2);
			int thin = Integer.parseInt(temp);
			
			if (thin == group) { //lost weight
				wrestMap.get(hash).getWrestSet().remove(w);
				wrestMap.get(group).getWrestSet().add(w);
				return;
			}
		}

		System.out.println(w.getName() + " cannot go to that group, he must lose weight first");
	}

	public String toString() {
		String thing = "";
		for (int i = 0; i < 10; i++) {
			thing += "Group " + i + ": ";
			thing += wrestMap.get(i).getWrestSet().toString() + "\n";
		}
		return thing;
	}

}
