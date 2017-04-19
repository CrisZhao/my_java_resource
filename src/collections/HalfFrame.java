package collections;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Zhao on 2016/12/21.
 */
public class HalfFrame<T extends ITimeData> {
    private long startTime;
    private List<T> cache = new ArrayList<>();

    public void add(T data) {
        if (startTime <= 0) {
            startTime = data.getTime();
        }
        cache.add(data);
    }

    public List<T> getCache() {
        return cache;
    }

    public long getStartTime() {
        return startTime;
    }
}
