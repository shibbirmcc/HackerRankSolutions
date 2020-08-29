package hackerrank.arrays;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/array-left-rotation/problem

public class LeftRotation {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int R = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] inputs = scanner.nextLine().split(" ");
		scanner.close();
		StringBuffer output = new StringBuffer();
		
		for(int i=R; i<N; i++) {
			output.append(inputs[i]).append(" ");
		}
		
		for(int i=0; i<R; i++) {
			output.append(inputs[i]).append(" ");
		}
		
		System.out.println(output.toString().trim());
	}
}
