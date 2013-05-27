package sort;

/**
 * @author Cris Zhao
 * @email zhaoqingce@gmail.com
 * 
 * An implementation of cocktail sort algorithm.
 */
public class CocktailSort {
	public void sort(int[] array) {
		int bottom = 0;
		int top = array.length - 1;
		boolean didSwap = true;
		// if no elements have been swapped, then the array is sorted
		while (didSwap == true) {
			didSwap = false;
			for (int i = bottom; i < top; i++) {
				if (array[i] > array[i + 1]) {
					SwapUtils.swap(array, i, i + 1);
					didSwap = true;
				}
			}
			top--;
			// decreases top because the element with the largest value in
			// the unsorted part of the list is now on the position top
			for (int i = top; i > bottom; i--) {
				if (array[i] < array[i - 1]) {
					SwapUtils.swap(array, i, i - 1);
					didSwap = true;
				}
			}
			// increases bottom because the element with the smallest value in
			// the unsorted part of the list is now on the position bottom
			bottom++;
		}
	}


}
