package hackerrank.queue;

import java.util.LinkedList;
import java.util.Scanner;

public class QueueUsingTwoStacks {
	
	public static void main(String[] args) {
		
		StringBuffer output = new StringBuffer();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		for(int i=0; i<N; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String[] inputs = scanner.nextLine().split(" ");
			
			if(inputs.length == 2) {
				queue.add(Integer.parseInt(inputs[1].trim()));
			}else {
				if(inputs[0].trim().equals("2")) {
					if( !queue.isEmpty() ) {
						queue.remove();
					}
				}else if(inputs[0].trim().equals("3")) {
					output.append(queue.peek().intValue()).append("\n");
				}
			}
			
		}
		scanner.close();
		
		System.out.println(output.toString());
	}
	
}
