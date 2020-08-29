package hackerrank.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

//resource: https://stackoverflow.com/questions/31113993/maximum-subarray-sum-modulo-m 

public class MaximumSubArraySum {
	private int n;
	private long m;
	private long[] a;
	private TreeSet<Long> prefixSumBalancedTree;
	
	public MaximumSubArraySum(int n, long m, long[] a) {
		this.n = n;
		this.m = m;
		this.a = a;
		prefixSumBalancedTree = new TreeSet<Long>();
	}
	
	public long getResult() {
		
		long maxModuloSum = 0;
		long prefix = 0;
		
		prefixSumBalancedTree.add(prefix);
		
		for(int i=0; i<n; i++) {
			prefix = (prefix + a[i]) % m;
			
			maxModuloSum = Math.max(maxModuloSum, prefix);
			
			Long greaterOrEqualToPrefixPlusOne = prefixSumBalancedTree.ceiling(prefix + 1);
			if(greaterOrEqualToPrefixPlusOne != null) {
				maxModuloSum = Math.max(maxModuloSum, prefix - greaterOrEqualToPrefixPlusOne.longValue() + m);
			}
			
			prefixSumBalancedTree.add(prefix);
		}

		return maxModuloSum;
	}
	
	public static void main(String[] args) {
		List<MaximumSubArraySum> solutions = new ArrayList<MaximumSubArraySum>();
		
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int count = 0; count < t; count++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			long m = scanner.nextLong();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			long[] a = new long[n];
			
			for (int i = 0; i < n; i++) {
				a[i] = scanner.nextLong(); 
			}
			solutions.add(new MaximumSubArraySum(n, m, a));
		}
		scanner.close();
		
		solutions.forEach(solution -> System.out.println(solution.getResult()));
		
	}
	
	
}
