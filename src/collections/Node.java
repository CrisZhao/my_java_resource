package collections;

/**
 * Created by Chris Zhao on 2016/12/29.
 */
class Node<T> {
    protected Node<T> next;
    protected T value;



    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node(T value) {
        this.value = value;
    }


}
