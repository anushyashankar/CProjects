
public class CreatedSort {
	
	private int[] nos;
	private int steps;
	
	public void createdSort(int []nos, int length) {
		steps++;
		if (length == 1) {	
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
		createdSort(nos,  length - 1);		
	}
}
