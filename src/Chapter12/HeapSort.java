package Chapter12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapSort {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public HeapSort(int max) {
        maxSize = max;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];
        while (index < currentSize / 2) {  // not on bottom row
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize &&
                    heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;

            if (top.getKey() >= heapArray[largerChild].getKey())
                break;

            // shift child up
            heapArray[index] = heapArray[largerChild];

            // go down
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null)
                System.out.print(heapArray[i].getKey() + " ");
            else
                System.out.print("-- ");
        }
        System.out.println();

        int nBlank = 32;
        int itemsPerRow = 1;
        int column = 0;
        int i = 0;
        String dots = ".................................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0)
                for (int k = 0; k < nBlank; k++)
                    System.out.print(' ');

            System.out.print(heapArray[i].getKey());
            if (++i == currentSize)
                break;

            if (++column == itemsPerRow) {
                nBlank /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlank * 2 - 2; k++)
                    System.out.print(' ');
        }
        System.out.println("\n" + dots + dots);
    }

    public void displayArray() {
        for (int i = 0; i < maxSize; i++)
            System.out.print(heapArray[i].getKey() + " ");
        System.out.println();
    }

    public void insertAt(int index, Node newNode) {
        heapArray[index] = newNode;
    }

    public void incrementSize() {
        currentSize++;
    }

    public static void main(String[] args) throws IOException {
        int size, i;
        System.out.print("Enter number of items: ");
        size = getInt();
        HeapSort theHeap = new HeapSort(size);

        for (i = 0; i < size; i++) {
            int random = (int) (java.lang.Math.random() * 100);
            Node newNode = new Node(random);
            theHeap.insertAt(i, newNode);
            theHeap.incrementSize();
        }

        System.out.print("Random: ");
        theHeap.displayArray();

        for (i = size / 2 - 1; i >= 0; i--) {
            theHeap.trickleDown(i);
//            theHeap.displayHeap();
        }

        System.out.print("Heap: ");
        theHeap.displayArray();
        theHeap.displayHeap();

        for (i = size - 1; i >= 0; i--) {
            Node biggestNode = theHeap.remove();
            theHeap.insertAt(i, biggestNode);
            theHeap.displayHeap();
        }

        System.out.print("Sorted: ");
        theHeap.displayArray();

    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
