package hackerrank.stacks;

import java.util.Scanner;
import java.util.Stack;

public class PoisonousPlants {

	private static class Plant {
		int pesticideAmount;
		int lifeSpan = 0;

		public Plant(int pesticide) {
			pesticideAmount = pesticide;
		}
	}

	private static int solution(Plant[] plants) {

		int length = plants.length;
		int maxDaysToFinishKilling = 0;

		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);

		for (int i = 1; i < length; i++) {

			Plant currentPlant = plants[i];

			currentPlant.lifeSpan = 1;

			while (!stack.isEmpty()) {

				Plant nonKillerPlant = plants[stack.peek().intValue()];

				if (nonKillerPlant.pesticideAmount >= currentPlant.pesticideAmount) {
					/***
					 * Current plant's life increases as long as it has non-killer plants before it
					 */
					currentPlant.lifeSpan = Math.max(currentPlant.lifeSpan, nonKillerPlant.lifeSpan + 1);

					stack.pop();
				} else {
					// found a killer plant and current plant's life ends
					break;
				}
			}

			/***
			 * if the stack is empty that means there was no killer plant for current plant,
			 * thus it's lifespan doesn't affect our calculation
			 */
			currentPlant.lifeSpan = stack.isEmpty() ? 0 : currentPlant.lifeSpan;
			maxDaysToFinishKilling = Math.max(currentPlant.lifeSpan, maxDaysToFinishKilling);

			stack.push(Integer.valueOf(i));
		}

		return maxDaysToFinishKilling;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int aCount = scanner.nextInt();
		Plant[] plants = new Plant[aCount];

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] aItems = scanner.nextLine().split(" ");
		scanner.close();
		for (int aItr = 0; aItr < aCount; aItr++) {
			int aItem = Integer.parseInt(aItems[aItr].trim());
			plants[aItr] = new Plant(aItem);
		}
		int result = solution(plants);
		System.out.println(result);
	}

}
