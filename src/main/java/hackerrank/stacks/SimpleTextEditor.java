package hackerrank.stacks;

import java.util.Scanner;

public class SimpleTextEditor {

	private static StringBuilder output = new StringBuilder();

	private static class OperationStack {
		private final int ARRAY_LENGTH = 1000000;
		private String operations[] = new String[ARRAY_LENGTH];
		private int operationListSize = 0;

		public void push(String t) {
			operations[operationListSize++] = t;
		}

		public String get(int i) {
			return operations[i];
		}

		public String pop() {
			String operation = get(operationListSize - 1);
			operations[operationListSize--] = null;
			return operation;
		}

		public String peek() {
			return operations[operationListSize - 1];
		}

		public boolean isEmpty() {
			return operationListSize <= 0;
		}
	}

	private static OperationStack operationStack = new OperationStack();

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		int Q = scanner.nextInt();
		operationStack.push("");

		for (int i = 0; i < Q; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String inputs[] = scanner.nextLine().split(" ");

			if (inputs.length == 1) {
				// undo
				if (!operationStack.isEmpty()) {
					operationStack.pop();
				}
			} else if (inputs.length == 2) {

				int operationIndex = Integer.parseInt(inputs[0]);

				if (operationIndex == 1) {
					// append string
					String appendString = inputs[1];
					String newString = operationStack.peek() + appendString;
					operationStack.push(newString);
				} else if (operationIndex == 2) {
					// delete int
					int deleteLetterCount = Integer.parseInt(inputs[1]);
					String existingString = operationStack.peek();
					int kthIndex = existingString.length() - deleteLetterCount;
					operationStack.push(existingString.substring(0, kthIndex));
				} else if (operationIndex == 3) {
					output.append(operationStack.peek().charAt(Integer.parseInt(inputs[1]) - 1)).append("\n");
				}
			}
		}
		scanner.close();
		System.out.println(output.toString());
	}

}
