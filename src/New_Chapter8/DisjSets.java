package New_Chapter8;

public class DisjSets {
    private int[] set;

    /**
     * Construct the disjoint sets object.
     *
     * @param numElements the initial number of disjoint sets.
     */
    public DisjSets(int numElements) {
        set = new int[numElements];
        for (int i = 0; i < set.length; i++)
            set[i] = -1;
    }

    /**
     * Union two disjoint sets.
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names.
     *
     * @param root1 the root of set1
     * @param root2 the root of set2
     */
    public void unionDefault(int root1, int root2) {
        set[root2] = root1;
    }

    /**
     * Union two disjoint sets using the height heuristic.
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names.
     *
     * @param root1 the root of set1
     * @param root2 the root of set2
     */
    public void union(int root1, int root2) {
        if (set[root2] < set[root1]) // root2 is deeper
            set[root1] = root2;
        else {
            // if same height, then make root1 new root
            if (set[root1] == set[root2])
                set[root1]--; // deeper the height record

            set[root2] = root1;
        }
    }

    /**
     * Perform a find.
     *
     * @param x the element being searched for.
     * @return the set containing x.
     */
    public int findDefault(int x) {
        if (set[x] < 0)
            return x;
        else
            return findDefault(set[x]);
    }

    /**
     * Perform a find with path compression.
     */
    public int find(int x) {
        if (set[x] < 0)
            return x;
        else
            return set[x] = find(set[x]);
    }
}
