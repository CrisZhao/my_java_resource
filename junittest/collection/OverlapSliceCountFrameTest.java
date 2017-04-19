package collection;

import collections.OverlapSliceCountList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Chris Zhao on 2017/1/11.
 */
public class OverlapSliceCountFrameTest {
    @Test
    public void add() throws Exception {
        OverlapSliceCountList<DetailVo> frame = new OverlapSliceCountList<>(6, 3);
        DetailVo detailVo1 = new DetailVo(1);
        DetailVo detailVo2 = new DetailVo(2);
        DetailVo detailVo3 = new DetailVo(3);
        DetailVo detailVo4 = new DetailVo(4);
        DetailVo detailVo5 = new DetailVo(5);
        DetailVo detailVo6 = new DetailVo(6);
        DetailVo detailVo7 = new DetailVo(7);
        DetailVo detailVo8 = new DetailVo(8);

        frame.add(detailVo1);
        frame.add(detailVo2);
        frame.add(detailVo3);

        Assert.assertFalse(frame.isFull());

        frame.add(detailVo4);
        frame.add(detailVo5);
        frame.add(detailVo6);

        Assert.assertTrue(frame.isFull());

        List<DetailVo> result = frame.getAndSlice();

        Assert.assertEquals(6, result.size());

        frame.add(detailVo7);
        frame.add(detailVo8);

        Assert.assertFalse(frame.isFull());

        result = frame.getAndSlice();

        Assert.assertEquals(5, result.size());

    }

}