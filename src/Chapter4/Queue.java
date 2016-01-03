package Chapter4;

public class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;

    public Queue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
    }

    public void insert(long value) {

        if(isFull()) {
            System.out.println("Queue is Full! Fail to insert "+value+".");
            return;
        }

        if(rear == maxSize-1)
            rear = -1;

        queArray[++rear] = value;
    }

    public long remove() {

        if(isEmpty()) {
            System.out.println("Queue is Empty! Fail to remove.");
            return -1;
        }

        long temp = queArray[front++];
        if(front == maxSize)
            front = 0;

        return temp;
    }

    public long peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public boolean isFull() {
        return (size() == maxSize);
    }

    public int size() {
        if(rear == -1)
            return 0;

        if(rear >= front)
            return (rear - front +1);
        else
            return (maxSize + rear - front + 1);
    }

    public void display() {
        if(size() == 0) {
            System.out.println("Empty!");
            return;
        }

        System.out.println("Queue has "+size()+" entries.");
        if(front <= rear) {
            for(int i=front;i<=rear;i++)
                System.out.print(queArray[i]+" ");
            System.out.println("");
        }
        else {
            for(int i=front;i<maxSize;i++)
                System.out.print(queArray[i]+" ");
            for(int i=0;i<=rear;i++)
                System.out.print(queArray[i]+" ");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue(10);

        for(int i=0;i<10;i++)
            queue.insert(i+3);

        queue.display();

        queue.insert(13);

        queue.remove();
        queue.remove();
        queue.insert(14);

        queue.display();

        for(int i=0;i<8;i++) {
            System.out.println("Removed: "+queue.remove());
        }

        queue.display();

        queue.insert(15);
        queue.insert(16);

        queue.display();
    }

}
