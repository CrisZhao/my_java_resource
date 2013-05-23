package sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author Cris Zhao
 * @email  zhaoqingce@gmail.com
 * An implementation of selection sort algorithm.
 */
public class SelectionSort {
	public void sort(int[] array) {
		for (int i = 0; i < array.length-1; i++) {
			int minIndex = i;
			for (int j = i+1; j < array.length; j++) {
				if(array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			if(minIndex!=i) {
				SwapUtils.swap(array, i, minIndex);
			}
			System.out.println(Arrays.toString(array));
		}
	}
	@Test
	public void test() {
		int[] array = new int[] {4,2,7,3,6,8,2};
		sort(array);
		System.out.println(Arrays.toString(array));
	}

}
