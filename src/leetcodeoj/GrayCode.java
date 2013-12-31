package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0 
 * 01 - 1 
 * 11 - 3 
 * 10 - 2
 *  
 * Note: For a given n, a gray code sequence is not uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code
 * sequence. Sorry about that.
 * 
 */
public class GrayCode {

	public ArrayList<Integer> grayCode(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(0);
		for (int i = 0; i < n; i++) {
			int length = result.size();
			int bit = 1 << i;
			for (int j = length - 1; j >= 0; j--) {
				result.add(bit + result.get(j));
			}
		}
		return result;
	}

	public int grayToBinary(int grayNum) {
		for (int mask = grayNum >> 1; mask != 0; mask = mask >> 1) {
			grayNum = grayNum ^ mask;
		}
		return grayNum;
	}

	public ArrayList<Integer> fastGrayCode(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int length = 1 << n;
		for (int i = 0; i < length; i++) {
			result.add((i >> 1) ^ i);
		}
		return result;
	}

	// my poor solution before google graycode
	public ArrayList<Integer> poorGrayCode(int n) {
		ArrayList<Integer> re = new ArrayList<Integer>();
		int[] base = { 0, 1, 1, 0 };

		for (int i = 0; i < 1 << n; i++) {
			int result = 0;
			for (int j = n - 1; j >= 0; j--) {
				int pow = 1 << j;
				result += base[(i / pow) % 4] * pow;
			}
			re.add(result);
			result = 0;
		}
		return re;
	}

	@Test
	public void test() {
		System.out.println(fastGrayCode(4));
	}
}
