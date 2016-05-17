package New_Chapter5;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<AnyType>();
    }

    /**
     * Insert into the hash table.
     * If the item is already present,
     * then do nothing.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length)
                rehash();
        }

    }

    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * Find an item in the hash table
     *
     * @param x the item to search for
     * @return true if x is found
     */
    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;

    }


    private void rehash() {

    }

    // x必须提供equals方法和返回int型的hashCode方法
    private int myhash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= theLists.length;

        if (hashVal < 0)
            hashVal += theLists.length;

        return hashVal;
    }

    private static int nextPrime(int n) {
        return 0;
    }

    private static boolean isPrime(int n) {
        return false;
    }


}
