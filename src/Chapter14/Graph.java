package Chapter14;

import org.omg.CORBA.INITIALIZE;

public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int nVerts;
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];

        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++)
            for (int j = 0; j < MAX_VERTS; j++)
                adjMat[i][j] = INFINITY;

        thePQ = new PriorityQ();
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void mstw() {
        currentVert = 0;

        while (nTree < nVerts - 1) {
            vertexList[currentVert].isInTree = true;
            nTree++;

            for (int i = 0; i < nVerts; i++) {
                if (i == currentVert)
                    continue;

                if (vertexList[i].isInTree)
                    continue;

                int distance = adjMat[currentVert][i];
                if (distance == INFINITY)
                    continue;

                putInPQ(i, distance);
            }

            if (thePQ.isEmpty()) {
                System.out.println("Graph Not Connected");
                return;
            }

            // remove edge with minimum distance, from PQ
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // display edge from source to current
            System.out.print(vertexList[sourceVert].label);
            System.out.print(vertexList[currentVert].label);
            System.out.print(" ");
        }

        for (int i = 0; i < nVerts; i++)
            vertexList[i].isInTree = false;
    }

    // PriorityQueue中已保留部分:从加入到Tree中的节点到未加入Tree节点的边
    // 因此新的边需要判断是否比PQ中已有的边更短
    // 如果更短,替换已有的边（到达同一目的节点）
    // 如果更长,则不做变动,保留PQ中已有的边
    public void putInPQ(int newVert, int newDist) {
        // is there another edge with the same destination vertex?
        int queueIndex = thePQ.find(newVert);

        if (queueIndex != -1) {
            Edge tempEdge = thePQ.peekN(queueIndex);
            int oldDist = tempEdge.distance;

            if (oldDist > newDist) {
                thePQ.removeN(queueIndex);
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);
            }
            // else no action; just leave the old vertex there
        } else // no edge with same destination vertex
        {
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();

        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');

        theGraph.addEdge(0, 1, 6);
        theGraph.addEdge(0, 3, 4);
        theGraph.addEdge(1, 2, 10);
        theGraph.addEdge(1, 3, 7);
        theGraph.addEdge(1, 4, 7);
        theGraph.addEdge(2, 3, 8);
        theGraph.addEdge(2, 4, 5);
        theGraph.addEdge(2, 5, 6);
        theGraph.addEdge(3, 4, 12);
        theGraph.addEdge(4, 5, 7);

        System.out.print("Minimum spanning tree: ");
        theGraph.mstw();
        System.out.println();
    }

}
