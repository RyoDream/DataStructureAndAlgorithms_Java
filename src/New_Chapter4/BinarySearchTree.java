package New_Chapter4;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        AnyType element;    // the data in the node
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode lt, BinaryNode rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() {
        if (isEmpty())
            ;

        return findMin(root).element;
    }

    public AnyType findMax() {
        if (isEmpty())
            ;

        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    /**
     * Interal method to find an item in a subtree.
     *
     * @param x is the item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;

        if (t.left == null)
            return t;

        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t == null)
            return null;

        if (t.right == null)
            return t;

        return findMax(t.right);
    }


    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<AnyType>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;   // duplicate, do nothing

        return t;
    }


    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {

        if (t == null)
            return t;   // item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    private void printTree(BinaryNode<AnyType> t) {

    }

    /**
     * Count number of nodes in a subtree.
     *
     * @param root the node that roots the subtree.
     * @return the number of all nodes.
     */
    public int countNodes(BinaryNode root) {
        if (root == null)
            return 0;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Count number of leaves in a subtree.
     *
     * @param root the node that roots the subtree.
     * @return the number of all leaves.
     */
    public int countLeaves(BinaryNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;

        return countLeaves(root.left) + countLeaves(root.right);
    }

    public int countFull(BinaryNode root) {
        if (root == null)
            return 0;

        int isFull = (root.left != null && root.right != null) ?
                1 : 0;

        return isFull + countFull(root.left) + countFull(root.right);
    }

}
