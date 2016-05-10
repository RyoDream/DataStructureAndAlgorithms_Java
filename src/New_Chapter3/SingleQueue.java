package New_Chapter3;

public class SingleQueue<AnyType> {
    private class Node<AnyType> {
        AnyType data;
        Node next;

        Node() {
            this(null, null);
        }

        Node(AnyType x) {
            this(x, null);
        }

        Node(AnyType x, Node p) {
            data = x;
            next = p;
        }
    }

    private Node front, rear;

    SingleQueue() {
        front = rear = null;
    }

    void enqueue(AnyType x) {
        Node p = new Node(x, null);
        if (rear != null)
            rear = rear.next = p;
        else
            front = rear = p;
    }

    AnyType dequeue() {
        AnyType temp = (AnyType) front.data;
        if (front.next == null)
            front = rear = null;
        else
            front = front.next;

        return temp;
    }
}
