package sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author Cris Zhao
 * @email zhaoqingce@gmail.com
 * 
 * An implementation of bubble sort algorithm.
 */
public class BubbleSort {

	public void sort(int[] array) {
		boolean didSwap;
		for (int i = array.length - 1; i > 0; i--) {
			didSwap = false;
			for (int j = 0; j < i; j++) {
				if (array[j + 1] < array[j]) {
					swap(array, j, j + 1);
					didSwap = true;
				}
			}
			//if no elements have been swapped, the list is sorted.
			if(didSwap == false) {
				return;
			}
			System.out.println(Arrays.toString(array));
		}
	}

	private void swap(int x[], int a, int b) {
		int t = x[a];
		x[a] = x[b];
		x[b] = t;
	}

	@Test
	public void test() {
		int[] array = new int[] { 6, 5, 3, 1, 2 };
		sort(array);
	}

}
