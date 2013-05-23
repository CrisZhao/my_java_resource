package sort;

import java.util.Arrays;

import org.junit.Test;

public class SwapUtils {
	public static  void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	@Test
	public void test() {
		int[] array = new int[] {4,2,6,3,6,8,2};
//		sort(array);
		System.out.println(Arrays.toString(array));
	}
}
