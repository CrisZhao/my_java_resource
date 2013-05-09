package math;

public class Sort {
	private static Sort instance = new Sort();

	public static Sort getInstance() {
		return instance;
	}
	private int[] order;

	public int[] getOrder() {
		return order;
	}

	private void QuickSort(int[] a, int lo0, int hi0) {
		int lo = lo0;
		int hi = hi0;
		int mid;
		if (hi0 > lo0) {
			mid = a[(lo0 + hi0) / 2];

			while (lo <= hi) {
				while ((lo < hi0) && (a[lo] < mid))
					++lo;
				while ((hi > lo0) && (a[hi] > mid))
					--hi;
				if (lo <= hi) {
					swap(a, lo, hi);
					++lo;
					--hi;
				}
			}
			if (lo0 < hi)
				QuickSort(a, lo0, hi);
			if (lo < hi0)
				QuickSort(a, lo, hi0);
		}
	}

	private void swap(int[] a, int i, int j) {
		int tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
		int t;
		t = order[i];
		order[i] = order[j];
		order[j] = t;
	}

	public void sort(int[] a) {
//		order = a.clone();
		order = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			order[i] = i;
		}

		QuickSort(a, 0, a.length - 1);
	}

}
