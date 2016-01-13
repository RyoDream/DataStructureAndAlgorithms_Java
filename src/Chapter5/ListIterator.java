package Chapter5;


public class ListIterator {
    private Node current;
    private Node previous;
    private LinkList ourList;

    public ListIterator(LinkList list) {
        ourList = list;
        reset();
    }

    public void reset() {
        current = ourList.getFirst();
        previous = null;
    }

    public boolean atEnd() {
        return (current.next == null);
    }

    public void nextNode() {
        if (!atEnd()) {
            previous = current;
            current = current.next;
        }
    }

    public Node getCurrent() {
        return current;
    }

    public void insertAfter(int id, double dd) {
        Node newNode = new Node(id, dd);

        if (ourList.isEmpty()) {
            ourList.setFirst(newNode);
            current = newNode;
        } else {
            newNode.next = current.next;
            current.next = newNode;
            nextNode();      // point to new Node
        }
    }

    public void insertBefore(int id, double dd) {
        Node newNode = new Node(id, dd);

        if (previous == null) {
            newNode.next = ourList.getFirst();
            ourList.setFirst(newNode);
            reset();
        } else {
            newNode.next = current;
            previous.next = newNode;
            current = newNode;
        }
    }

    public Node deleteCurrent() {
        if (ourList.isEmpty())
            return null;

        Node temp = current;

        if (previous == null) {
            ourList.setFirst(current.next);
            temp.next = null;
            reset();
        } else {
            previous.next = temp.next;
            if (atEnd())
                reset();
            else
                current = temp.next;

            temp.next = null;
        }

        return temp;
    }

    public static void main(String[] args) {
        LinkList list = new LinkList();
        ListIterator iterator = list.getIterator();

        iterator.insertAfter(11, 1.99);
        iterator.insertAfter(22, 2.99);
        iterator.insertAfter(33, 3.99);

        iterator.insertBefore(44, 4.99);
        iterator.insertBefore(55, 5.99);
        iterator.insertBefore(66, 6.99);

        list.displayList();


        iterator.deleteCurrent();

        list.displayList();

        iterator.nextNode();
        iterator.getCurrent().displayNode();

        iterator.reset();

        while (!iterator.atEnd()) {
            iterator.getCurrent().displayNode();
            iterator.nextNode();
        }
    }

}
