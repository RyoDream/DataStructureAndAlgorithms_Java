package Chapter7;

public class ShellSort {

    private int[] theArray;
    private int nElems;

    public ShellSort(int maxSize) {
        theArray = new int[maxSize];
        nElems = -1;
    }

    public void insert(int value) {
        if (!isFull())
            theArray[++nElems] = value;
    }

    public boolean isFull() {
        return (nElems == theArray.length - 1);
    }

    public void display() {
        for (int i : theArray)
            System.out.print(i + " ");
        System.out.println("");
    }

    public void shellSort() {
        int inner, outer;
        int temp;

        int h = 1;
        while (h <= nElems / 3)
            h = h * 3 + 1;

        while (h > 0) {
            for (outer = h; outer <= nElems; outer++) {
                temp = theArray[outer];
                inner = outer;

                while (inner > h - 1 && theArray[inner - h] >= temp) {
                    theArray[inner] = theArray[inner - h];
                    inner -= h;
                }
                theArray[inner] = temp;
            }
            System.out.print("h: " + h + " --- ");
            display();
            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) {
        int maxSize = 20;
        ShellSort shell = new ShellSort(maxSize);

        for (int i = 0; i < maxSize; i++) {
            int n = (int) (java.lang.Math.random() * 99);
            shell.insert(n);
        }

        shell.display();
        shell.shellSort();
        shell.display();
    }
}
