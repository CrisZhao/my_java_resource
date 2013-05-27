package sort;

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

	private int partition(int[] array, int left, int right) {
		int i = left, j = right;
		int pivot = array[(left + right) / 2];

		while (i <= j) {
			while (array[i] < pivot)
				i++;
			while (array[j] > pivot)
				j--;
			if (i <= j) {
				SwapUtils.swap(array, i, j);
				i++;
				j--;
			}
		}
		;

		return i;
	}

	private void quickSort(int arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

}
