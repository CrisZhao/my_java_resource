package leetcodeoj;

import java.util.Arrays;

import org.junit.Test;

/**
 * Given a number represented as an array of digits, plus one to the number.
 * 
 */
public class PlusOne {

	public int[] plusOne(int[] digits) {

		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i] = digits[i] + 1;
				return digits;
			} else {
				digits[i] = 0;
			}
		}
		int result[] = new int[digits.length + 1];
		result[0] = 1;
		System.arraycopy(digits, 0, result, 1, digits.length);
		return result;
	}

	@Test
	public void test() {
		int digits[] = { 1, 9, 3 };
		System.out.println(Arrays.toString(plusOne(digits)));
	}
}
