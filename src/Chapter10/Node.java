package Chapter10;

public class Node {
    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER - 1];

    // connect child to this node
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    // disconnect child from this node, return it
    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    public Node getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return (childArray[0] == null);
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem getItem(int index) {
        return itemArray[index];
    }

    public boolean isFull() {
        return (numItems == ORDER - 1);
    }

    public int findItem(int key) {
        for (int i = 0; i < ORDER - 1; i++) {
            if (itemArray[i] == null)
                break;
            else if (itemArray[i].iData == key)
                return i;
        }
        return -1;
    }

    // always insert into leaf node
    // only shift exist array
    // do nothing with child array
    public int insertItem(DataItem newItem) {
        // assumes node is not full
        numItems++;
        int newKey = newItem.iData;

        for (int i = ORDER - 2; i >= 0; i--) {
            if (itemArray[i] != null) {
                int itsKey = itemArray[i].iData;    // shift it right
                if (newKey < itsKey)
                    itemArray[i + 1] = itemArray[i];
                else {
                    itemArray[i + 1] = newItem;
                    return i + 1;
                }
            }
        }
        itemArray[0] = newItem;
        return 0;
    }

    // remove largest item
    public DataItem removeItem() {
        // assume node not empty
        DataItem temp = itemArray[numItems - 1];
        itemArray[numItems - 1] = null;
        numItems--;
        return temp;
    }

    public void displayNode() {
        for (int i = 0; i < numItems; i++)
            itemArray[i].displayItem();
        System.out.println('/');
    }

}
