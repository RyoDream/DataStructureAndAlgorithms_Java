package Chapter10;

public class DataItem {
    public int iData;

    public DataItem(int id) {
        iData = id;
    }

    public void displayItem() {
        System.out.print("/" + iData);
    }
}
