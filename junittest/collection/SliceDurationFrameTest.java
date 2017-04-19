package collection;

import collections.SliceDurationFrame;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chris Zhao on 2017/1/3.
 */
public class SliceDurationFrameTest {
    @Test
    public void test() {
        SliceDurationFrame frame = new SliceDurationFrame(250);
        frame.add(new DetailVo(11));
        frame.add(new DetailVo(101));
        frame.add(new DetailVo(110));
        frame.add(new DetailVo(123));
        frame.add(new DetailVo(132));
        Assert.assertFalse(frame.isFull());
        frame.add(new DetailVo(267));
        Assert.assertTrue(frame.isFull());
        Assert.assertEquals(101,frame.getStartTime());
        Assert.assertEquals(5, frame.getDetails().size());

        frame.add(new DetailVo(333));
        Assert.assertEquals(101,frame.getStartTime());
        Assert.assertEquals(6, frame.getDetails().size());

        frame.add(new DetailVo(500));
        Assert.assertEquals(267,frame.getStartTime());
        Assert.assertEquals(3, frame.getDetails().size());
    }
}
