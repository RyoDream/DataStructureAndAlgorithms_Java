package Chapter2;

/**
 * Created by shiyanch on 12/28/15.
 */
public class HighArray {
    private long[] a;
    private int nElems;

    public HighArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public boolean find(long searchKey) {
        for(int i=0;i<nElems;i++) {
            if (a[i] == searchKey)
                return true;
        }
        return false;
    }

    public void insert(long value) {
        // if reaches the maximum size, then insert to the last element
        if(nElems == a.length)
            --nElems;
        a[nElems++] = value;
    }

    public boolean delete(long value) {
        int i=0;
        for(i=0;i<nElems;i++) {
            if (a[i] == value)
                break;
        }

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
//        int maxSize = 4;
//        HighArray arr = new HighArray(maxSize);
//
//        arr.insert(77);
//        arr.insert(12);
//        arr.insert(89);
//        arr.insert(3);
//        arr.insert(48);
//
//        arr.display();
//
//        int searchKey = 13;
//        if(arr.find(searchKey))
//            System.out.println("Found " + searchKey);
//        else
//            System.out.println("Can't find " + searchKey);
//
//
//        arr.delete(3);
//        arr.display();
//
//        int deleteKey = 48;
//        if(!arr.delete(deleteKey))
//            System.out.println("Can't delete " + deleteKey);
//        else arr.display();
//    }
}
