package hackerrank.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BiggerIsGreater {
	
	private int T;
	private String[] inputs;
	
	public BiggerIsGreater(int T, String[] inputs) {
		this.T = T;
		this.inputs = inputs;
	}  
	
	public String solve() {
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<T; i++) {
			String in = inputs[i];
			int length = in.length();
			
			if(isSortedDescending(in) || length == 1) {
				builder.append("no answer").append("\n");
			}else if(isSortedAscending(in)) {
				// swapping last 2 characters
				String tmp = in.substring(0, length - 2) + in.charAt(length - 1) + in.charAt( length - 2);
				builder.append(tmp).append("\n");
			}else {
				builder.append(processString(in, length)).append("\n");
			}
		}
		
		
		return builder.toString();
	}
	
	
	private String processString(String in, int length) {
		
		int index = length - 2;
		for(; index >= 0; index--) {
			if( ((int)in.charAt(index)) < ((int)in.charAt(index+1))) {
				break;
			}
		}
		
		if(index >= 0) {
			
			int indexValue = ((int)in.charAt(index));
			
			List<Integer> indices = new ArrayList<Integer>();
			
			for(int i=index+1; i<length; i++) {
				if( ((int)in.charAt(i)) > indexValue) {
					indices.add(i);
				}
			}
			
			int min = ( (int)'z' ) + 1;
			int minIndex = -1;
			int indeiceLength = indices.size();
			
			for(int i=0; i<indeiceLength; i++) {
				int ii = indices.get(i);
				int charValue = (int)in.charAt(ii);
				
				if( charValue <  min) {
					min = charValue;
					minIndex = ii;
				}
			}
			
			
			// swap index and min
			StringBuilder builder = new StringBuilder();
			builder.append(in.substring(0, index)).append(in.charAt(minIndex));
			
			String sortingCandidate = in.substring(index+1, minIndex) + in.charAt(index) + in.substring(minIndex+1);
			char[] array = sortingCandidate.toCharArray();
			Arrays.sort(array);
			
			builder.append(new String(array));
			
			
			
			return builder.toString();
		}else {
			return "no answer";
		}
		
	}
	
	
	private boolean isSortedAscending(String in) {
		int length = in.length();
		for (int i = 1; i < length; i++)
			if ( ((int) in.charAt(i - 1) ) > ((int) in.charAt(i) ) ) {
				return false;
			}
		return true;
	}
	
	private boolean isSortedDescending(String in) {
		int length = in.length();
		for (int i = 1; i < length; i++)
			if ( ((int) in.charAt(i - 1) ) < ((int) in.charAt(i) ) ) {
				return false;
			}
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		
		String[] inputs = new String[T];
		for(int i=0; i<T; i++) {
			inputs[i] = scanner.nextLine().trim();
		}
		scanner.close();
		
		
		BiggerIsGreater solution = new BiggerIsGreater(T, inputs);
		System.out.println(solution.solve());
	}
	
	
	
}
