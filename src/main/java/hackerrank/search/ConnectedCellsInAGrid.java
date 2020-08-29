package hackerrank.search;

import java.util.LinkedList;
import java.util.Scanner;


public class ConnectedCellsInAGrid {
	
	public static class Cell {
		int i;
		int j;

		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Cell) {
				Cell c = (Cell) o;
				return i == c.i && j == c.j;
			}
			return false;
		}

		public String toString() {
			return " ( " + i + ", " + j + " ) ";
		}
	}
	
	private int[][] grid;
	private int r;
	private int c;
	private LinkedList<Cell> queue = new LinkedList<Cell>();
	
	public ConnectedCellsInAGrid(int[][] grid, int r, int c) {
		this.grid = grid;
		this.r = r;
		this.c = c;
	}
	
	private int solve() {
		int max = 0;
		
		for(int i=0; i < r; i++) {
			for(int j=0; j<c; j++) {
				max = Math.max(max, getRegionArea(i, j));
			}
		}
		
		return max;
	}
	
	private int getRegionArea(int i, int j) {
		int area = 0;

		if (isValidFilledCell(i, j)) {
			queue.add(new Cell(i, j));
			area++;
		}

		while (!queue.isEmpty()) {

			Cell c = queue.poll();
			
			// disable the processed cell
			grid[c.i][c.j] = 0;
			
			int validConnectedCellCount = 0;

			if (isValidFilledCell(c.i - 1, c.j)) {
				Cell cell = new Cell(c.i - 1, c.j); // up
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}

			}
			if (isValidFilledCell(c.i - 1, c.j - 1)) {
				Cell cell = new Cell(c.i - 1, c.j - 1); // up-left
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}
			if (isValidFilledCell(c.i - 1, c.j + 1)) {
				Cell cell = new Cell(c.i - 1, c.j + 1); // up-right
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}

			if (isValidFilledCell(c.i + 1, c.j)) {
				Cell cell = new Cell(c.i + 1, c.j); // down
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}
			if (isValidFilledCell(c.i + 1, c.j - 1)) {
				Cell cell = new Cell(c.i + 1, c.j - 1); // down-left
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}
			if (isValidFilledCell(c.i + 1, c.j + 1)) {
				Cell cell = new Cell(c.i + 1, c.j + 1); // down-right
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}

			if (isValidFilledCell(c.i, c.j - 1)) {
				Cell cell = new Cell(c.i, c.j - 1); // left
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}
			if (isValidFilledCell(c.i, c.j + 1)) {
				Cell cell = new Cell(c.i, c.j + 1); // right
				if (!queue.contains(cell)) {
					queue.add(cell);
					validConnectedCellCount++;
				}
			}

			area += validConnectedCellCount;
		}

		return area;
	}

	private boolean isValidFilledCell(int i, int j) {
		return i >= 0 && i < r && j >= 0 && j < c && grid[i][j] == 1;
	}
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		int m = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int array[][] = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				array[i][j] = scanner.nextInt();
			}
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		}

		scanner.close();
		
		
		ConnectedCellsInAGrid solution = new ConnectedCellsInAGrid(array, n, m);
		System.out.println(solution.solve());
	}
}
