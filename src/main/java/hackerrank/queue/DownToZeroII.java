package hackerrank.queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/down-to-zero-ii/problem

public class DownToZeroII {

	private static class TargetNumber{
		int N;
		int moves;
		public TargetNumber(int n, int m) {
			N = n;
			moves = m;
		}
	}
	
	private static Queue<TargetNumber> queue = new LinkedList<>();
	private static HashSet<Integer> visited = new HashSet<Integer>();
	
	private static int getMinimumMoves(int N) {
		
		queue.clear();
		queue.add(new TargetNumber(N, 0));
		
		visited.clear();

		while (!queue.isEmpty()) {

			TargetNumber targetNumber = queue.poll();

			if (targetNumber.N == 1) {
				return targetNumber.moves;
			}

			// decrease value and add to the queue
			if (!visited.contains(targetNumber.N - 1)) {
				queue.add(new TargetNumber(targetNumber.N - 1, targetNumber.moves + 1));
				// add to visited list
				visited.add(targetNumber.N - 1);
			}
			
			enqueueAllUnvisitedDivisors(targetNumber.N, targetNumber.moves + 1);
		}

		return -1;
	}
	
	private static void enqueueAllUnvisitedDivisors(int N, int move) {
		int sqrt = (int) Math.ceil(Math.sqrt(N));
		for (int i = 2; i <= sqrt; i++) {
			if (N % i == 0) {
				
				int divisor = N / i;
				int targetDivisor = Math.max(i, divisor);
				
				if (!visited.contains(targetDivisor)) {
					queue.add(new TargetNumber(targetDivisor, move));
					// add to visited list
					visited.add(targetDivisor);
				}
			}
		}
	}
	
	
	
//	private int test() {
//		
//	    int max = 1000001;
//	    int nums[max];
//	    
//	    //Initialize array
//	    for(int i = 0; i < max; ++i) 
//	    	nums[i] = -1;
//	    
//	    nums[0] = 0;
//	    nums[1] = 1;
//	    nums[2] = 2;
//	    nums[3] = 3;
//	    
//	    //Precompute
//	    for(int i = 0; i < max; ++i){
//	    	
//	    	int N = nums[i];
//	    	int previousN = nums[i-1];
//	    	
//	        if(N == -1 || N > (previousN + 1) ) {
//	            N = previousN + 1;
//	        }
//	        
//	        for(int j = 1; j <= i && j * i < max; ++j) {
//	        	
//	            if(nums[j * i] == -1 || (N + 1) < nums[j * i]) {
//	                nums[j * i] = N + 1;
//	            }
//	        }
//	    }
//	    
//	    
//	    return 0;
//	}

	public static void main(String[] args) {
		
		StringBuffer output = new StringBuffer();
		Scanner scanner = new Scanner(System.in);
		int Q = scanner.nextInt();
//		int N[] = new int[Q];
		
		for (int i = 0; i < Q; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//			N[i] = scanner.nextInt();
			int n = scanner.nextInt();
			output.append(getMinimumMoves(n)+1).append("\n");
		}
		scanner.close();

		
		
		
		
//		for(int i=0; i<Q; i++) {
//			int moves = getMinimumMoves(N[i])+1;
//			output.append(moves).append("\n");
//		}
		
		System.out.println(output.toString());
	}
}
