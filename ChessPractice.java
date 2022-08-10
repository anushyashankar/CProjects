
public class ChessPractice {

	private int[][] board;
	private int row;
	private int col;
	private int count = 0;

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

	public int getSteps(int rowtemp, int coltemp) {
		if (rowtemp - 2 < 0) {
			if (rowtemp - 1 < 0) {
				if (rowtemp + 2 >= board.length) {
					if (rowtemp + 1 >= board.length) {
						board[rowtemp][coltemp] = 0;
						count--;
						return getSteps(row, col);
					}
					else if (col - 2 < 0) {
						if (col - 1 < 0) {
							board[rowtemp][coltemp] = 0;
							count--;
							return getSteps(row, col);
						}
					}
				}
			}
		}
		else {
			return 25;
		}
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
