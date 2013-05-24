package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * An implementation of insert sort algorithm.
 * 
 * @author Cris Zhao
 * @email zhaoqingce@gmail.com
 */
public class QuickSort {

	public void sort(int[] array) {
		quickSort(array, 0, array.length - 1);

	}

	private int partition(int[] array, int low, int high) {
		int index = low;
		int value = getPartitionValue(array, low, high);
		for (int i = low; i < high; i++) {
			if (array[i] <= value) {
				SwapUtils.swap(array, index++, i);
				System.out.println(Arrays.toString(array));
			}
		}
		SwapUtils.swap(array, index, high);
		return index;
	}

	private int getPartitionValue(int[] array, int low, int high) {
		int r = new Random().nextInt(high - low + 1);
		// put the value in the end of the array
		SwapUtils.swap(array, low + r, high);
		return array[high];
	}

	private void quickSort(int[] array, int low, int high) {
		if (high <= low) {
			return;
		}
		int index = partition(array, low, high);
		quickSort(array, low, index - 1);
		quickSort(array, index + 1, high);
	}

}
