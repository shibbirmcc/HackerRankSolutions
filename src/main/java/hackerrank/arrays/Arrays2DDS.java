package hackerrank.arrays;

import java.io.IOException;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/2d-array/problem

public class Arrays2DDS {
	static int hourglas_filter[][] = new int[][] { { 1, 1, 1 }, { 0, 1, 0 }, { 1, 1, 1 } };
	static int hourglas_length = 3;

	/**
	 * Applying Convolution filter algorithm
	 */
	static int hourglassSum(int[][] a) {
		int maxSum = -Integer.MAX_VALUE; // since there will be negative numbers
		int maxLength = 6;

		for (int i = 0; i <= maxLength - hourglas_length; i++) {
			for (int j = 0; j <= maxLength - hourglas_length; j++) {
				int sum = 0;
				for (int hi = i; hi < i + hourglas_length; hi++) {
					for (int hj = j; hj < j + hourglas_length; hj++) {
						sum += a[hi][hj] * hourglas_filter[hi - i][hj - j];
					}
				}
				maxSum = Math.max(sum, maxSum);
			}
		}

		return maxSum;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		int[][] arr = new int[6][6];

		for (int i = 0; i < 6; i++) {
			String[] arrRowItems = scanner.nextLine().split(" ");
			for (int j = 0; j < 6; j++) {
				int arrItem = Integer.parseInt(arrRowItems[j].trim());
				arr[i][j] = arrItem;
			}
		}
		scanner.close();

		int result = hourglassSum(arr);
		System.out.println(result);
	}
}
