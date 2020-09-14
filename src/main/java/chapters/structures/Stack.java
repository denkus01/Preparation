package chapters.structures;

public class Stack {
    private int size;
    private int[] stackArray;
    private int top;

    public Stack(int InputSize) {
        this.size = InputSize;
        this.stackArray = new int[size];
        this.top = -1;
    }

    public void addElement(int element) {
        stackArray[++top] = element;
    }

    public int deleteElement() {
        return stackArray[top--];
    }

    public int read() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}
