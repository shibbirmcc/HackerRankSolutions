package hackerrank.stacks;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/and-xor-or/problem
public class AndXorOR {

	/***
	 * Solved with stack filled with the input integers. And Simplified modulus
	 * operation becomes just and XOR
	 */

	private static int andXorOr(int[] a, int[] stack) {

		int length = a.length;
		int top = a.length;

		int maxResult = 0;
		for (int i = 0; i < length; i++) {

			int m1 = a[i];
			// collecting m2 from the stack
			while (top >= 0) {
				int m2 = stack[top - 1]; // getting the top element

				if (m1 < m2) {
					int result = m1 ^ m2;
					maxResult = Math.max(result, maxResult);

					stack[--top] = 0; // pop

				} else if (m1 > m2) {
					int result = m1 ^ m2;
					maxResult = Math.max(result, maxResult);
					break;
				} else {
					break;
				}
			}

			// push m1 to stack
			stack[top++] = m1;
		}
		return maxResult;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int aCount = scanner.nextInt();

		int[] a = new int[aCount];
		int[] stack = new int[2 * 1000000];
		int stackIndex = aCount - 1;

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] aItems = scanner.nextLine().split(" ");
		scanner.close();

		for (int aItr = 0; aItr < aCount; aItr++) {
			int aItem = Integer.parseInt(aItems[aItr].trim());
			a[aItr] = aItem;
			stack[stackIndex--] = aItem;
		}

		int result = andXorOr(a, stack);
		System.out.println(result);
	}
}
