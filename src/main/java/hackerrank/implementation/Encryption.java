package hackerrank.implementation;

import java.util.Scanner;

public class Encryption {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine().trim();
		scanner.close();
		
		String spaceRemovedInput = getSpaceRemovedString(input, input.length());
		
		int L = spaceRemovedInput.length();
		double sqrt = Math.sqrt(L);
		int r = (int) Math.floor(sqrt);
		int c =  (int) Math.ceil(sqrt);
		
		if( r*c < L && c*c >= L) {
			r = c;
		}else if(r*c < L && r*r >= L ) {
			c = r;
		}
		

		char[][] grid = new char[r][c];
		int charIndex = 0;
		for(int i = 0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(charIndex < L) {
					grid[i][j] = spaceRemovedInput.charAt(charIndex++);
				}
			}
		}

		StringBuilder builder = new StringBuilder();
		for(int j=0; j<c; j++) {
			for(int i = 0; i<r; i++) {
				if(grid[i][j] != '\0') {
					builder.append(grid[i][j]);
				}
			}
			builder.append(" ");
		}
		
		System.out.println(builder.toString());
	}
	
	
	private static String getSpaceRemovedString(String input, int length) {
		String output = "";
		for (int i = 0; i < length; i++) {
			if (input.charAt(i) != ' ') {
				output += input.charAt(i);
			}
		}
		return output;
	}
}
