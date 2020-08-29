package hackerrank.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/the-grid-search/problem

public class TheGridSearch {
	
	private int R;
	private int C;
	private int[][] grid;
	private int r;
	private int c;
	private int[][] pattern;
	
	public TheGridSearch(int r, int c, int[][] grid, int r2, int c2, int[][] pattern) {
		this.R = r;
		this.C = c;
		this.grid = grid;
		this.r = r2;
		this.c = c2;
		this.pattern = pattern;
	}
	
	public boolean exists() {

		for (int i = 0; i <= R - r; i++) {
			for (int j = 0; j <= C - c; j++) {
				if (countMatchedElements(i, j) == (r * c)) {
					return true;
				}
			}
		}

		return false;
	}
	
	private int countMatchedElements(int i, int j) {
		int countMatch = 0;

		for (int hi = i; hi < i + r; hi++) {
			for (int hj = j; hj < j + c; hj++) {
				if (grid[hi][hj] == pattern[hi - i][hj - j]) {
					countMatch++;
				}else {
					return countMatch;
				}
			}
		}
		return countMatch;
	}
	
	
	public static void main(String[] args) {
		
		List<TheGridSearch> solutions = new ArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		
		int T = scanner.nextInt();
		
		for(int i=0; i<T; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int R = scanner.nextInt();
			int C = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			
			
			
			int[][] grid = new int[R][C];
			for(int j=0; j<R; j++) {
				String line = scanner.nextLine();
				for(int k = 0; k< C; k++) {
					grid[j][k] = Integer.parseInt(""+line.charAt(k));
				}
			}
			
			int r = scanner.nextInt();
			int c = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			int[][] pattern = new int[r][c];
			
			for(int j=0; j<r; j++) {
				String line = scanner.nextLine();
				for(int k = 0; k< c; k++) {
					pattern[j][k] = Integer.parseInt(""+line.charAt(k));
				}
			}
			
			
			solutions.add(new TheGridSearch(R, C, grid, r, c, pattern));
		}
		
		scanner.close();
		
		
		solutions.forEach(solution -> System.out.println(solution.exists() ? "YES" : "NO"));
	}
	
	
	
}
