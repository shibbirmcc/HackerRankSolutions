package hackerrank.dynamicprogramming;
import java.util.Scanner;


public class BricksGame {

	
public static void main(String[] args) {
	
	StringBuffer output = new StringBuffer();
	Scanner scanner = new Scanner(System.in);
	int t = scanner.nextInt();
	
	for (int i = 0; i < t; i++) {
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		int n = scanner.nextInt();

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] inputLine = scanner.nextLine().split(" ");

		int[] scores = new int[n];

		for (int j = 0; j < n; j++) {
			scores[j] = Integer.parseInt(inputLine[j]);
		}

		output.append(calculateMaxScore(scores)).append("\n");
	}
	scanner.close();

	System.out.println(output.toString());
}


private static long calculateMaxScore(int[] scores) {
	int length = scores.length;
	long sum = 0;
	long value_sum_index_plus1 = 0, value_sum_index_plus2 = 0, value_sum_index_plus3 = 0;

	for (int i = length - 1; i >= 0; i--) {
		sum += scores[i];

		long maxSumUntilNow = 0;

		if (i >= length - 3) {
			maxSumUntilNow = sum;
		} else {
			long sum_minus_1 = sum - value_sum_index_plus1;
			long sum_minus_2 = sum - value_sum_index_plus2;
			long sum_minus_3 = sum - value_sum_index_plus3;

			maxSumUntilNow = Math.max(sum_minus_1, Math.max(sum_minus_2, sum_minus_3));
		}

		value_sum_index_plus3 = value_sum_index_plus2;
		value_sum_index_plus2 = value_sum_index_plus1;
		value_sum_index_plus1 = maxSumUntilNow;
	}

	return value_sum_index_plus1;
}

}
