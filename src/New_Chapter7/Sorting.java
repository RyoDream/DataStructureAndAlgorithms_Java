package New_Chapter7;

public class Sorting {

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

    public static void main(String[] args) {
        shellSort(new Integer[]{1, 2, 3, 4, 7, 3, 5, 4, 6});
    }
}
