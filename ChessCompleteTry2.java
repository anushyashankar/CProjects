
public class ChessComplete {
	private int[][] board;
	private int row;
	private int col;
	private int count = 0;
	private int check = 0;

	public ChessComplete(int dim) {
		board = new int [dim][dim];
	}

	public void move(int x, int y) {
		board[x][y] = 1;
	}
	
	public boolean check(int i, int j) {
		if (board[i][j] == 0)
			return true;
		return false;
	}
	
	public int getSteps() {
		return getSteps(row, col, count);
	}

	public int getSteps(int rowtemp, int coltemp, int count) {
		if (rowtemp < 0 || coltemp < 0 || rowtemp >= board.length || coltemp >= board.length) {
			return getSteps(row, col, count - 1);
		}
		else if (board[rowtemp][coltemp] == 0) {
			row = rowtemp;
			col = coltemp;
			
			if (getSteps(row + 1, col + 2, count + 1) != -1 && check = 0) {
				check = 0;
				
			}
			
			if (getSteps(row + 1, col + 2, count + 1) != -1) return getSteps(row + 1, col + 2, count + 1);
			else if (getSteps(row + 1, col - 2, count + 1) != -1) return getSteps(row + 1, col - 2, count + 1);
			else if (getSteps(row + 2, col + 1, count + 1) != -1) return getSteps(row + 2, col + 1, count + 1);
			else if (getSteps(row + 2, col - 1, count + 1) != -1) return getSteps(row + 2, col - 1, count + 1);
			
			else if (getSteps(row - 1, col + 2, count + 1) != -1) return getSteps(row - 1, col + 2, count + 1);
			else if (getSteps(row - 1, col - 2, count + 1) != -1) return getSteps(row - 1, col - 2, count + 1);
			else if (getSteps(row - 2, col + 1, count + 1) != -1) return getSteps(row - 2, col + 1, count + 1);
			else if (getSteps(row - 2, col - 1, count + 1) != -1) return getSteps(row - 2, col - 1, count + 1);

		}
		else return -1;
	
	}
	
	public void displayBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.println(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		ChessComplete one = new ChessComplete(8);
		one.move(0, 0);
		one.displayBoard();
		System.out.print("Recursive method call count: ");
		System.out.printf("%,d", +one.getSteps());
		System.out.println();
	}
}
