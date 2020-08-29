package hackerrank.stacks;

import java.util.Scanner;
import java.util.Stack;

public class EqualStacks {

	private static int solution(Stack<Integer> stacks[], int sums[]) {

		int length = 3;

		// corner case, if any stack is empty then directly return 0
		for (int i = 0; i < length; i++) {
			if (stacks[i].isEmpty())
				return 0;
		}

		while (!allEmpty(stacks) && !hasEqualHeight(sums)) {
			int maxStackIndex = maxSumStackIndex(sums);

			int height = stacks[maxStackIndex].peek();
			stacks[maxStackIndex].pop();
			sums[maxStackIndex] -= height;
		}

		return stacks[0].isEmpty() ? 0 : sums[0];
	}

	private static int maxSumStackIndex(int sums[]) {
		int maxIndex = 0;
		int maxSum = 0;
		for (int i = 0; i < sums.length; i++) {
			if (sums[i] > maxSum) {
				maxSum = sums[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static boolean hasEqualHeight(int sums[]) {
		return sums[0] == sums[1] && sums[1] == sums[2];
	}

	private static boolean allEmpty(Stack<Integer> stacks[]) {
		return stacks[0].isEmpty() && stacks[1].isEmpty() && stacks[2].isEmpty();
	}

	public static void main(String[] args) {

		Stack<Integer> stacks[] = new Stack[3];
		int sums[] = new int[3];

		Scanner scanner = new Scanner(System.in);
		String inputs[] = scanner.nextLine().split(" ");
		int length = inputs.length;
		int n[] = new int[length];

		for (int i = 0; i < length; i++) {
			stacks[i] = new Stack<Integer>();
			n[i] = Integer.parseInt(inputs[i].trim());
		}

		for (int i = 0; i < length; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String numbers[] = scanner.nextLine().split(" ");

			sums[i] = 0;
			for (int j = n[i] - 1; j >= 0; j--) {
				int height = Integer.parseInt(numbers[j].trim());
				sums[i] += height;
				stacks[i].push(height);
			}

		}
		scanner.close();

		int result = solution(stacks, sums);
		System.out.println(result);
	}

}
