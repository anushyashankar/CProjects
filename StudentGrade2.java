import java.util.*;
public class StudentGrade2 {
	
	public static String printMenuAndGetChoice() {
		Map<Student, String> studentToGradeMap = new HashMap<Student, String>();
		Map<Integer, Student> idToStudent = new HashMap<Integer, Student>();
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
				printGrades(studentToGradeMap);
			}
			else if (choice.equals("B") ) {
				modifyStudent(studentToGradeMap, idToStudent);
			}
			else if (choice.equals("C") ) {
				removeStudent(studentToGradeMap, idToStudent);
			}
			else if (choice.equals("D") ) {
				addStudent(studentToGradeMap, idToStudent);
			}
		}

		return "Thank you!";
	}
	
	public static void addStudent(Map<Student, String>studentToGradeMap,
									Map<Integer, Student> idToStudent) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter first name of student: ");
		String fname = scan.next();
		System.out.println();
		
		System.out.print("Enter last name of student: ");
		String lname = scan.next();
		System.out.println();
		
		System.out.print("Enter student's id: ");
		int id = scan.nextInt();
		System.out.println();
		
		if(idToStudent.get(id) != null)
		{
			System.out.println("ID already exists!");
			return;
		}
		
		Student temp = new Student(id, fname, lname);
		idToStudent.put(id, temp);
		
		System.out.print("Enter a grade: ");
		String gr = scan.next();
		System.out.println();
		
		studentToGradeMap.put(temp, gr);
	}
	
	public static void removeStudent(Map<Student, String>studentToGradeMap,
										Map<Integer, Student> idToStudent) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter student's id: ");
		int id = scan.nextInt();
		
		if(idToStudent.get(id) == null) {
			System.out.println("ID does not exist!");
			return;
		}
		
		System.out.println("Are you sure you want to remove "
					+ idToStudent.get(id).getfName() + "?");
		System.out.print("Type 'Y' for yes or 'N' for no: ");
		String input = scan.next();
		System.out.println();
		
		if(input.equalsIgnoreCase("N"))
		{
			System.out.println("Remove was cancelled.");
			return;
		}
//		
//		String fname = idToStudent.get(id).getfName();
//		String lname = idToStudent.get(id).getlName();
//		idToStudent.remove(id);
//		
//		Student temp = new Student(id, fname, lname);
//		studentToGradeMap.remove(temp);
		
		studentToGradeMap.remove(idToStudent.get(id));
		idToStudent.remove(id);
	}
	
	public static void modifyStudent(Map<Student, String>studentToGradeMap,
									Map<Integer, Student> idToStudent)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter student's id: ");
		int id = scan.nextInt();
		
		if(idToStudent.get(id) == null)
		{
			System.out.println("ID does not exist!");
			return;
		}
		
		System.out.print("Enter new Grade: ");
		String gr = scan.next();
		System.out.println();
		
		System.out.println("Old Grade: "
					+ studentToGradeMap.get(idToStudent.get(id)));
		studentToGradeMap.remove(idToStudent.get(id));
		studentToGradeMap.put(idToStudent.get(id), gr);
	}
	
	public static void printGrades(Map<Student, String>studentToGradeMap)
	{
		Map<Student, String> temp = studentToGradeMap;
		Iterator<Student> it = studentToGradeMap.keySet().iterator();
		Set thing = studentToGradeMap.keySet();
		Student[] t = new Student[thing.size()];
		int count = 0;
		
		while(it.hasNext())
		{
			t[count] = it.next();
			count++;
		}
		
		Student first = t[0];
		int num = 0;
		
		for(int i = 0; i<t.length; i++)
		{
			for(int x = 1; x<t.length; x++)
			{
				if (t[x] != null)
				{
					if (first.compareTo(t[x]) == 1)
					{
						first = t[x];
						num = x;
					}
				}
			}
			
			t[num] = null;
			System.out.println(first
					+ " " + studentToGradeMap.get(first));
			
			for(int y = 1; y<t.length; y++)
			{
				if(t[y] != null)
				{
					first = t[y];
					num = y;
					break;
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(printMenuAndGetChoice());
	}
}