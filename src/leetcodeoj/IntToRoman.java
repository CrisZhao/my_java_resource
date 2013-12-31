package leetcodeoj;

import org.junit.Test;

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 */
public class IntToRoman {
	public String intToRoman(int num) {
		char symbol[] = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
		int scale = 1000;
		StringBuilder sb = new StringBuilder();
		for (int i = 6; i >= 0; i -= 2) {
			int digit = num / scale;
			if (digit != 0) {
				if (digit <= 3) {
					for (int j = 0; j < digit; j++) {
						sb.append(symbol[i]);
					}
				} else if (digit == 4) {
					sb.append(symbol[i]).append(symbol[i + 1]);
				} else if (digit == 5) {
					sb.append(symbol[i + 1]);
				} else if (digit <= 8) {
					sb.append(symbol[i + 1]);
					for (int j = 0; j < digit - 5; j++) {
						sb.append(symbol[i]);
					}
				} else if (digit == 9) {
					sb.append(symbol[i]);
					sb.append(symbol[i + 2]);
				}
			}
			num = num % scale;
			scale /= 10;
		}
		return sb.toString();
	}

	public String fastIntToRoman(int num) {
		StringBuilder sb = new StringBuilder();
		String symbol[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		int value[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		for (int i = 0; num != 0; ++i) {
			while (num >= value[i]) {
				num -= value[i];
				sb.append(symbol[i]);
			}
		}
		return sb.toString();
	}
	
	@Test
	public void test() {
		int a= 1549;
		System.out.println(intToRoman(a));
		System.out.println(fastIntToRoman(a));
	}
}
