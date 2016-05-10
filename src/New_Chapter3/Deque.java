package New_Chapter3;


public class Deque<AnyType> {
    private MyLinkedList<AnyType> list;

    public Deque() {
        list = new MyLinkedList<>();
    }

    public void push(AnyType x) {
        list.addFirst(x);
    }

    public AnyType pop() {
        return list.removeFirst();
    }

    public void inject(AnyType x) {
        list.addLast(x);
    }

    public AnyType eject() {
        return list.removeLast();
    }
}
