package leetcodeoj;

import org.junit.Test;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Some hints: Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the restriction
 * of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might overflow.
 * How would you handle such case?
 * 
 * There is a more generic way of solving this problem.
 */
public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}

		while (x != 0) {
			int l = x / div;
			int r = x % 10;
			if (l != r) {
				return false;
			}
			x = (x % div) / 10;
			div = div / 100;
		}
		return true;
	}
	
	@Test
	public void test() {
		System.out.println(isPalindrome(101));
	}
}
