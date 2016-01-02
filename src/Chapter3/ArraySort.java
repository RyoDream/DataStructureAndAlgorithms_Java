package Chapter3;

import javax.sound.midi.SysexMessage;

public class ArraySort {
    private long[] a;
    private int nElems;

    public ArraySort(int max){
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        if (nElems == a.length)
            --nElems;
        a[nElems++] = value;
    }

    public void display() {
        for(int i=0;i<nElems;i++)
            System.out.print(a[i]+" ");
        System.out.println(" ");
    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
        display();
    }

    public void bubbleSort() {
        int in,out;

        for (out=nElems-1;out>1;out--) {
            for (in=0;in<out;in++) {
                if (a[in] > a[in+1])
                    swap(in,in+1);
            }
        }
    }

    // 双向bubblesort
    public void shakedBubbleSort() {
        int shift,left=0,right=nElems-1;

        while(right>left) {
            shift=left;
            while (shift<right) {
                if(a[shift] > a[shift+1])
                    swap(shift,shift+1);
                shift++;
            }

            while(shift > left) {
                if (a[shift] < a[shift-1])
                    swap(shift,shift-1);
                shift--;
            }
            left++;
            right--;
        }
    }

    public void selectionSort() {
        int out,in,min;

        for (out=0;out<nElems-1;out++) {
            min = out;
            for(in=out+1;in<nElems;in++) {
                if(a[in] < a[min])
                    min = in;
            }
            swap(out, min);
        }
    }

    public void insertionSort() {
        int in,out;

        for(out=1;out<nElems;out++) {
            long temp = a[out];
            in = out;

            // a[in-1] >= temp :不稳定
            // a[in-1] > temp :稳定
            while (in > 0 && a[in-1]>=temp) {
                a[in] = a[in-1];
                --in;

            }
            a[in] = temp;
        }
    }

    public void noDups() {
        insertionSort();
        long[] result = new long[nElems];

        int j=0;
        for(int i=0;i<nElems;i++) {
            if(a[i] != a[i+1]) {
                result[j++] = a[i];
            }
        }
        a = result;
        nElems = j;
    }

    public double median() {
        insertionSort();

        if(nElems%2 == 0)
            return (double)(a[nElems/2]+a[nElems/2-1])/2.0;
        else
            return (double)a[nElems/2];
    }

    public static void main(String[] args) {
        int maxSize = 10;
        ArraySort arr = new ArraySort(maxSize);

        arr.insert(7);
        arr.insert(17);
        arr.insert(34);
        arr.insert(56);
        arr.insert(23);
        arr.insert(56);
        arr.insert(12);
        arr.insert(13);

        arr.display();

//        arr.bubbleSort();
//        arr.selectionSort();
//        arr.insertionSort();
//        arr.shakedBubbleSort();
//        arr.display();

        arr.noDups();
        arr.display();

    }

}
