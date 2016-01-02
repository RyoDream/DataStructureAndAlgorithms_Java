package Chapter2;

public class Person {
    private String lastName;
    private String firstName;
    private int age;

    public Person(String last, String first, int a) {
        this.lastName = last;
        this.firstName = first;
        this.age = a;
    }

    public void displayPerson() {
        System.out.print("Last name: " + lastName);
        System.out.print(", First name: " + firstName);
        System.out.println(", Age: " + age);
    }

    public String getLast() {
        return lastName;
    }
}

class ClassDataArray {
    private Person[] a;
    private int nElems;

    public ClassDataArray(int max) {
        a = new Person[max];
        nElems = 0;
    }

    public Person find(String searchName) {
        int i;
        for (i=0;i<nElems;i++) {
            if(a[i].getLast().equals(searchName))
                return a[i];
        }

        return null;
    }

    public void insert(String last, String first, int age) {
        // if reaches the maximum size, then insert to the last element
        if(nElems == a.length)
            nElems--;
        a[nElems++] = new Person(last,first,age);
    }

    public boolean delete(String searchName) {
        int i;
        for (i=0;i<nElems;i++) {
            if(a[i].getLast().equals(searchName))
                break;
        }

        if(i==nElems)
            return false;
        else {
            int top = (nElems == a.length)?(nElems-1):nElems;
            for(int j=i;j<top;j++)
                a[j] = a[j+1];
            nElems--;
            return true;
        }
    }

    public void displayA() {
        for (int i=0;i<nElems;i++) {
            a[i].displayPerson();
        }
    }
}

class ClassDataApp
{
    public static void main(String[] args)
    {
        int maxSize=3;
        ClassDataArray arr = new ClassDataArray(maxSize);

        arr.insert("Evans","Patty",20);
        arr.insert("Chris","Quan",24);
        arr.insert("Ryo", "Dream", 24);
        arr.insert("Adams", "Henry", 32);

        arr.displayA();

        String searchKey = "Simon";
        Person found = arr.find(searchKey);

        if(found != null) {
            found.displayPerson();
        }
        else System.out.println("Can't find "+searchKey);

        arr.delete("Adams");
        arr.delete("Hehe");

        arr.displayA();
    }
}



