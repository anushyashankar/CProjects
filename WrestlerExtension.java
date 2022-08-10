import java.util.*;

public class WrestlerExtension {
	TeamMap team;

	public WrestlerExtension() {
		team = new TeamMap();
	}

	public void everything() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to start a team? Type A for yes and E for no");
		String input = scan.next();

		while (!input.equalsIgnoreCase("E")) {
			System.out.println("Hello! Would you like to A) add, B) remove, C) change a wrestler's weight, D) view the wrestlers, or E) exit?");
			input = scan.next();

			while (!input.equalsIgnoreCase("A") && !input.equalsIgnoreCase("B") && !input.equalsIgnoreCase("C") && !input.equalsIgnoreCase("D") && !input.equalsIgnoreCase("E")) {
				System.out.println("The input was invalid: please enter A) add, B) remove, C) change a wrestler's weight, D) view the wrestlers, or E) exit");
				input = scan.next();
			}

			if (input.equalsIgnoreCase("E")) {
				System.out.println("Thank you!");
				return;
			}

			if (input.equalsIgnoreCase("A")) {
				System.out.println("Enter the name of the wrestler: ");
				String name = scan.next();
				System.out.println("Enter the weight of the wrestler: ");
				int weight = scan.nextInt();
				team.addWrestler(new Wrestler(name, weight));
			}
			if (input.equalsIgnoreCase("B")) {
				System.out.println("Enter the name of the wrestler: ");
				String name = scan.next();
				System.out.println("Enter the weight of the wrestler: ");
				int weight = scan.nextInt();
				team.removeWrestler(new Wrestler(name, weight));
			}
			if (input.equalsIgnoreCase("C")) {
				System.out.println("Enter the name of the wrestler: ");
				String name = scan.next();
				System.out.println("Enter the old weight of the wrestler: ");
				int oldWeight = scan.nextInt();
				System.out.println("Enter the new weight of the wrestler: ");
				int newWeight = scan.nextInt();
				team.changeWeight(new Wrestler(name, oldWeight), newWeight);
			}
			if (input.equalsIgnoreCase("D")) {
				System.out.println(team.toString());
			}
		}
	}


	public static void main (String[] args) {
		WrestlerExtension one = new WrestlerExtension();
		one.everything();
	}

}
