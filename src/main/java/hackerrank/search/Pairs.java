package hackerrank.search;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Pairs {

	private int n;
	private long k;
	private long[] array;
	private SortedSet<Long> expectedPairs;

	public Pairs(long[] array, int n, long k) {
		this.n = n;
		this.k = k;
		this.array = array;
		expectedPairs = new TreeSet<Long>();
	}

	public int getTotalNumberOfExistingPairs() {
		// first sort the input array
		Arrays.sort(array);

		long max = array[n - 1];

		for (int i = 0; i < n; i++) {
			long expected = array[i] + k;

			if (expected <= max && !expectedPairs.contains(expected)) {
				expectedPairs.add(expected);
			}
		}

		return countExistingPairs();
	}

	private int countExistingPairs() {
		int count = 0;

		int index = 0;

		Iterator<Long> it = expectedPairs.iterator();
		while (it.hasNext()) {
			long expected = it.next();

			while (array[index] < expected) {
				index++;
			}

			if (array[index] == expected) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long k = scanner.nextLong();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		long array[] = new long[n];

		for (int i = 0; i < n; i++) {
			array[i] = scanner.nextLong();
		}
		scanner.close();

		Pairs solution = new Pairs(array, n, k);
		System.out.println(solution.getTotalNumberOfExistingPairs());
	}
}
