package chapters.structures;

public class Queue {
    private Node front, rear;

    public Queue(Node front, Node rear) {
        this.front = front;
        this.rear = rear;
    }

    void enqueue(int key) {
        Node temp = new Node<>(key);

        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }
        this.front = temp;
        this.rear = temp;
    }

    Node dequeue() {
        if (this.front == null)
            return null;
        Node temp = this.front;
        this.front = this.front.next;

        if (this.front == null)
            this.rear = null;
        return temp;
    }
}
