package collection;

import collections.ITimeData;

/**
 * Created by Chris Zhao on 2017/3/7.
 */
public class DetailVo implements ITimeData {

    private long time;

    public DetailVo(long time) {
        this.time = time;
    }

    @Override
    public long getTime() {
        return time;
    }
}
