package collections;

import java.util.ArrayList;
import java.util.List;

/**
 * frame保存capacity个样本,滑动sliceNum个数据
 * Created by Chris Zhao on 2017/1/11.
 */
public class OverlapSliceCountList<T> {

    private Object[] data;
    private int writeIndex;
    private int readIndex;
    private int size;
    private int capacity;
    private int sliceNum;

    /**
     * @param capacity 最大存储的个数
     * @param sliceNum 容器满后滑动个数
     */
    public OverlapSliceCountList(int capacity, int sliceNum) {
        this.capacity = capacity;
        this.sliceNum = sliceNum;
        this.data = new Object[capacity];
    }

    /**
     * if isFull will throw IndexOutOfBoundsException
     * @param detailVo para add
     */
    public void add(T detailVo) {
        if (size >= capacity) {
            throw new IndexOutOfBoundsException();
        }

        data[writeIndex] = detailVo;
        writeIndex = nextIndex(writeIndex);
        size++;

    }

    private List<T> getData() {
        List<T> result = new ArrayList<>(size);
        int readPos = readIndex;
        for (int i = 0; i < size; i++) {
            result.add((T) data[readPos]);
            readPos = nextIndex(readPos);
        }
        return result;
    }

    private void slice() {
        if (size >= capacity) {
            size = capacity - sliceNum;
            readIndex += sliceNum;
            if (readIndex >= capacity) {
                readIndex -= capacity;
            }
        }
    }

    public boolean isFull() {
        return size == capacity;
    }

    public List<T> getAndSlice() {
        List<T> result = getData();
        slice();
        return result;
    }

    private int nextIndex(int index) {
        index++;
        if (index >= capacity) {
            index = 0;
        }
        return index;
    }


}
