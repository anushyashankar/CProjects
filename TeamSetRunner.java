

public class TeamSetRunner {

	public static void main(String[] args) {
		TeamSet one = new TeamSet();

		one.addWrestler(new Wrestler("Bob", 102));
		one.addWrestler(new Wrestler("Bob", 102));
		one.addWrestler(new Wrestler("Fred", 129));
		one.addWrestler(new Wrestler("Jake", 157));
		one.addWrestler(new Wrestler("Arnold", 199));
		one.addWrestler(new Wrestler("Stan", 119));
		System.out.println("Average weight of all wrestlers: " + one.avgWeight());
		System.out.println("Set of lightweight wrestlers: " + one.lightweight().toString());
		System.out.println("All wrestlers: " + one.toString());
		System.out.println("All wrestlers: " + one.getWrestSet());

	}

}
