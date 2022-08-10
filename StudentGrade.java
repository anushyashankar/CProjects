import java.util.*;
public class StudentGrade {

	public static String printMenuAndGetChoice() {
		Map<String, String> gradeMap = new HashMap<String, String>();
		Scanner scan = new Scanner(System.in);
		String choice1 = "";
		System.out.println("Hello! Welcome to the Student Gradebook. Please enter Y to continue, and N to exit: ");
		choice1 = scan.next();

		while (choice1.equals("Y")) {
			System.out.println("Student Grades:\nA: Print Grades\nB: Modify Student\nC: Remove Student\nD: Add Student\nE: Exit");
			System.out.println("Please enter a letter to pick an option: ");
			String choice = scan.next();

			if (choice.equals("E")) {
				return "Thank you!";
			}

			while (!choice.equals("A") && !choice.equals("B") && !choice.equals("C") && !choice.equals("D")) {
				System.out.println("You entered an invalid response.");
				System.out.println("Please enter a letter to pick an option Or type E to exit: ");
				choice = scan.next();
				if (choice.equals("E")) {
					return "Thank you!";
				}
			}

			if (choice.equals("A") ) {
				printGrades(gradeMap);
			}
			else if (choice.equals("B") ) {
				modifyStudent(gradeMap);
			}
			else if (choice.equals("C") ) {
				removeStudent(gradeMap);
			}
			else if (choice.equals("D") ) {
				addStudent(gradeMap);
			}
		}

		return "Thank you!";
	}

	public static void printGrades(Map<String, String> gradebook) {
		Iterator<String> it = gradebook.keySet().iterator();
		System.out.println("Student Grade List:");

		while (it.hasNext()) {
			String temp = it.next();
			System.out.println(temp + ": " + gradebook.get(temp));
		}
	}

	public static void modifyStudent(Map<String, String> gradeMap) {
		System.out.println("Type the name of student whose grade you would like to modify: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.next();

		while (gradeMap.get(name) == null) {
			System.out.println("That person does not exist in the map.");
			System.out.println("Type the name of student whose grade you would like to modify OR exit to leave: ");
			name = scan.next();
			if (name.equals("exit"))
				return;
		}

		System.out.println("Type the letter grade you wish for the student " + name + " to have: ");
		String grade = scan.next();
		gradeMap.remove(name);
		gradeMap.put(name,  grade);
	}

	public static void removeStudent(Map<String, String> gradeMap) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Type the name of student you want to remove: ");
		String name = scan.next();

		while (gradeMap.get(name) == null) {
			System.out.println("That person does not exist in the map.");
			System.out.println("Type the name of student you want to remove OR exit to leave: ");
			name = scan.next();
			if (name.equals("exit"))
				return;
		}

		gradeMap.remove(name);
	}

	public static void addStudent(Map<String, String> gradeMap) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Type the name of student you want to add: ");
		String name = scan.next();

		while (gradeMap.get(name) != null) {
			System.out.println("That person exists in the map.");
			System.out.println("Type the name of student you want to add OR exit to leave: ");
			name = scan.next();
			if (name.equals("exit"))
				return;
		}

		System.out.println("Please enter a grade for this student: ");
		String grade = scan.next();

		gradeMap.put(name, grade);
	}

	public static void main(String[] args) {
		System.out.println(printMenuAndGetChoice());
	}
}
