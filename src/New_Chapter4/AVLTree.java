package New_Chapter4;

public class AvlTree<AnyType extends Comparable<? super AnyType>> {

    class AvlNode {

        AnyType element;
        AvlNode left;
        AvlNode right;
        int height;

        AvlNode(AnyType element) {
            this(element, null, null);
        }

        AvlNode(AnyType element, AvlNode left, AvlNode right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    private AvlNode root;

    public AvlTree() {
        root = null;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public AnyType findMin() {
        return elementAt(findMin(root));
    }

    public AnyType findMax() {
        return elementAt(findMax(root));
    }

    public AnyType find(AnyType x) {
        return elementAt(find(x, root));
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty Tree");
        else
            printTree(root);
    }

    private void printTree(AvlNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.println(root.element + " ");
            printTree(root.right);
        }
    }

    private AnyType elementAt(AvlNode root) {
        return root == null ? null : root.element;
    }

    private AvlNode insert(AnyType x, AvlNode root) {
        if (root == null)
            root = new AvlNode(x, null, null);
        else if (x.compareTo(root.element) < 0) {
            root.left = insert(x, root.left);
            if (height(root.left) - height(root.right) == 2)
                if (x.compareTo(root.left.element) < 0)
                    root = rotateWithLeftChild(root);
                else
                    root = doubleWithLeftChild(root);
        } else if (x.compareTo(root.element) > 0) {
            root.right = insert(x, root.right);
            if (height(root.right) - height(root.left) == 2)
                if (x.compareTo(root.right.element) > 0)
                    root = rotateWithRightChild(root);
                else
                    root = doubleWithRightChild(root);
        } else    // Duplicate; do nothing
            ;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    private int height(AvlNode root) {
        return root == null ? -1 : root.height;
    }

    private AvlNode rotateWithLeftChild(AvlNode k2) {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode rotateWithRightChild(AvlNode k1) {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private AvlNode doubleWithLeftChild(AvlNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode doubleWithRightChild(AvlNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private AvlNode find(AnyType x, AvlNode root) {
        while (root != null)
            if (x.compareTo(root.element) < 0)
                root = root.left;
            else if (x.compareTo(root.element) > 0)
                root = root.right;
            else
                return root;

        return null;
    }

    private AvlNode findMin(AvlNode root) {
        if (root == null)
            return root;

        while (root.left != null)
            root = root.left;

        return root;
    }

    private AvlNode findMax(AvlNode root) {
        if (root == null)
            return root;

        while (root.right != null)
            root = root.right;

        return root;
    }

    public static void main(String[] args) {
        AvlTree t = new AvlTree();
        final int NUMS = 20;
        final int GAP = 37;

        System.out.println("Checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            t.insert(new Integer(i));

        t.printTree();

    }
}
