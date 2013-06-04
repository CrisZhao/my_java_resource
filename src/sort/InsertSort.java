package sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author Cris Zhao
 * @email zhaoqingce@gmail.com
 * 
 *        An implementation of insert sort algorithm.
 */
public class InsertSort {

	public void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int index = i;
			int temp = array[i];
			while (index > 0 && temp < array[index - 1]) {
				array[index] = array[index - 1];
				index--;
			}
			array[index] = temp;
			System.out.println(Arrays.toString(array));
		}
	}
	
	@Test
	public void test() {
		int[] array = new int[]{6,2,4,6,8,2,7,9,2,4,3};
		System.out.println(Arrays.toString(array));
		sort(array);
		System.out.println(Arrays.toString(array));
	}

}
