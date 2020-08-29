package hackerrank.arrays;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

// Source: https://www.hackerrank.com/challenges/sparse-arrays/problem

public class SparseArray {

	private static final Scanner scanner = new Scanner(System.in);

	static int[] matchingStrings(String[] strings, String[] queries) {
		int result[] = new int[queries.length];

		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			if (frequency.containsKey(strings[i])) {
				frequency.merge(strings[i], 1, Integer::sum);
			} else {
				frequency.put(strings[i], 1);
			}
			
		}

		for (int i = 0; i < queries.length; i++) {
			result[i] = (frequency.containsKey(queries[i]) ? frequency.get(queries[i]) : 0);
		}

		return result;
	}

	public static void main(String[] args) throws IOException {

		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int stringsCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] strings = new String[stringsCount];

		for (int i = 0; i < stringsCount; i++) {
			String stringsItem = scanner.nextLine();
			strings[i] = stringsItem;
		}

		int queriesCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] queries = new String[queriesCount];

		for (int i = 0; i < queriesCount; i++) {
			String queriesItem = scanner.nextLine();
			queries[i] = queriesItem;
		}

		int[] res = matchingStrings(strings, queries);

		for (int i = 0; i < res.length; i++) {
			bufferedWriter.write(String.valueOf(res[i]));

			if (i != res.length - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
