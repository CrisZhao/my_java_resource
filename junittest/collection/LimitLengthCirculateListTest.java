package collection;

import collections.LimitLengthCirculateList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Chris Zhao on 2016/12/28.
 */
public class LimitLengthCirculateListTest {

    @Test
    public void test1() {
        LimitLengthCirculateList<Integer> resultList = new LimitLengthCirculateList<>(4);
        resultList.add(1);
        List<Integer> result = resultList.getResult();
        Assert.assertEquals(1, result.size());

    }

    @Test
    public void test2() throws Exception {
        LimitLengthCirculateList<Integer> resultList = new LimitLengthCirculateList<>(4);
        resultList.add(1);
        resultList.add(2);
        List<Integer> result = resultList.getResult();
        Assert.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            int value =  result.get(i);
            Assert.assertEquals(i+1, value);
        }
    }

    @Test
    public void test5() throws Exception {
        LimitLengthCirculateList<Integer> resultList = new LimitLengthCirculateList<>(4);
        resultList.add(1);
        resultList.add(2);
        resultList.add(3);
        resultList.add(4);
        resultList.add(5);
        List<Integer> result = resultList.getResult();
        Assert.assertEquals(4, result.size());
        for (int i = 0; i < result.size(); i++) {
            int value =  result.get(i);
            Assert.assertEquals(i+2, value);
        }
    }
}
