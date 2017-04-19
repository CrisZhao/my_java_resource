package collection;

import collections.HalfFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Chris Zhao on 2017/1/3.
 */
public class HalfFrameTest {
    private Collection<DetailVo> details;
    @Before
    public void createTestIns() {

        details = new ArrayList<>();
        details.add(new DetailVo(11));
        details.add(new DetailVo(101));
        details.add(new DetailVo(110));
        details.add(new DetailVo(123));
        details.add(new DetailVo(132));
    }

    @Test
    public void test() {
        HalfFrame halfFrame = new HalfFrame();
        for (DetailVo vo : details) {
            halfFrame.add(vo);
        }
        Assert.assertEquals(11, halfFrame.getStartTime());
        Assert.assertEquals(5, halfFrame.getCache().size());


    }
}
