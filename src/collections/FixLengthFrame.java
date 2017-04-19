package collections;


/**
 * 保存length个时间间隔为duration的frame
 * Created by Chris Zhao on 2016/12/21.
 */
public class FixLengthFrame<T extends ITimeData> {
    private int length;
    private long curFrameStart;
    private int index = -1;
    private HalfFrame<T>[] sliceCache;
    private int duration;

    public FixLengthFrame(int length, int duration) {
        this.length = length;
        this.duration = duration;
        sliceCache = new HalfFrame[length];
    }

    private int nextIndex() {
        int nextIndex = index + 1;
        nextIndex = nextIndex >= length ? 0 : nextIndex;
        index = nextIndex;
        return nextIndex;
    }

    public boolean canInsert(ITimeData data) {
        if (index == length - 1 && data.getTime() - curFrameStart > duration
                ) {
            return false;
        }
        return true;

    }


    public FixLengthFrame update(ITimeData data) {
        if (data.getTime() - curFrameStart > duration) {
            //change
            HalfFrame next = new HalfFrame();
            next.add(data);
            sliceCache[nextIndex()] = next;
            curFrameStart = data.getTime();
//            index++;
        } else {
            HalfFrame frame = sliceCache[index];
            frame.add(data);
        }
        return this;

    }

    public HalfFrame[] getSliceCache() {
        return sliceCache;
    }

    public long getCurFrameStart() {
        return curFrameStart;
    }
}
