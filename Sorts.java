import java.util.Random;

public class Sorts {

	private int[] nos;
	private int steps;

	// Constructs a default array of size 10
	public Sorts() {
		nos = new int[10];
		nos[0] = -10001;
		nos[1] = 3;
		nos[2] = 7;
		nos[3] = 19;
		nos[4] = 15;
		nos[5] = 19;
		nos[6] = 7;
		nos[7] = 3;
		nos[8] = 19;
		nos[9] = -100;
	}

	public Sorts(int[] temp) {
		nos = temp;
	}

	// Constructs an array with size random Sorts from [0,range)
	public Sorts(int size, int range) {
		nos = new int[size];
		for(int i = 0; i < size; i++) {
			nos[i] = (int)(Math.random()*range) ;
		}
	}

	// Constructs an array of random Sorts [0-range) array of size count with a
	// seed
	// The seed allows you to use the same set of random numbers

	public Sorts(int count, int range, long seed) {
		Random generator = new Random(seed);
		nos = new int[count];
		for(int i = 0; i < count; i++) {
			nos[i] = generator.nextInt(range);
		}
	}

	// This constructor will create an ordered array of consecutive integers
	// true will yield ascending order
	// false will yield descending order
	public Sorts(int count, boolean ordered) {
		nos = new int[count];
		if(ordered) {
			for(int i = 0; i < count; i++)
				nos[i] = i;
		}
		else
			for(int i = count - 1; i > 0; i--)
				nos[i] = i;
	}

	public int getSteps() {
		return steps;
	}

	public void display() {
		for (int x : nos)
			System.out.print(x + " ");
		System.out.println();
	}

	public int[] getNos() {
		return nos;

	}

	public void swap(int x, int y) {
		int temp = nos[x];
		nos[x] = nos[y];
		nos[y] = temp;
		steps += 3;
	}

	public void bubbleSort() {
		// Consecutive values are compared and swapped if necessary
		steps = 0;
		boolean swapped = true;
		steps++;
		int lastSwap = nos.length - 1;
		steps++;
		int temp = 0;
		steps++;
		steps++; // initialize for loop
		for (int i = 0; i < nos.length; i++) {
			steps += 3; // boundary check, increment,if
			if (swapped) {
				swapped = false;
				steps++;
				steps++; // initialize for loop
				for (int j = 0; j < lastSwap; j++) {
					steps += 3; // boundary check, increment,if
					if (nos[j] > nos[j + 1]) {
						swap(j, j + 1);
						swapped = true;
						steps++;
						temp = j;
						steps++;
					}
				}
				lastSwap = temp;
				steps++;
			}

		}
	}

	public void bubbleSort2(int []nos, int length) {
		steps++;
		if (length == 1)	{	
			steps++;
			return;
		}
		steps++;
		for (int i = 0; i < length - 1; i++) {
			steps++;
			if (nos[i] > nos[i+1]) {
				steps++;
				swap(nos[i], nos[i+1]);		
			}
		}
		steps++;
		bubbleSort2(nos,  length - 1);		
	}

	public void insertionSort() {
		steps = 0;
		steps++;
		for (int i = 1; i < nos.length; ++i) {
			int key = nos[i];
			steps++;
			int j = i - 1;
			steps++;
			steps++;
			while (j >= 0 && nos[j] > key) {
				steps++;
				nos[j + 1] = nos[j];
				steps++;
				j = j - 1;
				steps++;
			}
			steps++;
			nos[j + 1] = key;
			steps++;
		}
	}

	public void selectionSort() {
		steps = 0;
		steps++;
		for (int i = 0; i < nos.length-1; i++) {
			int minInd = i;
			steps++;
			for (int j = i + 1; j < nos.length - 1; j++) {
				steps++;
				if (nos[j] < nos[minInd]) {
					steps++;
					minInd = j;
					steps++;
				}
				steps++;
			}
			steps++;

			swap(nos[i], nos[minInd]);
			steps++;
		}

	}

	public static void main(String[] args) {

		Sorts one = new Sorts(10000, 1000);
		//		Sorts two = one;
		//		Sorts three = one;
		//		
		StopWatch timer = new StopWatch();

		timer.start();
		one.steps = 0;
		one.selectionSort();
		timer.stop();
		one.display();
		System.out.println("Selection Sort\nDefault Array Steps:     " + one.getSteps());
		System.out.println("Default Array time: " + timer.getElapsedTime() + " milliseconds.");
		timer.reset();


		// This is a sample code for testing bubble sort for data in reverse order
		// Sorts two = new Sorts (100000,false);
		// timer.reset();
		// timer.start();
		// two.bubbleSort();
		// timer.stop();
		// two.display();
		// System.out.println("Reverse order Steps: " + two.getSteps());
		// System.out.println("Reverse order time: " + timer.getElapsedTime()+ "
		// milliseconds");
		// System.out.println();

	}
}
