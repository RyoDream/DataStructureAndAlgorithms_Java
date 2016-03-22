package Chapter13;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;
    private int[][] adjacencyMatrix;
    private int nVerts;
    private char[] sortedArray;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];

        adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;

        for (int i = 0; i < MAX_VERTS; i++)
            for (int j = 0; j < MAX_VERTS; j++)
                adjacencyMatrix[i][j] = 0;

        sortedArray = new char[MAX_VERTS];
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
    }

    public void displayEdge() {
        for (int row = 0; row < nVerts; row++) {
            for (int col = 0; col < nVerts; col++)
                System.out.print(adjacencyMatrix[row][col] + " ");
            System.out.println();
        }
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < MAX_VERTS; i++)
            if (adjacencyMatrix[v][i] == 1 && !vertexList[i].wasVisited)
                return i;
        return -1;
    }

    public void topologicalSort() {
        int orig_nVerts = nVerts;

        while (nVerts > 0) {
            int currentVertex = noSuccessors();
            if (currentVertex == -1) {
                System.out.print("Error: Graph has cycles");
                return;
            }

            sortedArray[nVerts - 1] = vertexList[currentVertex].label;

            deleteVertex(currentVertex);
        }

        System.out.print("Topologically sorted order: ");
        for (int i = 0; i < orig_nVerts; i++)
            System.out.print(sortedArray[i]);
        System.out.println();
    }

    public int noSuccessors() {
        boolean isEdge;

        for (int row = 0; row < nVerts; row++) {
            isEdge = false;
            for (int col = 0; col < nVerts; col++) {
                if (adjacencyMatrix[row][col] > 0) {
                    isEdge = true;
                    break;
                }
            }

            if (!isEdge)
                return row;
        }
        return -1;
    }

    public void deleteVertex(int delVert) {
        if (delVert != nVerts - 1) {
            for (int i = delVert; i < nVerts - 1; i++)
                vertexList[i] = vertexList[i + 1];

            for (int row = delVert; row < nVerts - 1; row++)
                moveRowUp(row, nVerts);

            for (int col = delVert; col < nVerts - 1; col++)
                moveColLeft(col, nVerts - 1);
        }
        nVerts--;
    }

    public void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++)
            adjacencyMatrix[row][col] = adjacencyMatrix[row + 1][col];
    }

    public void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++)
            adjacencyMatrix[row][col] = adjacencyMatrix[row][col + 1];
    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();

        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');
        theGraph.addVertex('G');
        theGraph.addVertex('H');

        theGraph.addEdge(0, 3);
        theGraph.addEdge(0, 4);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(2, 5);
        theGraph.addEdge(3, 6);
        theGraph.addEdge(4, 6);
        theGraph.addEdge(5, 7);
        theGraph.addEdge(6, 7);

        theGraph.displayEdge();

        theGraph.topologicalSort();
    }
}
