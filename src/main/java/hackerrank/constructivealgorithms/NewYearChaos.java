package hackerrank.constructivealgorithms;

import java.util.Scanner;

public class NewYearChaos {
	
	public static int solve(int[] array, int n) {
		int bribeCount = 0;
		
		for(int i = (n-1); i>=0 ; i--) {
			int movedIndices = array[i] - ( i + 1 );
			
			if(movedIndices > 2) {
				bribeCount = -1;
				break;
			}
			
			for(int j = Math.max(0, array[i] - 2); j < i; j++) {
				if(array[j] > array[i])
					bribeCount++;
			}
		}
		
		
		return bribeCount;
	}

	public static void main(String[] args) {
		StringBuffer result = new StringBuffer();

		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] array = new int[n];

			for (int j = 0; j < n; j++) {
				array[j] = scanner.nextInt();
			}
			int bribeCount = solve(array, n);
			result.append(bribeCount >= 0 ? bribeCount : "Too chaotic").append("\n");
		}
		scanner.close();

		System.out.println(result.toString());
	}
}
