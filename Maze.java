public class Maze {
	
	private char[][] maze;
	private int row;
	private int col;
	private int add;
	private int subtract;
	
	public Maze (char[][] m, int i, int j) {
		maze = m;
		row = i;
		col = j;
	}
	
	public void display() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean check(int row, int col) {
		if (maze[row][col] == '*' || maze[row][col] == '&')
			return false;
		else
			return true;
	}

	public boolean findExit() {
		return findExit(row, col);
	}
	
	public boolean findExit(int row, int col) {
		int rownow = row;
		int colnow = col;
		if (rownow >= maze.length || colnow >= maze.length || rownow < 0 || colnow < 0)
			return false;
		else if ((rownow == 0 || colnow == 0 || rownow == maze.length - 1 || colnow == maze[0].length - 1) && check(rownow, colnow)) {
			setIExit(row);
			setJExit(col);
			return check(row, col);
		}
		else if (!check(rownow, colnow)) {
			subtract -= 1;
			return false;
		}
		else {
			maze[row][col] = '&';
			add += 1;
			subtract += 1;
			return findExit(row - 1, col) || findExit(row + 1, col) || findExit (row, col - 1) || findExit (row, col + 1);
		}
	}

	public void setIExit(int r) {
		row = r;
	}
	
	public void setJExit(int c) {
		col = c;
	}

	public String getJExit() {
		// TODO Auto-generated method stub
		return "" + col;
	}

	public String getIExit() {
		// TODO Auto-generated method stub
		return "" + row;
	}

	public String getSteps() {
		// TODO Auto-generated method stub
		return " " + Math.abs(add+subtract);
	}
}
