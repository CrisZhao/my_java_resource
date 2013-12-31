package leetcodeoj;

import org.junit.Test;

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * 
 * @author Cris Zhao
 * 
 */
public class MaxProfitII {

	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int min = prices[0];
		int result = 0;
		for (int i = 0; i < prices.length; i++) {
			int delta = prices[i] - min;
			if (delta < 0) {
				min = prices[i];
			} else if (delta > result) {
				result = delta;
			}

		}
		return result;
	}

	// solution2 find max subarray
	public int maxProfit2(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 1; i < prices.length; i++) {
			int delta = prices[i] - prices[i - 1];
			sum += delta;
			if (sum > max) {
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		return max > 0 ? max : 0;
	}

	@Test
	public void test() {
		int a[] = { 1,2 };
		System.out.println(maxProfit(a));
	}
}
