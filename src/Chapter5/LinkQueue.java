package Chapter5;

public class LinkQueue {
    private FirstLastList list;

    public LinkQueue() {
        list = new FirstLastList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void insert(int id, double dd) {
        list.insertLast(id, dd);
    }

    public Node remove() {
        return list.deleteFirst();
    }

    public void displayQueue() {
        System.out.print("Queue (front -> last): ");
        list.displayList();
    }

    public static void main(String[] args) {
        LinkQueue queue = new LinkQueue();

        queue.insert(11, 1.99);
        queue.insert(22, 2.99);

        queue.displayQueue();

        queue.remove();
        queue.remove();

        queue.displayQueue();

        queue.insert(33, 3.99);
        queue.displayQueue();
    }
}
