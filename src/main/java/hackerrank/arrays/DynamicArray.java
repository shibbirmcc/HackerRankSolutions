package hackerrank.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/dynamic-array/problem

public class DynamicArray {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int Q = scanner.nextInt();
		
		List<Integer>[] a = new ArrayList[N];
		
		int lastAnswer = 0;
		StringBuffer output = new StringBuffer();
		
		for(int i=0; i<Q; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			int q = scanner.nextInt();
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			
			if(q == 1 || q==2) {
				
				int index = (x ^ lastAnswer) % N;
				
				if(index >= 0 && index < N) {
					if(q == 1) {
						if(a[index] == null) {
							a[index] = new ArrayList<Integer>();
						}
						a[index].add(y);
					}else {
						int seq_index = ( a[index] != null ? y%(a[index].size()) : 0) ;
						lastAnswer = ( a[index] == null ? lastAnswer : a[index].get(seq_index) );
						output.append(lastAnswer).append("\n");
					}
				}
			}
		}
		scanner.close();
		System.out.println(output.toString());
	}
	
}
