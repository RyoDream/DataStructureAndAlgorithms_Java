package Chapter13;

public class GraphBFS {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;
    private int[][] adjacencyMatrix;
    private int nVerts;
    private Queue theQueue;

    public GraphBFS() {
        vertexList = new Vertex[MAX_VERTS];

        adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;

        for (int i = 0; i < MAX_VERTS; i++)
            for (int j = 0; j < MAX_VERTS; j++)
                adjacencyMatrix[i][j] = 0;

        theQueue = new Queue();
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
        adjacencyMatrix[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void bfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theQueue.insert(0);

        int v2;

        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                theQueue.insert(v2);
            }
        }

        for (int i = 0; i < nVerts; i++)
            vertexList[i].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < MAX_VERTS; i++)
            if (adjacencyMatrix[v][i] == 1 && !vertexList[i].wasVisited)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        GraphBFS theGraph = new GraphBFS();

        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');

        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(0, 3);
        theGraph.addEdge(3, 4);

        System.out.print("Visites: ");
        theGraph.bfs();
        System.out.println();
    }
}
