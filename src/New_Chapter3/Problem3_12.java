package New_Chapter3;

public class Problem3_12<AnyType> {
    private static class Node<Comparable> {
        public Comparable data;
        public Node<Comparable> next;

        public Node(Comparable d, Node<Comparable> n) {
            data = d;
            next = n;
        }

        public Node() {
            this(null, null);
        }
    }

    private Node<Comparable> head;
    private int theSize;

    public Problem3_12() {
        init();
    }

    private void init() {
        theSize = 0;
        head = new Node<Comparable>();
    }

    public boolean add(Comparable x) {
        if (contains(x))
            return false;
        else {
            Node<Comparable> current = head;
            Node<Comparable> parent = head;

            while (current != null && current.data.compareTo(x) < 0) {
                parent = current;
                current = current.next;
            }

            Node<Comparable> newNode = new Node<>(x, null);
            newNode.next = current;
            parent.next = newNode;
            theSize++;
        }

        return true;
    }

    public boolean remove(Comparable x) {

        Node<Comparable> current = head;
        Node<Comparable> parent = head;

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


    public boolean contains(Comparable x) {
        Node<Comparable> current = head.next;
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
        Node<Comparable> current = head.next;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

}
