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
        return theNode.getChild(i);
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
    }
}
