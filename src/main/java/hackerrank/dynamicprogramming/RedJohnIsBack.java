package hackerrank.dynamicprogramming;

import java.util.Scanner;

public class RedJohnIsBack {
	
	private static int solution(int n) {
		if(n == 1) 
			return 0; // prime count until 1 is 0
		else if(n == 2)
			return 0; // prime count until 1 is 0
		else if (n == 3)
			return 0; // prime count until 1 is 0
		else if (n == 4)
			return 1; // prime count until 2 is 1
		else {
			int[] dp = new int[n+1];
			dp[1] = 1; dp[2] = 1; dp[3] = 1; dp[4] = 2;
			for(int i=5; i<=n; i++) {
				dp[i] = dp[i -1] + dp[i-4];
			}
			return countPrimesUntil(dp[n]);
		}
	}
	
	
	// Sieve of Eratosthenes
	private static int countPrimesUntil(int M) {
		int count =0;
		boolean[] isPrime = new boolean[M+1];
		for(int i=0; i<=M; i++) {
			isPrime[i] = true;
		}
		
		for(int i=2; i*i<=M; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<= M; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		for(int i=2; i<=M; i++) {
			count += isPrime[i] ? 1 : 0;
		}
		
		return count;
	}
	
	
	public static void main(String[] args) {
		
		StringBuffer output = new StringBuffer();
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		int n[] = new int[t];
		
		for (int i = 0; i < t; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			n[i] = scanner.nextInt();
		}
		scanner.close();
		
		for (int i = 0; i < t; i++) {
			output.append(solution(n[i])).append("\n");
		}

		System.out.println(output.toString());
	}
	
}
