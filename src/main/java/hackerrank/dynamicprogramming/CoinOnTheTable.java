package hackerrank.dynamicprogramming;

import java.util.Scanner;

public class CoinOnTheTable {
	private static int n;
	private static int m;
	private static int k;
	private static int finalTargetN;
	private static int finalTargetM;
	private static Character[][] board;
	private static int[][] calculatedMinimumOperations;

	private static void init() {
		calculatedMinimumOperations = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				calculatedMinimumOperations[i][j] = Integer.MAX_VALUE;

				if (board[i][j].equals(Character.valueOf('*'))) {
					finalTargetN = i;
					finalTargetM = j;
				}
			}
		}
	}

	private static void populateCostTable(int currentN, int currentM, int cost, int t) {
		/*************** BASE CASES **********************************/
		if (currentN < 0 || currentN >= n || currentM < 0 || currentM >= m) { // Out of Boundary
			return;
		}

		/******* Updatiung the cost ****************/
		// Keeping the minimum values
		if (cost < calculatedMinimumOperations[currentN][currentM]) {
			calculatedMinimumOperations[currentN][currentM] = cost;
		} else {
			return; // no need to proceed
		}
		/*******************************************/

		if (currentN == finalTargetN && currentM == finalTargetM) {
			return; // reached target
		}
		if (t >= k) {
			return;
		}
		/**************************************************************/

		populateCostTable(currentN - 1, currentM, ('U' == board[currentN][currentM] ? cost : cost + 1), t + 1);
		populateCostTable(currentN + 1, currentM, ('D' == board[currentN][currentM] ? cost : cost + 1), t + 1);
		populateCostTable(currentN, currentM - 1, ('L' == board[currentN][currentM] ? cost : cost + 1), t + 1);
		populateCostTable(currentN, currentM + 1, ('R' == board[currentN][currentM] ? cost : cost + 1), t + 1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String[] nmk = scanner.nextLine().split(" ");
		n = Integer.parseInt(nmk[0].trim());
		m = Integer.parseInt(nmk[1].trim());
		k = Integer.parseInt(nmk[2].trim());

		board = new Character[n][m];
		for (int i = 0; i < n; i++) {
			String row = scanner.nextLine();
			char[] arr = row.toCharArray();

			for (int j = 0; j < m; j++)
				board[i][j] = Character.valueOf(arr[j]);
		}
		scanner.close();

		init();
		populateCostTable(0, 0, 0, 0);
		
		int result = calculatedMinimumOperations[finalTargetN][finalTargetM];
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
}
