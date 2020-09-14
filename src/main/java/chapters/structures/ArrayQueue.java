package chapters.structures;
/**
    ArrayQueue using array realization
 */
public class ArrayQueue {
    private int[] queue;
    private int maxSize;
    private int currentElementsSize;
    private int front;
    private int rear;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        rear = -1;
        front = 0;
        currentElementsSize = 0;
    }

    public void insert(int elem) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queue[++rear] = elem;
        currentElementsSize++;
    }

    public int remove() {
        int temp = queue[front++];

        if (front == maxSize) {
            front = 0;
        }
        currentElementsSize--;
        return temp;
    }

    public int getFront() {
        return queue[front];
    }

    public int getRear() {
        return queue[rear];
    }

    public boolean isFull() {
        return (currentElementsSize == maxSize - 1);
    }

    public boolean isEmpty() {
        return (currentElementsSize == 0);
    }

    public int getSize() {
        return currentElementsSize;
    }
}
