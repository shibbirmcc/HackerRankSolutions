package hackerrank.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/matrix-rotation-algo/problem

public class MatrixLayerRotation {

	private static class Cell {
		int i;
		int j;

		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Cell) {
				Cell c = (Cell) o;
				return i == c.i && j == c.j;
			}
			return false;
		}

		public String toString() {
			return " ( " + i + ", " + j + " ) ";
		}
	}

	private static class Layer {
		private List<Cell> cells = new ArrayList<>();

		public void addCell(Cell c) {
			if (!cells.contains(c)) {
				cells.add(c);
			}
		}

		public int numberOfCells() {
			return cells.size();
		}

		public Cell getCell(int i) {
			return cells.get(i);
		}

		public Cell getResultingCell(int currentCellIndex, int r) {
			int size = cells.size();
			if (r == size) {
				return getCell(currentCellIndex);
			} else if (r < size) {
				int targetCellIndex = currentCellIndex + 1 + r;
				if (targetCellIndex > size)
					targetCellIndex = targetCellIndex % size;
				return getCell(targetCellIndex - 1);
			} else {
				int targetCellIndex = currentCellIndex + 1 + (r % size);
				if (targetCellIndex > size)
					targetCellIndex = targetCellIndex % size;
				return getCell(targetCellIndex - 1);
			}
		}

		public String toString() {
			return cells.toString();
		}
	}

	private static List<Layer> getLayers(int[][] array, int m, int n) {
		List<Layer> layers = new ArrayList<Layer>();

		int layerIndex = 0;
		while (true) {
			Layer layer = getLayer(m, n, layerIndex++);
			if (layer == null) {
				break;
			} else {
				layers.add(layer);
			}
		}
		return layers;
	}

	private static Layer getLayer(int r, int c, int layerIndex) {

		if (layerIndex < 0 || layerIndex > r || layerIndex > c) {
			return null;
		}

		int[] rowIndices = IntStream.rangeClosed(layerIndex, r - 1 - layerIndex).toArray();
		int rowIndexCount = rowIndices.length;
		int[] columnIndices = IntStream.rangeClosed(layerIndex, c - 1 - layerIndex).toArray();
		int columnIndexCount = columnIndices.length;

		if (rowIndexCount == 0 || columnIndexCount == 0) {
			return null;
		}

		Layer layer = new Layer();

		// left boundary, first column values from top to bottom
		for (int i = 0; i < rowIndexCount; i++) {
			layer.addCell(new Cell(rowIndices[i], columnIndices[0]));
		}

		// bottom boundary, all column values from left to right
		for (int j = 0; j < columnIndexCount; j++) {
			layer.addCell(new Cell(rowIndices[rowIndexCount - 1], columnIndices[j]));
		}

		// right boundary, last columne from bottom to top
		for (int i = rowIndexCount - 1; i >= 0; i--) {
			layer.addCell(new Cell(rowIndices[i], columnIndices[columnIndexCount - 1]));
		}

		// top boundary, all columns from left toright
		for (int j = columnIndexCount - 1; j >= 0; j--) {
			layer.addCell(new Cell(rowIndices[0], columnIndices[j]));
		}

		return layer;
	}

	private static int[][] getRotatedMatrix(int[][] array, int r, int c, int k, Layer l, int[][] result) {
		int size = l.numberOfCells();
		for (int i = 0; i < size; i++) {
			Cell currentCell = l.getCell(i);
			Cell targetCell = l.getResultingCell(i, k);
			result[targetCell.i][targetCell.j] = array[currentCell.i][currentCell.j];
		}
		return result;
	}

	private static void printMatrix(int[][] array, int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int k = scanner.nextInt();

		int array[][] = new int[r][c];

		for (int i = 0; i < r; i++) {
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			for (int j = 0; j < c; j++) {
				array[i][j] = scanner.nextInt();
			}
		}
		scanner.close();

		int[][] result = new int[r][c];
		List<Layer> layers = getLayers(array, r, c);
		for (int i = 0; i < layers.size(); i++) {
			Layer l = layers.get(i);
			result = getRotatedMatrix(array, r, c, k, l, result);
		}

		printMatrix(result, r, c);
	}

}
