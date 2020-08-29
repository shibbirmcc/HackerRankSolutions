package hackerrank.strings;

import java.util.Arrays;
import java.util.Scanner;


public class SherlockAndValidString {
	
	private static final int ASCII_VALUE_START = 97;
	private static final int ASCII_CHAR_COUNT = 26;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder builder = new StringBuilder(scanner.nextLine().trim());
		scanner.close();

		int[] frequecies = getFrequencies(builder.toString(), builder.length());

		int max = 0;
		int maxChar = 0;
		int min = Integer.MAX_VALUE;
		int minChar = 0;

		for (int i = 0; i < ASCII_CHAR_COUNT; i++) {
			if(frequecies[i] > max) {
				max = frequecies[i];
				maxChar = i + ASCII_VALUE_START;
			}
			
			if(frequecies[i] > 0) {
				if(frequecies[i] < min) {
					min = frequecies[i];
					minChar = i + ASCII_VALUE_START;
				}
			}
		}
		
		int n = builder.length();
		String input = builder.toString();
		boolean result = isValid(input, getFrequencies(input, n));
		
		input = deletCharacter(maxChar, builder.toString());
		n = input.length();
		result = result || isValid(input, getFrequencies(input, n));
		
		input = deletCharacter(minChar, builder.toString());
		n = input.length();
		result = result || isValid(input, getFrequencies(input, n));
		
		
		System.out.println(result ? "YES" : "NO");
	}
	
	private static int[] getFrequencies(String input, int n) {
		int[] frequecies = new int[ASCII_CHAR_COUNT];
		Arrays.fill(frequecies, 0);
		for (int i = 0; i < n; i++) {
			frequecies[((int) input.charAt(i)) - ASCII_VALUE_START] += 1;
		}
		return frequecies;
	}
	
	private static String deletCharacter(int asciValue, String input) {
		int index = input.indexOf(asciValue);
		if (index >= 0) {
			return input.substring(0, index) + ((index + 1 < input.length()) ? input.substring(index + 1) : "");
		}

		return input;
	}
	
	private static boolean isValid(String input, int[] frequencies) {
		int length = input.length();

		for (int i = 1; i < length; i++) {
			if (frequencies[input.charAt(i) - ASCII_VALUE_START] != frequencies[input.charAt(i - 1)
					- ASCII_VALUE_START]) {
				return false;
			}
		}

		return true;
	}
}
