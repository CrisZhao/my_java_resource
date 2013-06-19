package sort;

import org.junit.Test;

public class HeapSort {
	private void heapify(int[] array, int length, int index) {
		int left = left(index);
		int right = right(index);
		int maxIndex = index;
		if (left < length) {
			maxIndex = array[left] > array[maxIndex] ? left : maxIndex;
		}
		if (right < length) {
			maxIndex = array[right] > array[maxIndex] ? right : maxIndex;
		}
		if (maxIndex != index) {
			SwapUtils.swap(array, maxIndex, index);
			heapify(array, length, maxIndex);
		}

	}

	private void buildHeap(int[] array, int length) {
		int index;
		index = (length - 1) / 2;
		for (int j = index; j >= 0; j--) {
			heapify(array, length, j);
		}

	}

	public void sort(int[] array) {
		buildHeap(array, array.length);
		int heapNum = array.length;
		while (heapNum > 1) {
			SwapUtils.swap(array, 0, --heapNum);
			heapify(array, heapNum, 0);
		}

	}

	@Test
	public void tst() {
		int[] array = new int[] { 2, 4, 5, 6, 8, 9, 3, 2, 1 };
		sort(array);
		System.out.println(array);
	}

	private int left(int index) {
		return 2 * index + 1;
	}

	private int right(int index) {
		return 2 * index + 2;
	}

}
