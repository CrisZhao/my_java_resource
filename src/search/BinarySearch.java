package search;

import org.junit.Test;

public class BinarySearch {

	public int binarySearch(int[] array, int n, int value) {
		return search(0, n, array, value);
	}

	private int search(int low, int high, int[] array, int value) {
		if (high > array.length) {
			high = array.length;
		}
		if (low > high) {
			return -1;
		}
		int middle = (low + high) / 2;
		if (array[middle] == value) {
			return middle;
		}
		if (array[middle] < value) {
			return search(middle + 1, high, array, value);
		} else {
			return search(low, middle - 1, array, value);
		}
	}

	@Test
	public void test() {
		int[] array = { 1, 3, 5, 6, 7, 8 };
		System.out.println(binarySearch(array, 7, 8));
	}

}
