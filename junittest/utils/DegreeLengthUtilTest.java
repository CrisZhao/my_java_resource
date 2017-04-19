package utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chris Zhao on 2017/3/7.
 */
public class DegreeLengthUtilTest {
    private static double delta = 1;
    @Test
    public void convertLatitudeTest() {
        double length = DegreeLengthUtil.convertLatitude(delta);
        Assert.assertEquals(111194, length, delta);

    }

    @Test
    public void convertLongitudeTest() {
        double length = DegreeLengthUtil.convertLongitude(35, delta);
        Assert.assertEquals(91085, length, delta);
    }

    @Test
    public void lengthTest () {
        double disLon = DegreeLengthUtil.distanceHav(35,121,35,122);
        double disLat = DegreeLengthUtil.distanceHav(35,120,34,120);
        Assert.assertEquals(91085, disLon, delta);
        Assert.assertEquals(111194, disLat, delta);

    }

}
