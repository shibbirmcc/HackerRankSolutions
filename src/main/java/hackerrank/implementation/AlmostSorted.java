package hackerrank.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// https://www.hackerrank.com/challenges/almost-sorted/problem

public class AlmostSorted {
	private int n;
	private int[] array;
	private int[] sorted;
	private List<Integer[]> unmatchedSubsequenceindices;

	public AlmostSorted(int n, int[] array) {
		this.n = n;
		this.array = array;
		unmatchedSubsequenceindices  = new ArrayList<Integer[]>(1);
		sort();
	}

	private void sort() {
		sorted = new int[n];
		System.arraycopy(array, 0, sorted, 0, n);
		Arrays.sort(sorted);
	}

	public int[] getSortedArray() {
		return sorted;
	}

	public boolean isSorted(int[] a) {
		for (int i = 0; i < n; i++)
			if (a[i] != sorted[i])
				return false;
		return true;
	}

	public boolean canBeSwapped(int s1, int s2) {
		int[] tempArray = new int[n];
		System.arraycopy(array, 0, tempArray, 0, n);

		int temp = tempArray[s1];
		tempArray[s1] = tempArray[s2];
		tempArray[s2] = temp;

		return isSorted(tempArray);
	}

	public boolean canBeReversed(int i1, int i2) {
		int[] tempArray = new int[n];
		System.arraycopy(array, 0, tempArray, 0, n);

		for (int i = i1, j = i2; i <= i2 && j >= i1; i++, j--) {
			tempArray[i] = array[j];
		}

		return isSorted(tempArray);
	}

	public List<Integer[]> findUnmatchedSubsequenceIndices() {

		Stack<Integer> buffer = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			if (sorted[i] != array[i]) {
				buffer.add(i);
			} else if (!buffer.isEmpty()) {
				// subsequence ended
				int count = buffer.size();
				addToUnmatchedIndiceList(buffer.toArray(new Integer[count]));
				buffer.clear();
			}
		}

		if (!buffer.isEmpty()) {
			// subsequence ended
			int count = buffer.size();
			addToUnmatchedIndiceList(buffer.toArray(new Integer[count]));
			buffer.clear();
		}

		return unmatchedSubsequenceindices;
	}
	
	
	private void addToUnmatchedIndiceList(Integer[] indices) {
		if( !unmatchedSubsequenceindices.isEmpty() ) {
			int size = unmatchedSubsequenceindices.size();
			Integer[] lastIndices = unmatchedSubsequenceindices.get(size - 1);
			int lastIndexCount = lastIndices.length;
			int indexCount = indices.length;
			
			if(lastIndexCount == indexCount && indices[0] - lastIndices[lastIndexCount-1] == 2) {
				// merge these 2 sequences
				int totalCount = indexCount + lastIndexCount;
				Integer[] data = new Integer[totalCount];
				System.arraycopy(lastIndices, 0, data, 0, lastIndexCount);
				System.arraycopy(indices, 0, data, lastIndexCount, lastIndexCount);
				
				unmatchedSubsequenceindices.remove(size-1);
				unmatchedSubsequenceindices.add(data);
			}else {
				unmatchedSubsequenceindices.add(indices);
			}
		}else {
			unmatchedSubsequenceindices.add(indices);
		}
		
		
	}
	

	public void printResult(List<Integer[]> unmatchedSubsequenceindices) {
		// so, here we are because out array doesn't match with the sorted array.
		int unmatchedSubSequenceCount = unmatchedSubsequenceindices.size();

		if (unmatchedSubSequenceCount > 2) {
			// sublist is more than 2 means a lot of mismatches which can't be solved by one
			// operation
			System.out.println("no");
		} else if (unmatchedSubSequenceCount == 1) {
			// can be a range or can be a single element
			solveIfValidSolutionExists(unmatchedSubsequenceindices.get(0));
		} else if (unmatchedSubSequenceCount == 2) {
			solveIfValidSolutionExists(unmatchedSubsequenceindices.get(0), unmatchedSubsequenceindices.get(1));
		} else {
			System.out.println("no");
		}
	}

	
	/**
	 * If there is a single sublist containing unmatched indices, then either swap
	 * or reverse can be executed depending on the elements of the sublists
	 * 
	 * @param subsequence
	 */
	private void solveIfValidSolutionExists(Integer[] subsequence) {
		int subsequenceLength = subsequence.length;

		if (subsequenceLength > 1) {

			int i1 = subsequence[0];
			int i2 = subsequence[subsequenceLength - 1];

			if (canBeSwapped(i1, i2)) {
				System.out.println("yes");
				System.out.println("swap " + (i1 + 1) + " " + (i2 + 1));
			} else if (canBeReversed(i1, i2)) {
				System.out.println("yes");
				System.out.println("reverse " + (i1 + 1) + " " + (i2 + 1));
			} else {
				System.out.println("no");
			}

		} else {
			System.out.println("no");
		}
	}

	
	/**
	 * If 2 unmatched sublists found then the only the swap operation can be
	 * executed only if both of the sublists contains only one element each
	 * 
	 * @param subsequence1
	 * @param subsequence2
	 */
	private void solveIfValidSolutionExists(Integer[] subsequence1, Integer[] subsequence2) {
		int firstSubSequenceLength = subsequence1.length;
		int secondSequenceLength = subsequence2.length;

		if (firstSubSequenceLength == 1 && firstSubSequenceLength == secondSequenceLength) {

			int s1 = subsequence1[0];
			int s2 = subsequence2[0];

			if (canBeSwapped(s1, s2)) {
				System.out.println("yes");
				System.out.println("swap " + (s1 + 1) + " " + (s2 + 1));
			} else {
				System.out.println("no");
			}

		} else {
			System.out.println("no");
		}
	}

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = scanner.nextInt();
		}
		scanner.close();

		AlmostSorted solution = new AlmostSorted(n, array);

		if (solution.isSorted(array)) {
			System.out.println("yes");
			return;
		}

		List<Integer[]> unmatchedSubsequenceindices = solution.findUnmatchedSubsequenceIndices();

		// sorting umatched sublists by their lengths
		Collections.sort(unmatchedSubsequenceindices, new Comparator<Integer[]>() {
			public int compare(Integer[] o1, Integer[] o2) {
				return Integer.compare(o1.length, o2.length);
			}
		});
		
		solution.printResult(unmatchedSubsequenceindices);
	}

}
