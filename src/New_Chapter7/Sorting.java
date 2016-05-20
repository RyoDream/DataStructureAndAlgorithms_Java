package New_Chapter7;

import java.util.Objects;

public class Sorting {
    private static final int CUTOFF = 10;

    /**
     * Simple insertion sort.
     *
     * @param array an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] array) {
        int i;

        for (int p = 1; p < array.length; p++) {
            AnyType temp = array[p];

            for (i = p; i > 0 && temp.compareTo(array[i - 1]) < 0; i--)
                array[i] = array[i - 1];

            array[i] = temp;
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] array) {
        int j;

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                AnyType temp = array[i];
                for (j = i; j >= gap &&
                        temp.compareTo(array[j - gap]) < 0; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapsort(AnyType[] array) {
        // build heap
        for (int i = array.length / 2; i >= 0; i--)
            percolateDown(array, i, array.length);

        // deleteMax
        for (int i = array.length - 1; i > 0; i--) {
            swapReferences(array, 0, i);
            percolateDown(array, 0, i);
        }
    }

    /**
     * Method to swap two elements in an array.
     *
     * @param array  an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static final void swapReferences(Object[] array, int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    /**
     * Internal method for heapsort.
     *
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    public static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort that is used in deleteMin and buildHeap
     *
     * @param array     an array of Comparable items.
     * @param i         the position from which to percolate down.
     * @param n         the logical size of the binary heap.
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDown(AnyType[] array, int i, int n) {
        int child;
        AnyType temp;

        for (temp = array[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && array[child].compareTo(array[child + 1]) < 0)
                child++;
            if (temp.compareTo(array[child]) < 0)
                array[i] = array[child];
            else break;
        }

        array[i] = temp;
    }

    /**
     * Internal method that makes recursive calls.
     *
     * @param array     an array of Comparable items.
     * @param tempArray an array to place the merged result.
     * @param left      the left-most index of the subarray.
     * @param right     the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] array, AnyType[] tempArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(array, tempArray, left, center);
            mergeSort(array, tempArray, center + 1, right);
            merge(array, tempArray, left, center + 1, right);
        }
    }

    /**
     * Mergesort algorithm.
     *
     * @param array an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] array) {
        AnyType[] temp = (AnyType[]) new Comparable[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }

    /**
     * Internal method to merge two sorted halves of a subarray.
     *
     * @param array     an array of Comparable items.
     * @param tempArray an array to place the merged result.
     * @param leftPos   the left-most index of the subarray.
     * @param rightPos  the index of the start of the second half.
     * @param rightEnd  the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] array, AnyType[] tempArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElement = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {

            if (array[leftPos].compareTo(array[rightPos]) <= 0)
                tempArray[tempPos++] = array[leftPos++];
            else
                tempArray[tempPos++] = array[rightPos++];
        }

        while (leftPos <= leftEnd)
            tempArray[tempPos++] = array[leftPos++];

        while (rightPos <= rightEnd)
            tempArray[tempPos++] = array[rightPos++];

        for (int i = 0; i < numElement; i++, rightEnd--)
            array[rightEnd] = tempArray[rightEnd];
    }

    /**
     * Quicksort algorithm
     *
     * @param array an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] array) {
        quicksort(array, 0, array.length - 1);
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Use median-of-three partitioning and a cutoff of 10
     *
     * @param array an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] array, int left, int right) {
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(array, left, right);

            int i = left, j = right - 1;
            while (i < j) {
                while (array[++i].compareTo(pivot) < 0) {
                }
                while (array[--j].compareTo(pivot) > 0) {
                }

                if (i < j)
                    swapReferences(array, i, j);
            }

            swapReferences(array, i, right - 1);

            quicksort(array, left, i - 1);
            quicksort(array, i + 1, right);
        } else
            insertionSort(array, left, right);
    }

    /**
     * Internal insertion sort routine for subarrays
     * that is used by quicksort.
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static void insertionSort(Comparable[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            Comparable temp = a[p];
            int j;

            for (j = p; j > left && temp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = temp;
        }
    }

    /**
     * Return median of left, center, and right
     * Order these and hid the pivot.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[center].compareTo(array[left]) < 0)
            swapReferences(array, left, right);

        if (array[right].compareTo(array[left]) < 0)
            swapReferences(array, left, right);

        if (array[right].compareTo(array[center]) < 0)
            swapReferences(array, center, right);

        // Place pivot at position right-1
        swapReferences(array, center, right - 1);
        return array[right - 1];
    }


    public static void main(String[] args) {
        shellSort(new Integer[]{1, 2, 3, 4, 7, 3, 5, 4, 6});
    }
}
