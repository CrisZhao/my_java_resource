package leetcodeoj;

import org.junit.Test;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 * 
 */
public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		return maxArea(height, 0, height.length - 1);
	}

	public int maxArea1(int[] height) {
		int low = 0;
		int high = height.length - 1;
		int maxArea = 0;
		while (low < high) {
			maxArea = Math.max(maxArea,
					(high - low) * Math.min(height[low], height[high]));
			if (height[low] < height[high]) {
				low++;
			} else {
				high--;
			}
		}
		return maxArea;
	}

	private int maxArea(int[] height, int left, int right) {
		int length = right - left;
		if (length < 1) {
			return 0;
		}

		int low;
		int l = left;
		int r = right;
		if (height[l] <= height[r]) {
			low = height[l];
			for (int i = l + 1; i <= r; i++) {
				if (height[i] > low) {
					l = i;
					break;
				}
			}
		} else {
			low = height[r];
			for (int i = r - 1; i >= l; i--) {
				if (height[i] > low) {
					r = i;
					break;
				}
			}
		}
		int result = low * (right - left);
		if (l == left && r == right) {
			return result;
		}
		int subresult = maxArea(height, l, r);

		return result >= subresult ? result : subresult;
	}

	@Test
	public void test() {
		int height[] = { 1, 1 };
		System.out.println(maxArea(height));
	}
}
