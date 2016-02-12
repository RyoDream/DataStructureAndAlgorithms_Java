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
        return (nElems == theArray.length);
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

        // 由于right是pivot的位置,因此把rightPtr设置为right
        // 下面的while循环,将从right-1开始寻找大于pivot的元素
        int rightPtr = right;

        while (true) {

            // 与Partition.java不同,这里不需要判断leftPtr < right
            // 是因为++leftPtr,最后leftPtr == right,此时theArray[leftPtr] == pivot
            // 因此不会出现下标溢出
            while (theArray[++leftPtr] < pivot)
                ;

            // rightPtr > left是为了防止pivot小于所有元素,导致rightPtr<0而下标溢出
            while (rightPtr > left && theArray[--rightPtr] > pivot)
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
        int maxSize = 10;
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
