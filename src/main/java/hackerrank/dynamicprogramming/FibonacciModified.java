package hackerrank.dynamicprogramming;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {
	/***
	 * Memoized approach solution
	 * 
	 */
	static BigInteger fib(int n, BigInteger t[]) {
		if (t[n] == null) {
			if (n <= 1) {
				t[n] = BigInteger.valueOf(n);
			}else {
				BigInteger t1 = fib(n - 2, t);
				BigInteger t2 = fib(n - 1, t);
				t2 = t2.multiply(t2);
				t[n] = t1.add(t2);
			}
		}
		return t[n];
	}
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t1 = scanner.nextInt();
		int t2 = scanner.nextInt();
		int n  = scanner.nextInt();
		scanner.close();
		
		BigInteger[] t = new BigInteger[n+1];
		t[0] = BigInteger.valueOf(t1);
		t[1] = BigInteger.valueOf(t2);
		
		System.out.println(fib(n-1, t));
	}
}
