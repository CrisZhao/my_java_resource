package leetcodeoj;

import org.junit.Test;

/**
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321 Example2: x = -123, return -321
 * 
 * @author Cris Zhao
 * 
 */
public class ReverseInteger {

	public int reverse(int x) {
		boolean flag = false;
		if (x < 0) {
			flag = true;
			x = -x;
		}
		int result = 0;
		while (x > 0) {
			result = result * 10 + x % 10;
			x /= 10;
		}
		if (result < 0) {
			return -1;
		}
		if (flag) {
			return -result;
		}
		return result;
	}
	
	@Test
	public void test() {
		int a = 123;
		System.out.println(reverse(a));
		System.out.println(reverse(-a));
	}
}
