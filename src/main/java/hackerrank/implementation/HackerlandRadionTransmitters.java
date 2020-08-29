package hackerrank.implementation;

import java.util.Arrays;
import java.util.Scanner;

public class HackerlandRadionTransmitters {

	int n;
	int k;
	Integer[] houses;

	public HackerlandRadionTransmitters(int n, int k, Integer[] houses) {
		this.n = n;
		this.k = k;
		this.houses = houses;
	}

	public int getMinimumNumberOfTransmittersRequired() {
		int countTransmitters = 0;
		Arrays.sort(houses);

		for (int i = 0; i < n;i++) {
			countTransmitters++;
		
			int position = i;
			while ( i+1 < n && houses[i+1] - houses[position] <= k) {
				i++;
			}
			position = i;
			while ( i+1 < n && houses[i+1] - houses[position] <= k) {
				i++;
			}
		}

		return countTransmitters;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		Integer[] data = new Integer[n];
		for (int i = 0; i < n; i++) {
			data[i] = scanner.nextInt();
		}
		scanner.close();
		System.out.println(new HackerlandRadionTransmitters(n, k, data).getMinimumNumberOfTransmittersRequired());
	}
}
