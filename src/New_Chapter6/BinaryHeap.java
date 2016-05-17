package New_Chapter6;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;

    // IMPORTANT: index starts from 1
    private AnyType[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Object[capacity + 1];
    }

    public BinaryHeap(AnyType[] items) {

    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // Percolate up
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];

        array[hole] = x;
    }

    public AnyType findMin() {
        if (isEmpty())
            return null;

        return array[1];
    }

    /**
     * Remove the smallest item from the priority queue.
     *
     * @return the smallest iterm, or null, if empty.
     */
    public AnyType deleteMin() {
        if (isEmpty())
            return null;

        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    /**
     * Internal method to percolate down in the heap
     *
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown(int hole) {
        int child;
        AnyType temp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0)
                child++;

            if (array[child].compareTo(temp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = temp;
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    private void enlargeArray(int newSize) {
        AnyType[] newArray = (AnyType[]) new Object[newSize];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];

        array = newArray;
    }

    public static void main(String[] args) {
        int numItems = 10000;
        BinaryHeap h = new BinaryHeap(numItems);
        int i = 37;


        for (i = 37; i != 0; i = (i + 37) % numItems)
            h.insert(new Integer(i));
        for (i = 1; i < numItems; i++)
            if (((Integer) (h.deleteMin())).intValue() != i)
                System.out.println("Oops! " + i);

        for (i = 37; i != 0; i = (i + 37) % numItems)
            h.insert(new Integer(i));
        h.insert(new Integer(0));
        i = 9999999;
        h.insert(new Integer(i));
        for (i = 1; i <= numItems; i++)
            if (((Integer) (h.deleteMin())).intValue() != i)
                System.out.println("Oops! " + i + " ");
    }

}
