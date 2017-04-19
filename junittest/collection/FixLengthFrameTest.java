package collection;

import collections.FixLengthFrame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Chris Zhao on 2017/1/3.
 */
public class FixLengthFrameTest {




    @Test
    public void test() {
        Collection<DetailVo> details = new ArrayList<>();
        details.add(new DetailVo(1011));
        details.add(new DetailVo(1101));
        details.add(new DetailVo(1120));
        details.add(new DetailVo(1213));
        details.add(new DetailVo(1232));
        details.add(new DetailVo(1308));


        FixLengthFrame<DetailVo> frame = new FixLengthFrame<>(3, 100);
        for (DetailVo vo : details) {
            frame.update(vo);
        }
        Assert.assertEquals(1232, frame.getCurFrameStart());
        Assert.assertEquals(3, frame.getSliceCache().length);
        Assert.assertTrue(frame.canInsert(new DetailVo(1309)));
        Assert.assertFalse(frame.canInsert(new DetailVo(1333)));


    }


}
