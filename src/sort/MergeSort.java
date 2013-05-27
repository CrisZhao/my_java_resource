package sort;

/**
 * An implementation of merge sort algorithm.
 * @author Cris Zhao
 * @email  zhaoqingce@gmail.com
 */
public class MergeSort {
	
	public void sort(int[] array) {
		mergeSort(array, 0, array.length-1);
	}

	public void mergeSort(int[] array, int low, int high) {
		if (low < high) {
		int center = (low + high) / 2;
		mergeSort(array, low, center);
		mergeSort(array, center + 1, high);
		merge(array, low, center + 1, high);
		}
	}

	/**
	 * merge sort algorithm
	 * @param array an array to sort
	 * @param low left-most index of the left subarray
	 * @param center left-most index of the right subarray
	 * @param high right-most index of the right subarray
	 */
	private void merge(int[] array, int low, int center, int high) {
		int[] temp = new int[high - low + 1];
		int leftIndex = low;
		int rightIndex = center;
		int tempIndex = 0;
		while (leftIndex < center && rightIndex < high + 1) {
			if (array[leftIndex] < array[rightIndex]) {
				temp[tempIndex++] = array[leftIndex++];
			} else {
				temp[tempIndex++] = array[rightIndex++];
			}
		}
		// copy the rest elements of the left half subarray
		while (leftIndex < center) {
			temp[tempIndex++] = array[leftIndex++];
		}
		// copy the rest elements of the right half subarray
		while (rightIndex < high + 1) {
			temp[tempIndex++] = array[rightIndex++];
		}
		// copy temp array back
		while (tempIndex > 0) {
			array[high--] = temp[--tempIndex];
		}

	}
	

}
