package hackerrank.stacks;
import java.util.Scanner;

/***
 * 
 * @author shibbir.ahmed
 * 
 *         This problem is actually a dynamic problem because the description of
 *         top element being at 0th index contradicts with the idea of top
 *         element in a stack And the problem suggests that there should be more
 *         than one ways to select integers from the stacks which suggests that
 *         we can never use pop() method since we need to keep the old top value
 *         due to generation different combinations. using pop() and again
 *         push() the same value doesn't really an efficient way to describe a
 *         stack problem
 *
 */

public class GameOfTwoStacks {

	private static int maxScore = 0;
	private static int cumulativeSum = 0;

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		processInputs(scanner);
		scanner.close();
	}

	private static void processInputs(Scanner scanner) {

		int G = scanner.nextInt();

		for (int g = 0; g < G; g++) {

			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String inputs[] = scanner.nextLine().split(" ");
			int n = Integer.parseInt(inputs[0]);
			int m = Integer.parseInt(inputs[1]);
			int x = Integer.parseInt(inputs[2]);

			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String aStackInputs[] = scanner.nextLine().split(" ");

			int aStack[] = new int[n];
			for (int i = 0; i < n; i++) {
				aStack[i] = Integer.parseInt(aStackInputs[i]);
			}

			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String bStackInputs[] = scanner.nextLine().split(" ");

			int bStack[] = new int[m];
			for (int i = 0; i < m; i++) {
				bStack[i] = Integer.parseInt(bStackInputs[i]);
			}

			cumulativeSum = 0;
			maxScore = 0;
			calculateScore(x, aStack, bStack);

			System.out.println(maxScore);
		}
	}
	
	private static int getStackScore(int stack[], int x) {
		int score = 0;
		for (int i = 0; i < stack.length; i++) {
			if (cumulativeSum + stack[i] > x) {
				break;
			}
			cumulativeSum += stack[i];
			score++;
		}
		return score;
	}
	
	private static int removeScoreFromStack(int stack[], int prev_score, int x) {
		int score = prev_score;
		for (int i = prev_score-1; i >= 0; i--) {
			cumulativeSum -= stack[i];
			score--;
			if (cumulativeSum <= x) {
				break;
			}
		}
		return score;
	}

	
	
	private static void calculateScore(int x, int aStack[], int bStack[]) {

		/***
		 * If we find all the desired integers from the B stack then we take it for
		 * consideration
		 */
		int bScore = getStackScore(bStack, x);
		maxScore = bScore;
		int aScore = 0;
		
		
		for(int i=0; i<aStack.length && cumulativeSum <= x; i++) {
			
			int aData = aStack[i];
			cumulativeSum += aData;
			aScore++;

			if (cumulativeSum > x) {
				// we need to see if some of the elements of previous stack can be removed and
				// gained a proper adjustment
				bScore = removeScoreFromStack(bStack, bScore, x);
				
				// now, if the sum is still higher than x then we quit 
	            if (cumulativeSum > x) {
	                break;
	            }
			}
			
			maxScore = Math.max(aScore+bScore, maxScore);
		}
	}
}
