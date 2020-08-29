package hackerrank.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumSubArray {
	
	private static Integer[] subSolutions = null;
	
	private static String getMaxSubarrayValues(int[] array) {
		int length = array.length;
		
		subSolutions = new Integer[length];
		Arrays.fill(subSolutions, null);
		
		int max = -100000;
		
		for(int i=0; i<length; i++) {
			max =  Math.max(max, maxSubArraySumUntil(array, i));
		}
		
//		System.out.println();
//		for(int i=0; i<length; i++) {
//			System.out.print(subSolutions[i]+" ");
//		}
//		System.out.println();
		
		Arrays.fill(subSolutions, null);
		int maxSubSequence = -100000;
		for(int i=0; i<length; i++) {
			maxSubSequence =  Math.max(maxSubSequence, maxSubSequenceSumUntil(array, i, max));
		}
		
//		System.out.println();
//		for(int i=0; i<length; i++) {
//			System.out.print(subSolutions[i]+" ");
//		}
//		System.out.println();

		return max+" "+maxSubSequence;
	}
	
	private static int maxSubArraySumUntil(int[] array, int n) {
		if (subSolutions[n] != null)
			return subSolutions[n];
		
		if (n == 0)
			return subSolutions[n] = array[0];
		
		int max = Math.max(array[n], maxSubArraySumUntil(array, n - 1) + array[n]);
		subSolutions[n] = max;

		return max;
	}
	
	
	private static int maxSubSequenceSumUntil(int[] array, int n, int maxSubArraySum) {

		if (subSolutions[n] != null)
			return subSolutions[n];
		
		if (n == 0)
			return subSolutions[n] = array[n];

		int sumOfLastIndex = maxSubSequenceSumUntil(array, n - 1, maxSubArraySum);
		int lastSumToConsider = sumOfLastIndex + ( maxSubArraySum > 0 && array[n] > 0 ?  array[n] : 0 ) ;
		int max = Math.max( array[n] , lastSumToConsider);
		subSolutions[n] = max;

		return max;
	}
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		
		StringBuffer output = new StringBuffer();
		
		for (int i = 0; i < t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String[] inputLine = scanner.nextLine().split(" ");

			int[] numbers = new int[n];

			for (int j = 0; j < n; j++) {
				numbers[j] = Integer.parseInt(inputLine[j]);
			}
			
			output.append(getMaxSubarrayValues(numbers)).append("\n");
		}
		scanner.close();
		
		System.out.println(output.toString());
	}
	
}
