package New_Chapter6;

public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {
    private class BinomialNode<AnyType> {
        AnyType element;
        BinomialNode<AnyType> leftChild;
        BinomialNode<AnyType> nextSibling;

        BinomialNode(AnyType theElement, BinomialNode<AnyType> leftChild, BinomialNode<AnyType> nextSibling) {
            this.element = theElement;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }

        BinomialNode(AnyType theElement) {
            this(theElement, null, null);
        }
    }

    private static final int MAX_TREES = 14;
    private int currentSize;
    private BinomialNode[] theTrees;

    public BinomialQueue() {
        theTrees = new BinomialNode[MAX_TREES];
        makeEmpty();
    }

    /**
     * Merge rhs into the priority queue.
     * rhs becomes empty. rhs must be different from this.
     *
     * @param rhs the other binomial queue.
     */
    public void merge(BinomialQueue<AnyType> rhs) {
        if (this == rhs)
            return;

        currentSize += rhs.currentSize;

        if (currentSize + rhs.currentSize > capacity())
            return;

        currentSize += rhs.currentSize;

        BinomialNode<AnyType> carry = null;

        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            BinomialNode t1 = theTrees[i];
            BinomialNode t2 = rhs.theTrees[i];

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0: /* No trees */
                case 1: /* Only this */
                    break;
                case 2: /* Only rhs */
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4: /* Only carry */
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3: /* this and rhs */
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5: /* this and carry */
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6: /* rhs and carry */
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7: /* All three */
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }

        for (int k = 0; k < rhs.theTrees.length; k++)
            rhs.theTrees[k] = null;
        rhs.currentSize = 0;

    }

    public void insert(AnyType x) {
        BinomialQueue oneItem = new BinomialQueue();
        oneItem.currentSize = 1;
        oneItem.theTrees[0] = new BinomialNode(x);

        merge(oneItem);

    }

    public AnyType findMin() {

        if (isEmpty())
            return null;

        return (AnyType) theTrees[findMinIndex()].element;
    }

    public AnyType deleteMin() {

        if (isEmpty())
            return null;

        int minIndex = findMinIndex();
        AnyType minItem = (AnyType) theTrees[minIndex].element;

        BinomialNode<AnyType> deletedTree = theTrees[minIndex].leftChild;

        // Construct H''
        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>();
        deletedQueue.currentSize = (1 << minIndex) - 1;

        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        // Construct H'
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theTrees.length; i++)
            theTrees[i] = null;
    }


    /**
     * Return the result of merging equal-sized t1 and t2
     */
    private BinomialNode<AnyType> combineTrees(BinomialNode<AnyType> t1, BinomialNode<AnyType> t2) {
        if (t1.element.compareTo(t2.element) > 0)
            return combineTrees(t2, t1);

        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }

    private int findMinIndex() {
        int i, minIndex;

        for (i = 0; theTrees[i] == null; i++)
            ;
        for (minIndex = i; i < theTrees.length; i++)
            if (theTrees[i] != null &&
                    ((AnyType) theTrees[i].element).compareTo((AnyType) theTrees[minIndex].element) < 0)
                minIndex = i;

        return minIndex;
    }
}
