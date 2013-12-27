package leetcodeoj;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length. Do not allocate extra space for
 * another array, you must do this in place with constant memory.
 * 
 * For example, Given input array A = [1,1,2], Your function should return
 * length = 2, and A is now [1,2].
 * 
 * 
 */
public class RemoveDupliates {
	public static int removeDuplicates(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		int index = 0;
		int temp = A[0];
		for (int i = 1; i < A.length; i++) {
			if (temp == A[i]) {
				continue;
			}
			index++;
			temp = A[i];
			A[index] = temp;

		}
		return index + 1;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 3, 3, 3, 4, 5, 6, 6, 7, 7 };
		System.out.println(removeDuplicates(a));
		System.out.println(a);
	}

}
