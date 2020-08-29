package hackerrank.search;

import java.util.Scanner;
import java.util.TreeSet;

public class MinimumLoss {
	private int n;
	private long[] prices;
	private TreeSet<Long> sortedPriceTree;
	
	public MinimumLoss(int n, long[] prices) {
		this.n = n;
		this.prices = prices;
		this.sortedPriceTree = new TreeSet<Long>();
	}
	
	public long minimumLoss() {
		
		long minLoss = Long.MAX_VALUE;
		
		for(int i=0; i<n; i++) {
			sortedPriceTree.add(prices[i]);
		}
		
		for(int i=0; i<n; i++) {
			long currentPrice = prices[i];
			/**
			 * Remove the price that is being processed because the price is only for this
			 * year, it should not be available in the selling years (future years)
			 */
			sortedPriceTree.remove(currentPrice);
			Long lossPrice = sortedPriceTree.lower(currentPrice);
			if(lossPrice != null) {
				minLoss = Math.min(minLoss, currentPrice - lossPrice);
			}
		}
		
		return minLoss;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n= scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		long[] data = new long[n];
		for(int i=0; i<n; i++) {
			data[i] = scanner.nextLong();
		}
		scanner.close();
		System.out.println(new MinimumLoss(n, data).minimumLoss());
	}
	
}
