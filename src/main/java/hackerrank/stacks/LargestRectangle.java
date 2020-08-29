package hackerrank.stacks;

import java.util.Scanner;

public class LargestRectangle {

	static long largestRectangle(int[] h) {

		int buildingCount = h.length;

		long maxArea = 0;
		for (int i = 0; i < buildingCount - 1; i++) {
			int height = h[i];

			int leftCount = countLeftAdjacentBuildings(h, i - 1, height);
			int rightCount = countRightAdjacentBuildings(h, i + 1, height);
			int k = leftCount + rightCount + 1;

			long area = k * height;
			maxArea = Math.max(area, maxArea);
		}

		return maxArea;
	}

	private static int countLeftAdjacentBuildings(int[] h, int index, int height) {
		int k = 0;
		for (int i = index; i >= 0; i--) {
			if (h[i] < height)
				break;
			k++;
		}
		return k;
	}

	private static int countRightAdjacentBuildings(int[] h, int index, int height) {
		int k = 0;
		for (int i = index; i < h.length; i++) {
			if (h[i] < height)
				break;
			k++;
		}
		return k;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int n = scanner.nextInt();
		int[] h = new int[n];

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] hItems = scanner.nextLine().split(" ");

		for (int i = 0; i < n; i++) {
			int hItem = Integer.parseInt(hItems[i]);
			h[i] = hItem;
		}

		long result = largestRectangle(h);

		System.out.println(String.valueOf(result));
		scanner.close();
	}
}
