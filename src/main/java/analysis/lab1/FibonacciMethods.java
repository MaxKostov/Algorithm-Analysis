package analysis.lab1;

import java.util.HashMap;
import java.util.Map;

public class FibonacciMethods {

    // 1. Recursive Method
    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // 2. Dynamic Programming Method
    public static long fibonacciDP(int n) {
        if (n <= 1) {
            return n;
        }
        long[] fib = new long[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    // 3. Matrix Power Method
    public static long fibonacciMatrix(int n) {
        if (n <= 1) {
            return n;
        }
        long[][] F = {{1, 1}, {1, 0}};
        power(F, n - 1);
        return F[0][0];
    }

    private static void power(long[][] F, int n) {
        if (n <= 1) {
            return;
        }
        long[][] M = {{1, 1}, {1, 0}};
        power(F, n / 2);
        multiply(F, F);
        if (n % 2 != 0) {
            multiply(F, M);
        }
    }

    private static void multiply(long[][] F, long[][] M) {
        long x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        long y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        long z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        long w = F[1][0] * M[0][1] + F[1][1] * M[1][1];
        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    // 4. Binet Formula Method
    public static long fibonacciBinet(int n) {
        double phi = (1 + Math.sqrt(5)) / 2;
        double phi1 = (1 - Math.sqrt(5)) / 2;
        return (long) ((Math.pow(phi, n) - Math.pow(phi1, n)) / Math.sqrt(5));
    }

    // 5. Iterative Method
    public static long fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // 6. Memoization Method
    private static Map<Integer, Long> memo = new HashMap<>();

    public static long fibonacciMemoization(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long fib = fibonacciMemoization(n - 1) + fibonacciMemoization(n - 2);
        memo.put(n, fib);
        return fib;
    }

    public static void main(String[] args) {
        int[] smallInputs = {5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45};
        int[] largeInputs = {501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012, 6310, 7943, 10000, 12589, 15849};

        System.out.println("Warming up JVM...");
        warmUp();

        System.out.println("\nResults for small inputs:");
        displayResults(smallInputs);

        System.out.println("\nResults for large inputs:");
        displayLargeResults(largeInputs);
    }

    private static void warmUp() {
        for (int i = 0; i < 1000; i++) {
            fibonacciDP(20);
            fibonacciMatrix(20);
            fibonacciBinet(20);
            fibonacciIterative(20);
            memo.clear();
            fibonacciMemoization(20);
        }
    }

    private static void displayResults(int[] inputs) {
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                "n", "Recursive", "DP", "Matrix", "Binet", "Iterative", "Memoization");

        for (int n : inputs) {
            long startTime, endTime;

            startTime = System.nanoTime();
            long fibRecursive = fibonacciRecursive(n);
            endTime = System.nanoTime();
            double timeRecursive = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            long fibDP = fibonacciDP(n);
            endTime = System.nanoTime();
            double timeDP = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            long fibMatrix = fibonacciMatrix(n);
            endTime = System.nanoTime();
            double timeMatrix = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            long fibBinet = fibonacciBinet(n);
            endTime = System.nanoTime();
            double timeBinet = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            long fibIterative = fibonacciIterative(n);
            endTime = System.nanoTime();
            double timeIterative = (endTime - startTime) / 1e6;

            memo.clear();
            System.gc(); pause();
            startTime = System.nanoTime();
            long fibMemoization = fibonacciMemoization(n);
            endTime = System.nanoTime();
            double timeMemoization = (endTime - startTime) / 1e6;

            System.out.printf("%-10d %-15.3f %-15.3f %-15.3f %-15.3f %-15.3f %-15.3f%n",
                    n, timeRecursive, timeDP, timeMatrix, timeBinet, timeIterative, timeMemoization);
        }
    }

    private static void displayLargeResults(int[] inputs) {
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s%n",
                "n", "DP", "Matrix", "Binet", "Iterative", "Memoization");

        for (int n : inputs) {
            System.gc(); pause();
            long startTime = System.nanoTime();
            fibonacciDP(n);
            long endTime = System.nanoTime();
            double timeDP = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            fibonacciMatrix(n);
            endTime = System.nanoTime();
            double timeMatrix = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            fibonacciBinet(n);
            endTime = System.nanoTime();
            double timeBinet = (endTime - startTime) / 1e6;

            System.gc(); pause();
            startTime = System.nanoTime();
            fibonacciIterative(n);
            endTime = System.nanoTime();
            double timeIterative = (endTime - startTime) / 1e6;

            memo.clear();
            System.gc(); pause();
            startTime = System.nanoTime();
            fibonacciMemoization(n);
            endTime = System.nanoTime();
            double timeMemoization = (endTime - startTime) / 1e6;

            System.out.printf("%-10d %-15.3f %-15.3f %-15.3f %-15.3f %-15.3f%n",
                    n, timeDP, timeMatrix, timeBinet, timeIterative, timeMemoization);
        }
    }

    private static void pause() {
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}
    }
}
