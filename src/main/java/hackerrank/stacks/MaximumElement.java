package hackerrank.stacks;

import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> maxValueStack = new Stack<Integer>();

		StringBuffer output = new StringBuffer();

		int Q = scanner.nextInt();

		int maxValue = 0;

		for (int i = 0; i < Q; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String inputs[] = scanner.nextLine().split(" ");

			if (inputs.length == 1) {
				int operationIndex = Integer.parseInt(inputs[0]);
				if (operationIndex == 3) {
					// print max
					output.append(maxValueStack.peek().intValue()).append("\n");
				} else if (operationIndex == 2) {
					stack.pop();
					maxValueStack.pop();
					maxValue = maxValueStack.isEmpty() ? 0 : maxValueStack.peek().intValue();
				}
			} else if (inputs.length == 2) {

				int operationIndex = Integer.parseInt(inputs[0]);

				if (operationIndex == 1) {
					int n = Integer.parseInt(inputs[1]);
					maxValue = Math.max(maxValue, n);

					stack.push(n);
					maxValueStack.push(maxValue);
				}
			}
		}
		scanner.close();
		System.out.println(output.toString());
	}

}
