package Chapter13;

public class GraphDFS {
    public final int MAX_VERTS = 20;
    private Vertex[] vertexList;
    private int[][] adjacencyMatrix;
    private int nVertexs;
    private StackX theStack;

    public GraphDFS() {
        vertexList = new Vertex[MAX_VERTS];

        adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
        nVertexs = 0;

        for (int i = 0; i < MAX_VERTS; i++)
            for (int j = 0; j < MAX_VERTS; j++)
                adjacencyMatrix[i][j] = 0;

        theStack = new StackX();
    }

    public void addVertex(char lab) {
        vertexList[nVertexs++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
        adjacencyMatrix[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void dfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theStack.push(0);

        while (!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -1)
                theStack.pop();
            else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                theStack.push(v);
            }
        }

        for (int i = 0; i < nVertexs; i++)
            vertexList[i].wasVisited = false;
    }

    public void mst() {
        vertexList[0].wasVisited = true;
        theStack.push(0);

        while (!theStack.isEmpty()) {
            int currentVertex = theStack.peek();
            int v = getAdjUnvisitedVertex(currentVertex);
            if (v == -1)
                theStack.pop();
            else {
                vertexList[v].wasVisited = true;
                theStack.push(v);

                displayVertex(currentVertex);
                displayVertex(v);
                System.out.print(" ");
            }
        }

        for (int i = 0; i < nVertexs; i++)
            vertexList[i].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < nVertexs; i++)
            if (adjacencyMatrix[v][i] == 1 && !vertexList[i].wasVisited)
                return i;

        return -1;
    }

    public static void main(String[] args) {
        GraphDFS theGraph = new GraphDFS();

        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');

        theGraph.addEdge(0, 1);
        theGraph.addEdge(0, 2);
        theGraph.addEdge(0, 3);
        theGraph.addEdge(0, 4);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(1, 3);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(2, 3);
        theGraph.addEdge(2, 4);
        theGraph.addEdge(3, 4);

        System.out.print("Visites: ");
        theGraph.dfs();
        System.out.println();

        System.out.print("Minimum spanning tree: ");
        theGraph.mst();
        System.out.println();

    }
}
