package Chapter5;

/**
 * list with first and lsat references
 */
public class FirstLastList {
    private Node first;
    private Node last;

    public FirstLastList() {
        first = last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int id, double dd) {
        Node newNode = new Node(id, dd);

        if(isEmpty())
            last = newNode;

        newNode.next = first;
        first = newNode;
    }

    public void insertLast(int id, double dd) {
        Node newNode = new Node(id, dd);

        if(isEmpty())
            first = newNode;
        else
            last.next = newNode;

        last = newNode;
    }

    public Node deleteFirst() {
        if(isEmpty())
            return null;

        Node temp = first;
        first = first.next;
        temp.next = null;

        // if the list empty now
        if(first == null)
            last = null;

        return temp;
    }

    public Node deleteLast() {
        if(isEmpty())
            return null;

        Node res = last;

        if(first == last) {
            first = last = null;
            return res;
        }
        else {
            Node current = first;
            while (current.next != last)
                current = current.next;
            current.next = null;
            last = current;
        }
        return res;
    }

    public void displayList() {
        System.out.println("List (first --> last): ");
        Node current = first;
        while(current != null) {
            current.displayNode();
            current = current.next;
        }
    }

    public static void main(String[] args) {
        FirstLastList list = new FirstLastList();

        list.insertFirst(22, 2.99);
        list.insertFirst(44, 4.99);
        list.insertFirst(66, 6.99);
        list.insertFirst(88, 8.99);

        list.insertLast(11, 1.99);
        list.insertLast(33, 3.99);
        list.insertLast(55, 5.99);

        list.displayList();

        while (!list.isEmpty()) {
            Node node = list.deleteLast();
            System.out.print("Deleted: ");
            node.displayNode();
        }

        list.displayList();

    }
}
