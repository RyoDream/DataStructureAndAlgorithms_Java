package Chapter6;

public class MergeSort {

    private int[] theArray;
    private int nElems;

    public MergeSort(int max) {
        theArray = new int[max];
        nElems = 0;
    }

    public void insert(int value) {
        theArray[nElems++] = value;
    }

    public void mergeSort() {
        int[] workSpace = new int[nElems];
        recMergeSort(workSpace, 0, nElems - 1);
    }

    public static void merge(int[] arrayA, int sizeA, int[] arrayB, int sizeB, int[] arrayC) {
        int aDex = 0;
        int bDex = 0;
        int cDex = 0;

        while (aDex < sizeA && bDex < sizeB) {
            if (arrayA[aDex] < arrayB[bDex])
                arrayC[cDex++] = arrayA[aDex++];
            else
                arrayC[cDex++] = arrayB[bDex++];
        }

        while (aDex < sizeA)
            arrayC[cDex++] = arrayA[aDex++];

        while (bDex < sizeB)
            arrayC[cDex++] = arrayB[bDex++];
    }

    public void display() {
        for (int i : theArray)
            System.out.print(i + " ");
        System.out.println("");
    }

    private void recMergeSort(int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound)
            return;
        else {
            int mid = (lowerBound + upperBound) / 2;

            recMergeSort(workSpace, lowerBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);

            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    private void merge(int[] workSpace, int lowPtr, int highPtr, int upperBound) {

        int index = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (theArray[lowPtr] < theArray[highPtr])
                workSpace[index++] = theArray[lowPtr++];
            else
                workSpace[index++] = theArray[highPtr++];
        }

        while (lowPtr <= mid)
            workSpace[index++] = theArray[lowPtr++];

        while (highPtr <= upperBound)
            workSpace[index++] = theArray[highPtr++];

        for (index = 0; index < n; index++)
            theArray[lowerBound + index] = workSpace[index];

    }


    public static void main(String[] args) {
        int maxSize = 20;
        MergeSort mergeSort = new MergeSort(maxSize);
        mergeSort.insert(64);
        mergeSort.insert(21);
        mergeSort.insert(33);
        mergeSort.insert(70);
        mergeSort.insert(12);
        mergeSort.insert(85);
        mergeSort.insert(44);
        mergeSort.insert(3);
        mergeSort.insert(99);
        mergeSort.insert(0);
        mergeSort.insert(108);
        mergeSort.insert(36);

        mergeSort.display();

        mergeSort.mergeSort();
        mergeSort.display();
    }
}
