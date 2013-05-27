package sort;

public class EffictiveQuickSort {

	public void sort(int[] array) {
		quickSort(array, 0, array.length - 1);

	}

	private void quickSort(int[] array, int low, int high) {
		if (high <= low) {
			return;
		}
		int lo = low;
		int hi = high;
		int mid = array[(low + high) / 2];

		while (true) {
			while ((lo < high) && (array[lo] < mid))
				lo++;
			while ((hi > low) && (array[hi] > mid))
				hi--;
			if (lo > hi)
				break;
			if (lo < hi) {
				SwapUtils.swap(array, lo, hi);
			}
			lo++;
			hi--;
		}
		if (low < hi)
			quickSort(array, low, hi);
		if (lo < high)
			quickSort(array, lo, high);
	}

}
