package chapters.structures;

public class Node<T> {
    Node next;
    private T t;

    public Node(T t) {
        this.next = null;
        this.t = t;
    }
}
