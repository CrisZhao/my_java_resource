package collections;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 只保存durationo时间长度的数据
 * Created by Chris Zhao on 2016/12/21.
 */
public class SliceDurationFrame<T extends ITimeData> {
    private int duration;
    private Queue<T> details = new LinkedList<>();
    private boolean full;

    public SliceDurationFrame(int duration) {
        this.duration = duration;
    }

    public void add(T data) {
        details.add(data);
        reStruct(data);
    }

    private void reStruct(ITimeData data) {
        ITimeData first = details.peek();
        while (first!=null && data.getTime() - first.getTime() > duration) {
            full = true;
            details.remove();
            reStruct(data);
            first = details.peek();
        }
    }

    public long getStartTime() {
        if(details.isEmpty()) {
            return 0;
        }
        return details.peek().getTime();

    }

    public boolean isFull() {
        return full;
    }

    public Queue<T> getDetails() {
        return details;
    }
}
