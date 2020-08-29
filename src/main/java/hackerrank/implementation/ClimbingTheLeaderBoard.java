package hackerrank.implementation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class ClimbingTheLeaderBoard {

	private static HashMap<Integer, Integer> scorePositionMapping = new HashMap<Integer, Integer>(); // <score , position>
	
    static int[] climbingLeaderboard(Integer[] scores, Integer[] alice) {
    	int scoreLength = scores.length;
    	int length = alice.length;
    	int result[] = new int[length];
    	
    	for(int i=0; i<length; i++) {
    		
    		int insertionPosition = Arrays.binarySearch(scores, alice[i] , Comparator.reverseOrder());
    		
    		if(insertionPosition >= 0) {
    			
    			int position = scorePositionMapping.get(scores[insertionPosition]);
    			result[i]  = position;
    			
    		}else {
    			// doesn't exist
    			
    			int index = Math.abs(insertionPosition) - 1;
    			if(index >= scoreLength) {
    				// last one
    				result[i]  = scorePositionMapping.get(scores[scoreLength-1]) + 1;
    				
    			}else {
    				result[i]  = scorePositionMapping.get(scores[index]); // acquire this position
    			}
    			
    		}
    	}
    	
    	return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Integer[] scores = new Integer[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        

        int position = 1;
        
        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;

            if(i == 0) {
            	scorePositionMapping.put(scoresItem, position);
            }else if(scoresItem != scores[i-1]) {
            	scorePositionMapping.put(scoresItem, ++position);
            }
        }

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Integer[] alice = new Integer[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        scanner.close();
    }
}
