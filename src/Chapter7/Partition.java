package Chapter7;

public class Partition {
    private int[] theArray;
    private int nElems;

    public Partition(int maxSize) {
        theArray = new int[maxSize];
        nElems = 0;
    }

    public void insert(int value) {
        if (!isFUll())
            theArray[nElems++] = value;
    }

    public boolean isFUll() {
        return (nElems == theArray.length - 1);
    }

    public int size() {
        return nElems;
    }

    public void display() {
        for (int i = 0; i < size(); i++)
            System.out.print(theArray[i] + " ");
        System.out.println("");
    }

    public int partitionIt(int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right + 1;

        while (true) {

            // leftPtr < right 是为了防止pivot大于所有元素,则leftPtr会向右越界
            while (leftPtr < right && theArray[++leftPtr] < pivot)
                ;

            // rightPtr > left 是为了防止pivot小于所有元素,则rightPtr会向左越界
            while (rightPtr > left && theArray[--rightPtr] > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }
        // return index of first element larger that pivot
        return leftPtr;

        // return index of first element less than pivot
        // return rightPtr;
    }

    public void swap(int index1, int index2) {
        int temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
    }

    public static void main(String[] args) {
        int maxSize = 16;
        int pivot = 99;
        Partition partition = new Partition(maxSize);

        for (int i = 0; i <= maxSize; i++) {
            int n = (int) (java.lang.Math.random() * 199);
            partition.insert(n);
        }

        partition.display();
        int index = partition.partitionIt(0, partition.size() - 1, pivot);

        System.out.println("Pivot is " + pivot + " , Partition is at index " + index);
        partition.display();

    }
}
