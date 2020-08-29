package hackerrank.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// https://www.hackerrank.com/challenges/stockmax/problem

public class StockMaximize {

	/***
	 * The question has trick that we can sell any number of profits
	 */
	private static long getMaxProfit(Integer[] prices) {
		long maxProfit = 0;

		/***
		 * Since this problem is about working on the predicted stock prices, we should
		 * really start interpreting the prices from the end day because end day is the
		 * day when we are going to sell whatever we already have purchased.
		 */
		int length = prices.length;
		// So, we should assume that the price on the last day is the highest and we
		// sold everything on the last day
		int highestStockPrice = prices[length - 1];

		for (int i = length - 2; i >= 0; i--) {
			if (prices[i].intValue() < highestStockPrice) {
				// sell the current stock until we find another higher stock price
				maxProfit += highestStockPrice - prices[i].intValue();
			} else {
				// change the highest price
				highestStockPrice = prices[i].intValue();
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();

		List<Integer[]> pricelist = new ArrayList<Integer[]>();

		for (int i = 0; i < t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String[] inputLine = scanner.nextLine().split(" ");

			Integer[] prices = new Integer[n];

			for (int j = 0; j < n; j++) {
				prices[j] = Integer.parseInt(inputLine[j]);
			}
			pricelist.add(prices);
		}
		scanner.close();
		
		pricelist.forEach(prices ->{
			System.out.println(getMaxProfit(prices));
		});
	}
}
