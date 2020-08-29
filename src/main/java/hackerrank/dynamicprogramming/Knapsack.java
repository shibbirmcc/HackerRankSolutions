package hackerrank.dynamicprogramming;

import java.util.Scanner;

// https://www.hackerrank.com/challenges/unbounded-knapsack/problem
public class Knapsack {

	/**
	 * We can view this problem as a subset of the problems finding total number of
	 * ways to generate a sum of N using array elements But the question puzzled the
	 * problem solvers with the option of adding same element multiple times.
	 * 
	 * So, interpreting the problem from Knapsack perspective would be critical to
	 * formulate a faster theory. But if we think that, if we can generate the total
	 * number of combinations that can create a sum resulting N then we can just
	 * collect the combination of sum <= N that has a value greater than 0
	 * 
	 * So, we create a 1-D table (counts[]) where we generate sums of the elements (
	 * repeating allowed). Thus the table indices would be (0,1,2....N) And the
	 * table elements would be the total number of combinations can be found to
	 * generate that sum with the given integers.
	 * 
	 * So, while finding the result, we can start from the last of the table which
	 * has the value of the maximum sum counts[N] and traverse until 0. If we find
	 * an element that has a count > 0 then we consider the index to be the result,
	 * because the index represents the sum value
	 */

	static int[] countWays(int N, int[] weights) {
		int length = weights.length;
		int count[] = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			count[i] = 0;
		}
		count[0] = 1;

		for (int i = 1; i <= N; i++)
			for (int j = 0; j < length; j++)
				if (i >= weights[j]) {
					int adding_value = count[i - weights[j]];
					count[i] += (adding_value > 0 ? adding_value : 0);
				}
		return count;
	}

	private static int getMaxValue(int counts[], int N) {
		for (int i = N; i >= 0; i--) {
			if (counts[i] > 0) {
				return i;
			}
		}
		return 0;
	}

	public static void main(String args[]) {

		StringBuffer output = new StringBuffer();
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();

		for (int i = 0; i < t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			int k = scanner.nextInt();

			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String[] inputLine = scanner.nextLine().split(" ");

			int[] weights = new int[n];

			for (int j = 0; j < n; j++) {
				weights[j] = Integer.parseInt(inputLine[j]);
			}

			output.append(getMaxValue(countWays(k, weights), k)).append("\n");
		}
		scanner.close();

		System.out.println(output.toString());
	}

}
