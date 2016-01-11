package Chapter5;


public class SortedList {
    private Node first;

    public SortedList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(int id, double dd) {
        Node newNode = new Node(id, dd);
        Node previous = null;
        Node current = first;

        // same key appears previously
        while (current != null && current.iData < id) {
            previous = current;
            current = current.next;
        }

        if (previous == null)
            first = newNode;
        else
            previous.next = newNode;

        newNode.next = current;
    }

    /*
    return & delete first Node
     */
    public Node remove() {
        Node temp = first;

        if (first != null) {
            first = first.next;
            temp.next = null;
        }

        return temp;
    }

    public void displayList() {
        System.out.println("List (first --> last): ");
        Node current = first;

        while (current != null) {
            current.displayNode();
            current = current.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        SortedList sortedList = new SortedList();
        sortedList.insert(11, 1.33);
        sortedList.insert(22, 2.33);
        sortedList.insert(33, 3.33);
        sortedList.insert(11, 4.33);


        sortedList.insert(22, 5.33);
        sortedList.insert(11, 6.33);
        sortedList.insert(10, 7.33);
        sortedList.insert(55, 8.33);

        sortedList.displayList();

        sortedList.remove();
        sortedList.displayList();
    }
}
