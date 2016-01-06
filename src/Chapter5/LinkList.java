package Chapter5;

public class LinkList {

    private Node first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int id, double dd) {
        Node newNode = new Node(id, dd);
        newNode.next = first;
        first = newNode;
    }

    public Node deleteFirst() {
        if(isEmpty())
            return null;

        Node temp = first;
        first = first.next;
        temp.next = null;
        return temp;
    }

    public void displayList() {
        System.out.println("List (first --> last): ");
        Node current = first;
        while(current != null) {
            current.displayNode();
            current = current.next;
        }
    }

    public Node find(int key) {

        Node current = first;

        while(current != null && current.iData != key) {
            current = current.next;
        }
        return current;
    }

    public Node delete(int key) {

        if(isEmpty())
            return null;

        Node current = first;
        Node previous = first;

        while(current.iData != key) {
            if(current.next == null)
                return null;

            previous = current;
            current = current.next;
        }

        if(current == first)
            first = first.next;
        else
            previous.next = current.next;

        current.next = null;
        return current;
    }

    public static void main(String[] args) {
        LinkList linkList = new LinkList();

        linkList.insertFirst(22, 2.99);
        linkList.insertFirst(44, 4.99);
        linkList.insertFirst(66, 6.99);
        linkList.insertFirst(88, 8.99);

        linkList.displayList();

        Node res = linkList.delete(22);
        if(res != null)
            res.displayNode();
        else
            System.out.print("Can't delete element.\n");

        res = linkList.find(44);
        if(res != null)
            res.displayNode();
        else
            System.out.print("Can't find element.\n");


        while (!linkList.isEmpty()) {
            Node node = linkList.deleteFirst();
            System.out.print("Deleted: ");
            node.displayNode();
        }

        linkList.displayList();
    }
}
