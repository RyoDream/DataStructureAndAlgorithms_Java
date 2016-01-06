package Chapter5;

public class LinkStack {
    private LinkList list;

    public LinkStack() {
        list = new LinkList();
    }

    public void push(int id, double dd) {
        list.insertFirst(id, dd);
    }

    public Node pop() {
        return list.deleteFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void displayStack() {
        System.out.print("Stack (top -> bottom): ");
        list.displayList();
    }

    public static void main(String[] args) {
        LinkStack stack = new LinkStack();
        stack.push(11, 1.99);
        stack.push(22, 2.99);
        stack.push(33, 3.99);

        stack.displayStack();

        stack.pop();
        stack.pop();

        stack.displayStack();

        stack.pop();
        stack.pop();
        stack.displayStack();


    }
}
