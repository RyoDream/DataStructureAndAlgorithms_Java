package New_Chapter5;

public class QuadraticProbingHashTable<AnyType> {

    private static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType e, boolean i) {
            element = e;
            isActive = i;
        }

        public HashEntry(AnyType e) {
            this(e, true);
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    private int currentSize;
    private HashEntry<AnyType>[] array;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    /**
     * Internal method to allocate array.
     *
     * @param arraySize the size of the array
     */
    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    /**
     * Find an item in the hash table.
     *
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    /**
     * Internal method that performs quadratic probing resolution.
     *
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = hash(x);

        while (array[currentPos] != null &&
                !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset *= 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }

        return currentPos;
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Insert into the hash table.
     * If the item is already present, do nothing.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<AnyType>(x);
        if (++currentPos > array.length / 2)
            rehash();
    }

    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    /**
     * Expand hash table.
     */
    private void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        // Copy table over
        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);

        return;
    }

    public int hash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= array.length;

        if (hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }

    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }


}
