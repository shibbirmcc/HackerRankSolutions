package hackerrank.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IceCreamPerlor {
	
	private int n;
	private int m;
	private List<Integer> prices;
	
	public IceCreamPerlor(int n, int m, List<Integer> prices) {
		this.n = n;
		this.m = m;
		this.prices = prices;
	}
	
	public String solve() {
		
		StringBuffer result = new StringBuffer();
		
		for(int i=0; i<n; i++) {
			
			int current = prices.get(i);
			
			if(current < m) {
				int diff = m - current;
				
				Integer lookForIndexOf = Integer.valueOf(diff);
				int resultIndex = prices.indexOf(lookForIndexOf);
				
				if(resultIndex >= 0 && resultIndex != i) {  // can't be same flavour
					int index1 = i + 1;
					int index2 = resultIndex+1;
					result.append(index1 < index2 ? index1 : index2).append(" ").append(index1 > index2 ? index1 : index2);
					break;
				}
			}
		}
		
		return result.toString();
	}
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		
		IceCreamPerlor[] solutions = new IceCreamPerlor[t];
		
		for(int i = 0; i<t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int m = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			List<Integer> prices = new ArrayList<Integer>();
			
			for(int j = 0; j<n; j++) {
				prices.add(scanner.nextInt());
			}
			
			solutions[i] = new IceCreamPerlor(n, m, prices);
		}
		scanner.close();
		
		for(int i=0; i<t; i++) {
			System.out.println(solutions[i].solve());
		}
	}
	
}
