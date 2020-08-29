package hackerrank.stacks;

import java.io.*;
import java.util.*;

public class BalancedBrackets {

	private static boolean isStartingBracket(char c) {
		return (c == '(' || c == '{' || c == '[');
	}

	private static boolean matchedEndingBracket(char cs, char ce) {
		return (cs == '(' && ce == ')') || (cs == '{' && ce == '}') || (cs == '[' && ce == ']');
	}

	static String isBalanced(String s) {
		Stack<Character> balancedStack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			Character c = Character.valueOf(s.charAt(i));
			if (isStartingBracket(c)) {
				balancedStack.push(c);
			} else { // ending bracket
				if (balancedStack.isEmpty()) {
					return "NO";
				} else if (matchedEndingBracket(balancedStack.peek().charValue(), c.charValue())) {
					balancedStack.pop();
				} else {
					return "NO";
				}
			}
		}
		return balancedStack.isEmpty() ? "YES" : "NO";
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			String s = scanner.nextLine();

			String result = isBalanced(s);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
