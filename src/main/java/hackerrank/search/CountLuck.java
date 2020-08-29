package hackerrank.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CountLuck {

	private static class Node {
		public int i;
		public int j;
		public int value;

		private boolean visited = false;
		private int sourceIndex = -1;
		private int neighbourCount = 0;

		public Node(int i, int j, int value) {
			this.i = i;
			this.j = j;
			this.value = value;
		}

		public void visit() {
			this.visited = true;
		}

		public boolean isVisited() {
			return this.visited;
		}

		public void setSourceNode(int source) {
			this.sourceIndex = source;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Node) {
				Node c = (Node) o;
				return i == c.i && j == c.j;
			}
			return false;
		}

		public int hashCode() {
			return Objects.hash(i, j);
		}

		public String toString() {
			return " ( " + i + ", " + j + " ) ";
		}

		public void setNeighbourCount(int neighbourCount) {
			this.neighbourCount = neighbourCount;
		}
	}

	private int r;
	private int c;
	private Node[] grid;
	/**
	 * Linear index of source and target
	 */
	private int source;
	private int target;
	private int k;

	public CountLuck(Node[] grid, int r, int c, int source, int target, int k) {
		this.r = r;
		this.c = c;
		this.grid = grid;
		this.source = source;
		this.target = target;
		this.k = k;
	}

	private List<Integer> getValidNeighbourIndices(int i, int j) {
		List<Integer> neighbours = new ArrayList<Integer>();

		if (isValidBlankCell(i, j + 1)) {
			neighbours.add(linearIndex(i, j + 1)); // right
		}

		if (isValidBlankCell(i, j - 1)) {
			neighbours.add(linearIndex(i, j - 1)); // left
		}

		if (isValidBlankCell(i + 1, j)) {
			neighbours.add(linearIndex(i + 1, j)); // down
		}

		if (isValidBlankCell(i - 1, j)) {
			neighbours.add(linearIndex(i - 1, j)); // up
		}
		return neighbours;
	}

	private int linearIndex(int i, int j) {
		return (i * c) + j;
	}

	private boolean isValidBlankCell(int i, int j) {
		return i >= 0 && i < r && j >= 0 && j < c && grid[linearIndex(i, j)].value == 1;
	}

	/**
	 * Using BFS to traverse and count the wand waves
	 * 
	 * @return
	 */
	public boolean isCorrectGuess() {

		AtomicBoolean destinationReached = new AtomicBoolean(false);

		/**
		 * Queue will contain only the linear indices
		 */
		Queue<Integer> queue = new LinkedList<>();

		grid[source].visit();
		queue.add(source);

		while (!queue.isEmpty() && !destinationReached.get()) {

			int currentNodeIndex = queue.poll();

			if (currentNodeIndex == target) {
				break;
			}

			List<Integer> neighbourList = getValidNeighbourIndices(grid[currentNodeIndex].i, grid[currentNodeIndex].j);
			grid[currentNodeIndex].setNeighbourCount(neighbourList.size());

			for (int neighborIndex : neighbourList) {

				if (neighborIndex == target) {
					grid[neighborIndex].setSourceNode(currentNodeIndex);
					destinationReached.set(true);
					break;
				}

				// We only add unvisited neighbors
				if (!grid[neighborIndex].isVisited()) {
					grid[neighborIndex].setSourceNode(currentNodeIndex);
					grid[neighborIndex].visit();
					queue.add(neighborIndex);
				}
			}
		}

		int count = 0;
		int sourceIndex = grid[target].sourceIndex;
		while (sourceIndex >= 0) {

			if (sourceIndex == source && grid[sourceIndex].neighbourCount > 1) {
				count += 1;
			} else if (grid[sourceIndex].neighbourCount > 2) {
				count += 1;
			}

			sourceIndex = grid[sourceIndex].sourceIndex;
		}

		return count == k;
	}

	public static void main(String[] args) {

		List<CountLuck> solutions = new ArrayList<CountLuck>();

		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();

		for (int count = 0; count < t; count++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int r = scanner.nextInt();
			int c = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			Node[] array = new Node[r * c];
			int source = -1;
			int target = -1;

			int linearIndex = 0;
			for (int i = 0; i < r; i++) {
				String line = scanner.nextLine();
				for (int j = 0; j < c; j++) {

					if (line.charAt(j) == '*') {
						target = linearIndex;
						array[linearIndex] = new Node(i, j, 1);
					} else if (line.charAt(j) == 'M') {
						source = linearIndex;
						array[linearIndex] = new Node(i, j, 1);
					} else if (line.charAt(j) == 'X') {
						array[linearIndex] = new Node(i, j, 0);
					} else {
						array[linearIndex] = new Node(i, j, 1);
					}

					linearIndex++;
				}
			}

			int k = scanner.nextInt();
			solutions.add(new CountLuck(array, r, c, source, target, k));
		}
		scanner.close();

		solutions.forEach(solution -> System.out.println(solution.isCorrectGuess() ? "Impressed" : "Oops!"));
	}

}
