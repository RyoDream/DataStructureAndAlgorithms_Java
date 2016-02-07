package Chapter6;

public class Exercises {

    public int mult(int x, int y) {
        if (y == 0)
            return 0;
        else
            return x + mult(x, y - 1);
    }


    public int power(int x, int y) {
        if (y > 1) {
            int res = power(x * x, y / 2);
            if (y % 2 == 1)
                res *= x;
            return res;
        } else
            return x;
    }

    public static void main(String[] args) {
        Exercises exercises = new Exercises();
        System.out.println(exercises.power(3, 5));
        System.out.println(exercises.mult(4, 6));
    }
}
