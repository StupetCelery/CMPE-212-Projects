package a1;

public class Sandpile {

	/**
	 * Print a 2-dimensional array of cells using least 3 spaces for each value.
	 * Values for each row of the array appear on a single line, and each row
	 * appears on its own line.
	 * 
	 * @param cells a two-dimensional array
	 * @throws IllegalArgumentException if the specified array has a dimension equal
	 *                                  to zero
	 */
	public static void printCells(int[][] cells) {
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = cells[r][c];
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
	}

	// TRANSLATE THE REMAINING C FUNCTIONS INTO JAVA METHODS HERE
	
	public static void init(int grains, int rows, int cols, int[][] cells) {
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		if (grains < 0) {
			throw new IllegalArgumentException("grains < 0");
		}
		cells[rows / 2][cols / 2] = grains;
	}
	
	public static Index2 first_to_topple(int rows, int cols, int[][] cells) {
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		Index2 idx = new Index2();		
		for (int r = 0; r < rows; r++) {
			idx.row = r;
			for (int c = 0; c < cols; c++) {
				idx.col = c;
				int val = cells[r][c];
				if (val >= 4) {
					return idx;
				}
			}
		}
		idx.row = -1;
		idx.col = -1;
		return idx;
	}
	
	public static void topple(Index2 i, int rows, int cols, int[][] cells) {
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		int grains = cells[i.row][i.col];
		if (grains < 4) {
			throw new IllegalArgumentException("grains < 4");
		}
		cells[i.row][i.col] -= 4;
		if (i.row > 0) {
			cells[i.row - 1][i.col]++;
		}
		if (i.col < cols - 1) {
			cells[i.row][i.col + 1]++;
		}
		if (i.row < rows - 1) {
			cells[i.row + 1][i.col]++;
		}
		if (i.col > 0) {
			cells[i.row][i.col - 1]++;
		}
	}
	
	public static int degree(Index2 i, int[][] cells) {
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		if (i.row > rows || i.col > cols) {
			throw new IllegalArgumentException("index is too high for the grid");
		}
		
		int d;
		
		//check to see if cell is a corner cell
		if ((i.row == 0 || i.row == rows) && (i.col == 0 || i.col == cols)) {
			return d = 2;
		}
		
		//check to see if cell is an edge cell
		if (i.row == 0 || i.row == rows || i.col == 0 || i.col == cols){
			return d = 3;
		}
		
		//cell is an interior cell
		return d = 4;
	}

	/**
	 * Creates a 15x15 sandpile simulation starting with 2 to-the-power 8 grains of
	 * sand on the center cell. The starting configuration of cells is printed to
	 * standard output and then the simulation is run until all cells reach a stable
	 * state (have fewer than 4 grains of sand). The stable configuration of cells
	 * is printed to standard output.
	 * 
	 * <p>
	 * Finally, an image of the stable configuration is shown.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] cells = new int[15][15];
		// FINISH TRANSLATING THE main FUNCTION HERE
		final int ROWS = cells.length;
		final int COLS = cells[0].length;
		init((int)Math.pow(2,8), ROWS, COLS, cells);
		System.out.println("Original cells");
		printCells(cells);
		Index2 i = first_to_topple(ROWS, COLS, cells);
		while (i.row >= 0) {
			topple(i, ROWS, COLS, cells);
			i = first_to_topple(ROWS, COLS, cells);
		}
		System.out.println("\nFinal cells");
		printCells(cells);
		
		// THE NEXT TWO LINES SHOULD BE THE LAST LINES OF THE METHOD 
		// show an image of the stable configuration
		SandpileViewer.draw(cells);
	}

}
