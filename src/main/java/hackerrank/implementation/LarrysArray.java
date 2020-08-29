package hackerrank.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LarrysArray {

	private int t;
	private List<Integer[]> elementList;

	public LarrysArray(int t) {
		this.t = t;
		elementList = new ArrayList<Integer[]>(t);
	}

	public void addElements(Integer[] e) {
		this.elementList.add(e);
	}

	public void solve() {
		for (int i = 0; i < t; i++) {

			Integer[] elements = elementList.get(i);
			int inversionCount = countInversionsDuringMergeSort(elements, 0, elements.length-1);
			if(inversionCount % 2 == 0) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}

	private int countInversionsDuringMergeSort(Integer[] arr, int l, int r) {
		int count = 0;

		if (l < r) {
			int m = (l + r) / 2;
			count += countInversionsDuringMergeSort(arr, l, m);
			count += countInversionsDuringMergeSort(arr, m + 1, r);

			count += mergeAndCount(arr, l, m, r);
		}

		return count;
	}

	private int mergeAndCount(Integer[] arr, int l, int m, int r) {

		// Left subarray
		Integer[] left = Arrays.copyOfRange(arr, l, m + 1);

		// Right subarray
		Integer[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

		int i = 0, j = 0, k = l, swaps = 0;

		while (i < left.length && j < right.length) {
			if (left[i] <= right[j])
				arr[k++] = left[i++];
			else {
				arr[k++] = right[j++];
				swaps += (m + 1) - (l + i);
			}
		}

		while (i < left.length)
			arr[k++] = left[i++];

		while (j < right.length)
			arr[k++] = right[j++];

		return swaps;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();
		LarrysArray solution = new LarrysArray(t);

		for (int i = 0; i < t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			Integer[] elements = new Integer[n];

			for (int j = 0; j < n; j++) {
				elements[j] = scanner.nextInt();
			}
			solution.addElements(elements);
		}
		scanner.close();

		solution.solve();
	}

}
