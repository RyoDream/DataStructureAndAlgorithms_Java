package Chapter10;

public class Tree234 {
    private Node root = new Node();

    public int find(int key) {
        Node curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1)
                return childNumber;
            else if (curNode.isLeaf())
                return -1;
            else
                curNode = getNextChild(curNode, key);
        }
    }

    // new item always inserted into leaf node (bottom level)
    public void insert(int value) {
        Node curNode = root;
        DataItem tempItem = new DataItem(value);

        while (true) {
            if (curNode.isFull()) {
                split(curNode);
                curNode = curNode.getParent();
                curNode = getNextChild(curNode, value);
            } else if (curNode.isLeaf())
                break;
            else
                curNode = getNextChild(curNode, value);
        }

        // always insert into leaf node
        curNode.insertItem(tempItem);
    }

    public void split(Node thisNode) {
        // assume node is full
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;

        itemC = thisNode.removeItem();
        itemB = thisNode.removeItem();
        child2 = thisNode.disconnectChild(2);
        child3 = thisNode.disconnectChild(3);
        Node newRight = new Node();

        if (thisNode == root) {
            root = new Node();
            parent = root;
            root.connectChild(0, thisNode);
        } else
            parent = thisNode.getParent();

        // deal with parent
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();

        // maintain child array
        for (int i = n - 1; i > itemIndex; i--) {
            Node temp = parent.disconnectChild(i);
            parent.connectChild(i + 1, temp);
        }

        parent.connectChild(itemIndex + 1, newRight);

        // deal with newRight
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }

    // gets appropriate child of node during search for value
    public Node getNextChild(Node theNode, int theValue) {
        int i;
        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();
        for (i = 0; i < numItems; i++) {
            if (theValue < theNode.getItem(i).iData)
                return theNode.getChild(i);
        }
        return theNode.getChild(i); // return right child of the last item
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node thisNode, int level, int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();

        int numItems = thisNode.getNumItems();
        for (int i = 0; i < numItems + 1; i++) {
            Node nextNode = thisNode.getChild(i);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, i);
            else
                return;
        }
    }

    // assume the tree is not empty
    public int getMinValue() {
        Node parent = root;
        Node current = root;

        while (current != null) {
            parent = current;
            current = current.getChild(0);
        }

        return parent.getItem(0).iData;
    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.println("Preorder traverse");
                // preOrder(root);
                break;
            case 2:
                System.out.println("Inorder traverse");
                inOrder(root);
                System.out.println("");
                break;
            case 3:
                System.out.println("Postorder traverse");
                // postOrder(root);
                break;
        }
    }

    public void inOrder(Node root) {
        if (root == null)
            return;

        int i = 0;
        for (; i < root.getNumItems(); i++) {
            inOrder(root.getChild(i));
            System.out.print(root.getItem(i).iData + " ");
        }

        if (i != 0) {
            inOrder(root.getChild(i));
        }
    }

    public void sort(int[] array) {
        this.root = new Node();
        for (int i : array) {
            this.insert(i);
        }
        inOrderForSort(array, root, 0);
    }

    public int inOrderForSort(int[] array, Node root, int arrayIndex) {
        if (root == null)
            return arrayIndex;

        int i = 0;
        for (; i < root.getNumItems(); i++) {
            arrayIndex = inOrderForSort(array, root.getChild(i), arrayIndex);
            array[arrayIndex++] = root.getItem(i).iData;
        }

        if (i != 0) {
            arrayIndex = inOrderForSort(array, root.getChild(i), arrayIndex);
        }

        return arrayIndex;
    }

    public static void main(String[] args) {
        int value;
        Tree234 theTree = new Tree234();

        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);

        theTree.displayTree();

        theTree.insert(45);
        theTree.displayTree();

        theTree.find(30);

        System.out.println(theTree.getMinValue());
        theTree.traverse(2);

        int[] array = {1, 3, 2, 5, 7, 8, 4, 9};
        theTree.sort(array);
        for (int i : array)
            System.out.print(i + " ");
    }
}
