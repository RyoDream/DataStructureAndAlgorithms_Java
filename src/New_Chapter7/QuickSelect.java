package New_Chapter7;

public class QuickSelect {
    /**
     * Quick selection algorithm.
     * Places the kth smallest item in a[k-1].
     *
     * @param a an array of Comparable items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */
    public static void quickSelect(Comparable[] a, int k) {
        quickSelect(a, 0, a.length - 1, k);
    }

    /**
     * Internal selection method that makes recursive calls.
     * Place the kth smallest item at position k-1.
     *
     * @param array an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param k     the desired index (1 is the minimum) in the entire array.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSelect(AnyType[] array, int left, int right, int k) {
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

        if (k <= i)
            quickSelect(array, left, i - 1, k);
        else if (k > i + 1)
            quickSelect(array, i + 1, right, k);
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
}
