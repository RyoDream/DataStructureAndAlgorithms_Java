package Chapter13;

public class Queue {
    private final int SIZE = 20;
    private int[] queArray;
    private int front;
    private int rear;

    public Queue() {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int value) {
        if (rear == SIZE - 1)
            rear = -1;
        queArray[++rear] = value;
    }

    public int remove() {
        int temp = queArray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    public boolean isFull() {
        return (size() == SIZE);
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        if (rear >= front)
            return (rear - front + 1);
        else
            return (SIZE + rear - front + 1);
    }
}
