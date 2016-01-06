package Chapter5;

class Node {
    public int iData;
    public double dData;
    public Node next;

    public Node(int id, double dd) {
        iData = id;
        dData = dd;
    }

    public void displayNode() {
        System.out.println("{"+iData+","+dData+"}");
    }
}
