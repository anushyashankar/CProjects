

public class TeamMapRunner {

	public static void main(String[] args) {
		
		TeamMap a = new TeamMap();
		Wrestler bob = new Wrestler("Bob", 102);
		a.addWrestler(bob);
		a.addWrestler(new Wrestler("Fred", 112));
		a.addWrestler(new Wrestler("Jake", 157));
		a.addWrestler(new Wrestler("Arnold", 199));
		a.addWrestler(new Wrestler("Stan", 119));
		Wrestler juan = new Wrestler("Juan", 180);
		a.addWrestler(juan);
		System.out.println(a);
		
		System.out.println("Attempt to move Juan to Weight Group 7...");
		a.changeWeightGroup(juan, 7);
		System.out.println("Juan is in Weight Group " + a.getWeightGroup(juan));
		System.out.println("\nAttempt to move Bob to Weight Group 2...");
		a.changeWeightGroup(bob, 2);
		System.out.println("Bob is in Weight Group " + a.getWeightGroup(bob));
		System.out.println();
		System.out.println("Juan trimmed down by 8 pounds to compete in a lower group");
		a.changeWeight(juan, 172);
		System.out.println(a);


	}

}
