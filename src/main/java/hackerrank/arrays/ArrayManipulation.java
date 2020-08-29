package hackerrank.arrays;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// problem source: https://www.hackerrank.com/challenges/crush/problem

public class ArrayManipulation {
	/***
	 * We can solve it using prefix sum. For each operation a, b, k We add k to a'th
	 * index and add a -k to (b+1)th index. Here we make a boundary that all of the
	 * indices between a-b has an added value of k Then once all the operations are
	 * finished, we sequentially add elements of the array one by one and find the
	 * maximum sum.
	 * 
	 * In java we can implement this algorithm using a TreeMap so that we don't have
	 * to iterate through all the array elements to find the maximum sum, we can do
	 * it by iterating on the indices on which we have performed the operations. If
	 * we use TreeMap then our indices will be sorted when we will iterate to find
	 * the max value.
	 */
    private static long solution(int n, int[][] queries, TreeMap<Integer, Integer> resultMap) {
    	for(int i=0; i<queries.length; i++) {
    		int a = queries[i][0]-1;
    		int b = queries[i][1]-1;
    		int k = queries[i][2];
    		
    		resultMap.merge(a, k, Integer::sum);
    		if(b+1 != n) {
    			resultMap.merge(b+1, -k, Integer::sum);
    		}
    	}
    	
    	long prefixSum = 0;
    	long maxResult = 0;
    	for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
    		prefixSum += entry.getValue().intValue();
    		maxResult = Math.max(prefixSum, maxResult);
    	}
    	
    	return maxResult;
    }
    

    private static final Scanner scanner = new Scanner(System.in);
    private static final TreeMap<Integer, Integer> resultMap = new TreeMap<Integer, Integer>();
    

	public static void main(String[] args){

		String[] nm = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		int[][] queries = new int[m][3];
		
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		
		for (int i = 0; i < m; i++) {
			String[] queriesRowItems = scanner.nextLine().split(" ");
			

			for (int j = 0; j < 3; j++) {
				int queriesItem = Integer.parseInt(queriesRowItems[j]);
				queries[i][j] = queriesItem;
			}
			
			// initializing the indices of TreeMap so that we don't have to check if a key
			// exists or not
			resultMap.put(Integer.valueOf(queries[i][0]-1), Integer.valueOf(0)); // a
			resultMap.put(Integer.valueOf(queries[i][1]), Integer.valueOf(0)); // b+1
		}
		
		System.out.println(solution(n, queries, resultMap));
		
		scanner.close();
		
	}
}
