package Chapter5;


class DoublyNode {
    public int iData;
    public DoublyNode next;
    public DoublyNode prev;

    public DoublyNode(int id) {
        iData = id;
    }

    public void displayNode() {
        System.out.print(iData + " ");
    }
}

public class DoublyLinkedList {
    private DoublyNode first;
    private DoublyNode last;

    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int id) {
        DoublyNode newNode = new DoublyNode(id);

        if (isEmpty())
            last = newNode;         // newNode <-- last
        else
            first.prev = newNode;   // newNode <-- old first

        newNode.next = first;       // newNode --> old first
        first = newNode;            // first --> newNode
    }

    public void insertLast(int id) {
        DoublyNode newNode = new DoublyNode(id);

        if (isEmpty())
            first = newNode;        // first --> newNode
        else {
            last.next = newNode;    // old last --> newNode
        }
        newNode.prev = last;        // old last <-- newNode
        last = newNode;             // newNode <-- last
    }

    public DoublyNode deleteFirst() {
        if (isEmpty())
            return null;

        DoublyNode temp = first;
        if (first.next == null)     // if only one item
            last = null;            // null <-- last
        else
            first.next.prev = null; // null <-- old next

        first = first.next;         // first --> old next
        temp.next = null;
        return temp;
    }

    public DoublyNode deleteLast() {
        if (isEmpty())
            return null;

        DoublyNode temp = last;

        if (last.prev == null)      // if only one item
            first = null;           // first --> null
        else
            last.prev.next = null;  // old previous --> null

        last = last.prev;           // old previous <-- last
        temp.prev = null;
        return temp;
    }

    // insert dd just after key
    public boolean insertAfter(int key, int id) {
        if (isEmpty())
            return false;

        DoublyNode current = first;

        while (current.iData != key) {
            current = current.next;
            if (current == null)
                return false;
        }

        DoublyNode newNode = new DoublyNode(id);

        if (current == last)
            last = newNode;
        else {
            newNode.next = current.next;
            current.next.prev = newNode;
        }

        newNode.prev = current;
        current.next = newNode;

        return true;
    }

    public DoublyNode deleteKey(int key) {
        if (isEmpty())
            return null;

        DoublyNode current = first;

        while (current.iData != key) {
            current = current.next;
            if (current == null)
                return null;
        }

        if (current == first)
            first = current.next;
        else
            current.prev.next = current.next;

        if (current == last)
            last = current.prev;
        else
            current.next.prev = current.prev;

        current.next = null;
        current.prev = null;
        return current;
    }

    public void displayForward() {
        System.out.print("List (first --> last): ");
        DoublyNode current = first;

        while (current != null) {
            current.displayNode();
            current = current.next;
        }
        System.out.println("");
    }

    public void displayBackward() {
        System.out.print("List (last --> first): ");
        DoublyNode current = last;

        while (current != null) {
            current.displayNode();
            current = current.prev;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.insertFirst(22);
        list.insertFirst(44);
        list.insertFirst(66);

        list.insertLast(11);
        list.insertLast(33);
        list.insertLast(55);

        list.displayForward();
        list.displayBackward();

        list.deleteFirst();
        list.deleteLast();
        list.deleteKey(11);

        list.displayForward();

        list.insertAfter(22, 77);
        list.insertAfter(33, 88);

        list.displayForward();
    }
}
