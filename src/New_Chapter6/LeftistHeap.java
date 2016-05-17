package New_Chapter6;

public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {

    private static class Node<AnyType> {
        AnyType element;
        Node<AnyType> left, right;
        int npl;

        Node(AnyType theElement) {
            this(theElement, null, null);

        }

        Node(AnyType theElement, Node<AnyType> left, Node<AnyType> right) {
            this.element = theElement;
            this.left = left;
            this.right = right;
            npl = 0;
        }
    }

    private Node<AnyType> root;

    public LeftistHeap() {
        root = null;
    }

    /**
     * Merge rhs into the priority queue.
     * rhs becomes empty. rhs must be different from this.
     *
     * @param rhs
     */
    public void merge(LeftistHeap<AnyType> rhs) {
        if (this == rhs)
            return;

        root = merge(root, rhs.root);
        rhs.root = null;
    }

    /**
     * Insert into priority queue, maintaining heap order.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = merge(new Node<AnyType>(x), root);

    }

    public AnyType findMin() {
        return isEmpty() ? null : root.element;
    }

    /**
     * Remove the smallest item from the priority queue.
     *
     * @return the smallest item, or return null if empty.
     */
    public AnyType deleteMin() {
        if (isEmpty())
            return null;

        AnyType minItem = root.element;
        root = merge(root.left, root.right);
        return minItem;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }


    /**
     * Internal method to merge two roots.
     * Deals with deviant cases and calls recursive merge1.
     */
    private Node<AnyType> merge(Node<AnyType> h1, Node<AnyType> h2) {
        if (h1 == null)
            return h2;

        if (h2 == null)
            return h1;

        if (h1.element.compareTo(h2.element) < 0)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);

    }


    /**
     * Internal method to merge two roots.
     * Assumes trees are not empty, and h1's root contains smallest item.
     * h1.element <= h2.element
     */
    private Node<AnyType> merge1(Node<AnyType> h1, Node<AnyType> h2) {
        if (h1.left == null)
            h1.left = h2;
        else {
            h1.right = merge(h1.right, h2);
            if (h1.left.npl < h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl + 1;
        }

        return h1;
    }

    private void swapChildren(Node<AnyType> t) {
        Node temp = t.left;
        t.left = t.right;
        t.right = temp;
    }

    public static void main(String[] args) {
        int numItems = 100;
        LeftistHeap h = new LeftistHeap();
        LeftistHeap h1 = new LeftistHeap();
        int i = 37;

        for (i = 37; i != 0; i = (i + 37) % numItems)
            if (i % 2 == 0)
                h1.insert(new Integer(i));
            else
                h.insert(new Integer(i));

        h.merge(h1);
        for (i = 1; i < numItems; i++)
            if (((Integer) (h.deleteMin())).intValue() != i)
                System.out.println("Oops! " + i);
    }
}
