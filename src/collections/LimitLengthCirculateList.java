package collections;

import java.util.ArrayList;
import java.util.List;

/**
 * 长度限制为length的循环链表，not thread safe
 * FIFO
 * size>=length时，替换掉curNode.next.value
 * 即此时链表中存在最久的值,不替换完整node对象
 * Created by Chris Zhao on 2016/12/29.
 */
public class LimitLengthCirculateList<T> {
    private int size;
    private int length;
    private Node<T> curNode;

    public LimitLengthCirculateList(int length) {
        this.length = length;
    }

    public void add(T t) {
        if (size < length) {
            addNewNode(t);
            size++;
        } else {
            replace(t);
        }
    }

    private void replace(T t) {
        curNode.next.value = t;
        curNode = curNode.next;
    }


    private void addNewNode(T t) {
        if (curNode == null) {
            curNode = new Node<T>(t);
            curNode.next = curNode;
        } else {
            Node<T> node = new Node<T>(t);
            node.next = curNode.next;
            curNode.next = node;
            curNode = node;
        }
    }

    public List<T> getResult() {
        ArrayList<T> result = new ArrayList<T>(size);
        if (curNode == null) {
            return result;
        }

        Node<T> node = curNode.next;
        while (node != curNode) {
            result.add(node.value);
            node = node.next;
        }
        result.add(curNode.value);
        return result;
    }

    public boolean isFull() {
        return size==length;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        curNode = null;
        size = 0;
    }


}
