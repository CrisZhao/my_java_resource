package math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chris Zhao on 2017/1/9.
 */
public class DoubleNumFormatTest {

    private static double delta = 0.001;

    @Test
    public void doubleNumFormatTest() {
       double d1 = 0;
       double d2 = 123.45678;
       double d3 = -123.45478;
       double r1 = DoubleNumFormat.format(2, d1);
       double r2 = DoubleNumFormat.format(2, d2);
       double r3 = DoubleNumFormat.format(2, d3);

       Assert.assertEquals("0.0", String.valueOf(r1));
       Assert.assertEquals("123.46", String.valueOf(r2));
       Assert.assertEquals("-123.45", String.valueOf(r3));

    }

}
