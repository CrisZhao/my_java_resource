package leetcodeoj;

import org.junit.Test;

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author Cris Zhao
 * 
 */
public class RomanToInt {

	public int romanToInt(String s) {
		int result = 0;
		int pre = 1000;
		for (char c : s.toCharArray()) {
			int current = toNum(c);
			result += current;
			if (current > pre) {
				result -= 2 * pre;
			}
			pre = current;
		}
		return result;

	}

	public int toNum(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

	@Test
	public void test() {
		String s = "VI";
		System.out.println(romanToInt(s));
	}
}
