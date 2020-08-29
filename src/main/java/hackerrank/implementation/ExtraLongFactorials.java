package hackerrank.implementation;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {
	
	public static BigInteger getFactorial(int n) {
		BigInteger[] factorials = new BigInteger[n+1];
		factorials[0] = BigInteger.valueOf(1);
		
		for(int i=1; i<=n; i++) {
			factorials[i] =  factorials[i-1].multiply(BigInteger.valueOf(i));
		}
		
		return factorials[n];
	}
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		System.out.println(getFactorial(n));
	}
	
}
