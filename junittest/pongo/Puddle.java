package pongo;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: Cris Zhao
 * Date: 13-11-8
 * Time: 上午10:51
 */
public class Puddle {

    public int calculate(int[] testCase) {
        int l = 0;
        int r = testCase.length - 1;
        int maxl = testCase[0];
        int maxr = testCase[r];

        int volume = 0;
        while (l < r) {
            if (maxl < maxr) {
                l++;
                if (testCase[l] < maxl) {
                    volume += maxl - testCase[l];
                } else {
                    maxl = testCase[l];
                }
            } else {
                r--;
                if (testCase[r] < maxr) {
                    volume += maxr - testCase[r];
                } else {
                    maxr = testCase[r];
                }
            }
        }
        return volume;
    }

    @Test
    public void test() {
        int[] test1 = {2, 5, 1, 2, 3, 4, 7, 7, 6};
        int[] test2 = {2, 5, 1, 3, 1, 2, 1, 7, 7, 6};
        int[] test3 = {6, 1, 4, 6, 7, 5, 1, 6, 4};

        Assert.assertEquals(10, calculate(test1));
        Assert.assertEquals(17, calculate(test2));
        Assert.assertEquals(13, calculate(test3));
    }
}
