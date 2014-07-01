package leetcodeoj;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: Cris Zhao
 * Date: 14-7-1
 * Time: 下午6:04
 */
public class TrappingRainWater {
    public int trap(int[] A) {
        int secHight = 0;
        int left = 0;
        int right = A.length - 1;
        int area = 0;
        while (left < right) {
            if (A[left] < A[right]) {
                secHight = Math.max(A[left], secHight);
                area += secHight - A[left];
                left++;
            } else {
                secHight = Math.max(A[right], secHight);
                area += secHight - A[right];
                right--;
            }
        }
        return area;
    }

    @Test
    public void test() {
        int[] test1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Assert.assertEquals(6, trap(test1));

    }
}
