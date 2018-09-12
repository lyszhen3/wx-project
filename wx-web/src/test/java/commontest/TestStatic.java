package commontest;

import java.util.concurrent.TimeUnit;

public class TestStatic {

    private static String HX = "HX";

    public static void main(String[] args) throws InterruptedException {

        long fib = fib(4);
        System.out.println(fib);

    }


    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
