package New_Chapter3;

public class SingleStack<AnyType> {
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

    Node head;

    SingleStack() {
        head = null;
    }

    void push(AnyType x) {
        Node<AnyType> p = new Node<>(x, head);
        head = p;
    }

    AnyType top() {
        return (AnyType) head.data;
    }

    AnyType pop() {
        AnyType value = (AnyType) head.data;
        head = head.next;
        return value;
    }
}
