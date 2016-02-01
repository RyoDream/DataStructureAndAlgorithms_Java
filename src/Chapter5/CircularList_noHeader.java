package Chapter5;


public class CircularList_noHeader {

    private Node current;

    public CircularList_noHeader() {
        current = null;
    }

    public boolean isEmpty() {
        return (current == null);
    }

    public void insertAfterCurrent(int id, double dd) {
        Node newNode = new Node(id, dd);

        if (isEmpty()) {
            current = newNode;
        } else {
            newNode.next = current.next;
        }

        current.next = newNode;
    }

    public Node removeAfterCurrent() {
        if (isEmpty())
            return null;

        if (current.next == current) {
            Node temp = current;
            current.next = null;
            current = null;
            return temp;
        }

        Node temp = current.next;
        current.next = temp.next;
        temp.next = null;
        return temp;
    }

    public void step() {
        if (current == null)
            return;

        current = current.next;
    }

    public Node findNode(int key) {
        if (current.iData == key)
            return current;

        Node temp = current;

        while (current != temp && current.iData != key) {
            current = current.next;
        }

        if (current.iData == key)
            return current;
        else
            return null;
    }

    public void displayList() {
        if (isEmpty())
            System.out.println("List is empty.");

        Node temp = current;
        do {
            current.displayNode();
            step();
        } while (current != temp);
    }

    public static void main(String[] args) {
        CircularList_noHeader circularList = new CircularList_noHeader();
        circularList.insertAfterCurrent(1, 1.1);
        circularList.insertAfterCurrent(2, 2.2);
        circularList.insertAfterCurrent(3, 3.3);
        circularList.insertAfterCurrent(4, 4.4);
        circularList.insertAfterCurrent(5, 5.5);

        circularList.displayList();

        circularList.removeAfterCurrent().displayNode();
        circularList.removeAfterCurrent().displayNode();

        circularList.displayList();
    }
}
