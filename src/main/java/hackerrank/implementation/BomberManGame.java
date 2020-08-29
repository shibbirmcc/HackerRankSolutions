package hackerrank.implementation;

import java.util.Scanner;


public class BomberManGame {

	public final static char BOMB = (char)(79);
	public final static char NOTHING = '.';
	
	private int r;
	private int c;
	/**
	 * Grid represents the total grid in numbers such as : Bomb containing cell will
	 * have value >= 0, nothing cell will have value < 0
	 */
	private int[][] grid;
	private int[][][] lookup_table;

	public BomberManGame(int r, int c, int[][] grid) {
		this.r = r;
		this.c = c;
		this.grid = grid;
		init();
	}
	
	public void init() {
		
		lookup_table = new int[4][r][c];
		
		for(int t = 0; t<4; t++) {
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					lookup_table[t][i][j] = 0;
				}
			}
		}
	}
	
	
	private void addToLookupTable(int t) {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				lookup_table[t][i][j] = grid[i][j];
			}
		}
	}
	
	private String getResultFromLookupTable(int t) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				buffer.append( lookup_table[t][i][j] < 0 ? NOTHING : BOMB);
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	/***
	 * When a step is completed then all the remaining time of each bomb containing
	 * cell will increase by 1
	 */
	public void stepCompleted() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				grid[i][j] += (grid[i][j] >= 0 ? 1 : 0);
			}
		}
	}
	
	/***
	 * Empty cells have value < 0, so, setting 0 to those cells to contain bombs
	 */
	public void plantBombsInEmptyCells() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				grid[i][j] = ( grid[i][j] < 0 ? 0 : grid[i][j] );
			}
		}
	}
	
	
	private void explode(int i, int j) {
		if (grid[i][j] >= 3) {
			grid[i][j] = -1;

			if (i > 0)
				grid[i - 1][j] = (grid[i - 1][j] >= 3 ? grid[i - 1][j] : -1);
			if (i < r - 1)
				grid[i + 1][j] = (grid[i + 1][j] >= 3 ? grid[i + 1][j] : -1);
			if (j > 0)
				grid[i][j - 1] = (grid[i][j - 1] >= 3 ? grid[i][j - 1] : -1);
			if (j < c - 1)
				grid[i][j + 1] = (grid[i][j + 1] >= 3 ? grid[i][j + 1] : -1);
		}
	}
	
	public void explodeAndWatch() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				explode(i,j);
			}
		}
	}

	public String getResult(long t) {
		
		if(t < 2) {
			StringBuffer buffer = new StringBuffer();
			
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					buffer.append( grid[i][j] < 0 ? NOTHING : BOMB);
				}
				buffer.append("\n");
			}
			return buffer.toString();
		}else {
			if((int) (t%2) == 0) {
				return getResultFromLookupTable(0);
			}else if((int) (t%4) == 3) {
				return getResultFromLookupTable(1);
			}else {
				return getResultFromLookupTable(2);
			}
		}
	}
	

	public void solve(long t) {
		
		//step1
		if(t == 0L) return; 
		
		//step2
		stepCompleted();
		if(t == 1L) return; 
		
		
		//step 3
		stepCompleted();
		plantBombsInEmptyCells();
		addToLookupTable(0);
		if(t == 2L) return; 
		
		// step 4
		stepCompleted();
		explodeAndWatch();
		addToLookupTable(1);
		if(t == 3L) return;
		
		//step 3
		stepCompleted();
		plantBombsInEmptyCells();
		if(t == 4L) return; 
		
		// step 4
		stepCompleted();
		explodeAndWatch();
		addToLookupTable(2);
		if(t == 5L) return;
	}
	

	public static void main(String[] args) {
		int r, c;
		long t;
		int[][] grid;

		Scanner scanner = new Scanner(System.in);
		r = scanner.nextInt();
		c = scanner.nextInt();
		t = scanner.nextLong();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		grid = new int[r][c];
		for (int i = 0; i < r; i++) {
			String line = scanner.nextLine();
			for (int j = 0; j < c; j++) {
				grid[i][j] = Character.compare(line.charAt(j), BOMB) == 0 ? 0 : -1;
			}
		}
		scanner.close();
		
		BomberManGame game = new BomberManGame(r, c, grid);
		game.solve(t);
		System.out.println(game.getResult(t));
	}

}
