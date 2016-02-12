package Chapter7;


public class RadixSort {
    private int[] theArray;
    private int nElems;

    public RadixSort(int maxSize) {
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

    public int getDigit(int x, int d) {
        int a[] = {1, 1, 10, 100};
        return ((x / a[d]) % 10);
    }

    public void doRadixSort(int left, int right, int digit) {
        final int radix = 10;

        int[] count = new int[radix];
        int[] bucket = new int[right - left + 1];

        // 每一次循环,完成针对d位上的排序
        for (int d = 1; d <= digit; d++) {

            // 置空count
            for (int i = 0; i < radix; i++)
                count[i] = 0;

            // 这个循环的作用是统计d位数字为j的元素有几个
            // 循环结束后,d位上数字为j的元素有count[j]个
            for (int i = left; i <= right; i++) {
                int j = getDigit(theArray[i], d);
                count[j]++;
            }

            // 这个循环的作用是在int[] bucket中给元素预留位置
            // d位上数字为0的元素,放置在[0, count[0]-1]
            // d位上数字为1的元素,放置在[count[1-1], count[1]-1]
            // ....
            // d位上数字是j的元素,将放置在bucket数组下标为count[j-1]~count[j]-1的范围内
            for (int i = 1; i < radix; i++)
                count[i] += count[i - 1];


            // 从尾到头进行处理,保持排序的稳定性
            for (int i = right; i >= left; i--) {
                int j = getDigit(theArray[i], d);        // j表示theArray[i]在d位上的数字
                bucket[count[j] - 1] = theArray[i];   // count[j]-1是bucket数组中,最后一个对应j的下标
                count[j]--;                         // 由于前一个循环预留了位置,这里更新count[j]的值,作为下一个j所对应的下标
            }

            // 从bucket中把数据导到theArray中
            for (int i = left, j = 0; i <= right; i++, j++)
                theArray[i] = bucket[j];
        }
    }

    public void radioSort() {
        doRadixSort(0, nElems - 1, 3);
    }


    public static void main(String[] args) {
        int maxSize = 20;
        RadixSort radixSort = new RadixSort(maxSize);

        for (int i = 0; i <= maxSize; i++) {
            int n = (int) (java.lang.Math.random() * 999);
            radixSort.insert(n);
        }

        radixSort.display();
        radixSort.radioSort();
        radixSort.display();
    }

}
