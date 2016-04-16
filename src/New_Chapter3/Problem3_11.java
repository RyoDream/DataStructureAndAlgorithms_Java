package New_Chapter3;

public class Problem3_11<AnyType> {
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node(AnyType d, Node<AnyType> n) {
            data = d;
            next = n;
        }

        public Node() {
            this(null, null);
        }
    }

    private Node<AnyType> head;
    private int theSize;

    public Problem3_11() {
        init();
    }

    private void init() {
        theSize = 0;
        head = new Node<AnyType>();
    }

    public boolean add(AnyType x) {
        if (contains(x))
            return false;
        else {
            Node<AnyType> newNode = new Node(x, null);
            newNode.next = head.next;
            head.next = newNode;
            theSize++;
        }

        return true;
    }

    public boolean remove(AnyType x) {

        Node<AnyType> current = head;
        Node<AnyType> parent = head;

        while (current != null && !current.data.equals(x)) {
            parent = current;
            current = current.next;
        }

        if (current == null)
            return false;
        else {
            parent.next = current.next;
            theSize--;
            return true;
        }
    }


    public boolean contains(AnyType x) {
        Node<AnyType> current = head.next;
        while (current != null) {
            if (current.data.equals(x))
                return true;
            else
                current = current.next;
        }

        return false;
    }


    public int size() {
        return theSize;
    }

    public void print() {
        Node<AnyType> current = head.next;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }
}
