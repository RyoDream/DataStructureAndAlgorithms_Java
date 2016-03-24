package Chapter14;

public class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private final int SIZE = 20;
    private Edge[] queArray;
    private int size;

    public PriorityQ() {
        queArray = new Edge[SIZE];
        size = 0;
    }


    public void insert(Edge item) {
        int i;

        // find place to insert
        for (i = 0; i < size; i++)
            if (item.distance >= queArray[i].distance)
                break;

        for (int k = size - 1; k >= i; k--)
            queArray[k + 1] = queArray[k];

        queArray[i] = item;
        size++;
    }

    // remove minimum item
    public Edge removeMin() {
        return queArray[--size];
    }

    // remove item at n
    public void removeN(int n) {
        for (int i = n; i < size - 1; i++)
            queArray[i] = queArray[i + 1];
        size--;
    }

    public Edge peekMin() {
        return queArray[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == SIZE);
    }

    public Edge peekN(int n) {
        return queArray[n];
    }

    // find item with specified destVert value
    public int find(int findDex) {
        for (int i = 0; i < size; i++) {
            if (queArray[i].destVert == findDex)
                return i;
        }
        return -1;
    }


}
