package Chapter2;

/**
 * Created by shiyanch on 12/28/15.
 */
public class OrdArray {
    private long[] a;
    private int nElems;

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        int low = 0;
        int high = nElems - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) >> 1;
            if(a[mid] == searchKey)
                return mid;
            else if (a[mid] > searchKey)
                high = mid -1;
            else
                low = mid + 1;
        }
        return nElems;
    }

    public void insert(long value) {
        int i;
        for (i=0;i<nElems;i++) {
            if(a[i] > value)
                break;
        }

        // if insertion-counts exceed maximum size
        int top = (nElems == a.length)?(--nElems):nElems;

        for (int j=top;j>i;j--) {
            a[j] = a[j-1];
        }

        a[i] = value;
        nElems++;

        display();
    }

    public boolean delete(long value) {
        int i = find(value);

        if(i==nElems)
            return false;
        else {
            int top = (nElems == a.length)?(nElems-1):nElems;
            for(int j=i;j<top;j++) {
                a[i] = a[i+1];
            }
            nElems--;
            return true;
        }
    }

    public void display() {
        for(int i=0;i<nElems;i++)
            System.out.print(a[i]+" ");
        System.out.println(" ");
    }

//    public static void main(String[] args) {
//        int maxSize = 5;
//        OrdArray arr = new OrdArray(maxSize);
//
//        arr.insert(22);
//        arr.insert(12);
//        arr.insert(89);
//        arr.insert(3);
//        arr.insert(48);
//
//        arr.display();
//
//        int searchKey = 13;
//        System.out.println(arr.find(searchKey));
//
//        arr.delete(22);
//        arr.display();
//
//        int deleteKey = 33;
//        if(!arr.delete(deleteKey))
//            System.out.println("Can't delete " + deleteKey);
//
//        arr.insert(1);
//        arr.display();
//        arr.insert(28);
//    }
}
