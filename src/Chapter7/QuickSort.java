package Chapter7;


public class QuickSort {
    private int[] theArray;
    private int nElems;

    public QuickSort(int maxSize) {
        theArray = new int[maxSize];
        nElems = 0;
    }

    public void insert(int value) {
        if (!isFull())
            theArray[nElems++] = value;
    }

    public boolean isFull() {
        return (nElems == theArray.length - 1);
    }

    public void display() {
        for (int i = 0; i < nElems; i++)
            System.out.print(theArray[i] + " ");
        System.out.println("");
    }

    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right) {
        if (right <= left)
            return;

        int pivot = theArray[right];

        int partition = partitionIt(left, right, pivot);
        recQuickSort(left, partition - 1);
        recQuickSort(partition + 1, right);
    }

    public int partitionIt(int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {
            while (theArray[++leftPtr] < pivot)
                ;

            while (rightPtr > 0 && theArray[--rightPtr] > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right);
        return leftPtr;
    }


    public void swap(int index1, int index2) {
        int temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
    }

    public static void main(String[] args) {
        int maxSize = 16;
        QuickSort quickSorter = new QuickSort(maxSize);

        for (int i = 0; i <= maxSize; i++) {
            int n = (int) (java.lang.Math.random() * 99);
            quickSorter.insert(n);
        }

        quickSorter.display();
        quickSorter.quickSort();
        quickSorter.display();
    }

}
