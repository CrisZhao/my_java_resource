package sort;

public class HeapSort {
	private void adjustOnce(int[] array, int index) {
		int length = array.length;
		int left = 2*index +1;
		int right = 2*index +2;
		int smallestIndex = index;
			if(left<length && array[left] < array[smallestIndex]){
				smallestIndex = left;
			}
			if(right<length && array[right] < array[smallestIndex]){
				smallestIndex = right;
			}
			if(smallestIndex!=index) {
				SwapUtils.swap(array, smallestIndex, index);
			}
		
		
	}
	public void sort(int[] array) {
		int index;
			index = (array.length-1)/2;
			for (int j = index; index>=0;index--) {
				adjustOnce(array, j);
			}
			
	}
	

}
