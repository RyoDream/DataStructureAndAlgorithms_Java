package Chapter7;

// use insertion sort for cleanup
public class QuickSort3 {
    private int[] theArray;
    private int nElems;

    public QuickSort3(int maxSize) {
        theArray = new int[maxSize];
        nElems = 0;
    }

    public boolean isFull() {
        return (nElems == theArray.length);
    }

    public void insert(int value) {
        if (!isFull())
            theArray[nElems++] = value;
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
        int size = right - left + 1;
        if (size < 10)
            insertionSort(left, right);
        else {
            int median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    public void insertionSort(int left, int right) {
        int in, out;

        for (out = left + 1; out <= right; out++) {
            int temp = theArray[out];
            in = out;

            while (in > left && theArray[in - 1] >= temp) {
                theArray[in] = theArray[in - 1];
                --in;
            }

            theArray[in] = temp;
        }
    }

    public int medianOf3(int left, int right) {
        int center = (left + right) / 2;

        if (theArray[left] > theArray[center])
            swap(left, center);

        if (theArray[left] > theArray[right])
            swap(left, right);

        if (theArray[center] > theArray[right])
            swap(center, right);

        swap(center, right - 1);
        return theArray[right - 1];
    }

    public int partitionIt(int left, int right, int pivot) {
        int leftPtr = left;
        // 由于right-1为pivot的位置,因此把rightPtr设置为right-1
        // 下面的while循环中,将从right-2开始寻找大于pivot的元素
        int rightPtr = right - 1;

        while (true) {

            // 这里不需要判断leftPtr < right
            // 因为++leftPtr,最后leftPtr == right-1,此时theArray[leftPtr] == pivot
            // 而theArray[right] > pivot,因此可以保证left从左至右的遍历,同时不会超过下标（在right-1就停了）
            while (theArray[++leftPtr] < pivot)
                ;

            // 这里不需要判断rightPtr > left
            // 因为theArray[left] < pivot
            // 因此当--rightPtr,最后rightPtr == left,此时不满足theArray[rightPtr] > pivot
            // 进而防止了下标溢出（在left就停了）
            while (theArray[--rightPtr] > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right - 1);
        return leftPtr;
    }

    private void swap(int index1, int index2) {
        int temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
    }

    public static void main(String[] args) {

        int maxSize = 20;
        QuickSort3 quickSort3 = new QuickSort3(maxSize);

        for (int i = 0; i <= maxSize; i++) {
            int n = (int) (java.lang.Math.random() * 99);
            quickSort3.insert(n);
        }

        quickSort3.display();
        quickSort3.quickSort();
        quickSort3.display();
    }
}
