package Chapter11;


public class HashDouble {
    private DataItem[] hashArray; // array holds hash table
    private int arraySize;
    private DataItem nonItem; // for deleted items

    public HashDouble(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }

    public void displayTable() {
        System.out.print("Table: ");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null)
                System.out.print(hashArray[i].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    public int hashFunc1(int key) {
        return key % arraySize;
    }

    public int hashFunc2(int key) {
        // non-zero, less than array size, different from hF1
        // array size must be relatively prime to 5, 4, 3, and 2
        return 5 - key % 5;
    }

    public void insert(DataItem item) {
        // assumes table not full

        int key = item.getKey();
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize;
            hashVal %= arraySize;
        }

        hashArray[hashVal] = item;
    }

    public DataItem delete(int key) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }

    public DataItem find(int key) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }
}
