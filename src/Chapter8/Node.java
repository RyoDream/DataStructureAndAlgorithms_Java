package Chapter8;

public class Node {
    int iData;
    Node leftChild;
    Node rightChild;

    public Node() {
        iData = 0;
        leftChild = rightChild = null;
    }

    public Node(int value) {
        iData = value;
        leftChild = rightChild = null;
    }

    public void displayNode() {
        System.out.print("{" + iData + "}");
    }
}
