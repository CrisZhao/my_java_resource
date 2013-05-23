package sort;

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
		}
	}

}
