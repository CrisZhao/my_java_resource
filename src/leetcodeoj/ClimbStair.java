package leetcodeoj;

import org.junit.Test;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * 
 */
public class ClimbStair {

	public int climbStairs(int n) {
		if (n == 0 || n == 1 || n == 2) {
			return n;
		}
		int step1 = 1;
		int step2 = 2;
		int result = 0;
		for (int i = 3; i <= n; i++) {
			result = step1 + step2;
			step1 = step2;
			step2 = result;
		}
		return result;
	}

	//it is slow for large n
	public int recursion(int n) {
		if (n == 0 || n == 1 || n == 2) {
			return n;
		}
		return climbStairs(n - 1) + climbStairs(n - 2);
	}

	@Test
	public void test() {
		System.out.println(climbStairs(38));
	}
}
