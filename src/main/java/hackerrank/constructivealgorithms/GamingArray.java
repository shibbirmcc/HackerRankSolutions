package hackerrank.constructivealgorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class GamingArray {

	private static class Element {
		private int value;
		public int currentIndex;

		public Element(int v, int i) {
			value = v;
			currentIndex = i;
		}

		public String toString() {
			return "" + value;
		}
	}

	public static String solve(Element[] array, int n) {

		Arrays.sort(array, new Comparator<Element>() {
			@Override
			public int compare(Element o1, Element o2) {
				return Integer.compare(o2.value, o1.value); // reverse order
			}
		});

		int deletableIndex = array[0].currentIndex;
		int count = 1;

		for (int i = 1; i < n && deletableIndex >= 0; i++) {
			if (array[i].currentIndex < deletableIndex) {
				deletableIndex = array[i].currentIndex;
				count++;
			}
		}

		return (count % 2 == 0 ? "ANDY" : "BOB");
	}

	public static void main(String[] args) {
		StringBuffer result = new StringBuffer();

		Scanner scanner = new Scanner(System.in);
		int g = scanner.nextInt();
		for (int i = 0; i < g; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			Element[] array = new Element[n];

			for (int j = 0; j < n; j++) {
				array[j] = new Element(scanner.nextInt(), j);
			}

			result.append(solve(array, n)).append("\n");
		}
		scanner.close();

		System.out.println(result.toString());
	}
}
