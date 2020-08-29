package hackerrank.dynamicprogramming;

import java.util.Scanner;

public class TheCoinChangeProblem {

	static long[] init(long N) {
		long lookup_table[] = new long[(int) N + 1];
		lookup_table[0] = 1;
		return lookup_table;
	}

	static void updateLookupTable(int number, int n, int coinValue, long states[]) {
		if (number > n)
			return;
		int remainder = number - coinValue;
		states[number] += states[remainder];
		updateLookupTable(number + 1, n, coinValue, states);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int m = scanner.nextInt();

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] inputLine = scanner.nextLine().split(" ");
		scanner.close();

		long lookup_table[] = init(N);

		for (int i = 0; i < m; i++) {
			int coin = Integer.parseInt(inputLine[i]);
			updateLookupTable(coin, N, coin, lookup_table);
		}
		System.out.println(lookup_table[N]);
	}
}
