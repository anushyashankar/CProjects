

public class ChessComplete {
	private static int[][] board;
	private int position;
	private int count, numSteps;

	public ChessComplete(int size) {
		board = new int[size][size];
		count = 0;
		numSteps = size*size;
	}
	
	public boolean isEmpty(int r, int c) {
		if (board[r][c] == 0)
			return true;
		else
			return false;
	}

	public boolean move(int x, int y) {
		count++;
		if (x < 0 || y < 0 || x >= board.length || y >= board.length)
		{
			return false;
		}
		else if (numSteps == position)
			return true;
		else if (!isEmpty(x, y))
		{
			return false;
		}
		else {
			position++;
			board[x][y] = position; 
			if (move(x+2, y-1) || move(x+2, y+1) || move(x-2, y+1) || move(x-2, y-1) 
					|| move(x+1, y+2) || move(x-1, y+2) || move(x+1, y-2) || move(x-1, y-2))
				return true;
			else {
				position--;
				board[x][y] = 0;
				return false;
			}
		}
	}
	
	public int getSteps() {
		return count;
	}
	
	public void displayBoard() {
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				System.out.printf("%2d ", board[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		ChessComplete one = new ChessComplete(7);
		one.move(0, 0);
		one.displayBoard();
		System.out.print("Recursive method call count:");
		System.out.printf("%,2d ", +one.getSteps());
		System.out.println();
		}
}
