package Chapter14;


class Edge {
    public int srcVert;
    public int destVert;
    public int distance;

    public Edge(int sv, int dv, int d) {
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
}
