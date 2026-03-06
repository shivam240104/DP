// count ways to reach the nth stair, the person can climb either 1 stair or 2 stair at a time .

import java.util.*;
class climbingStairs{
   // recurssion
    private static int count(int n){
        if( n == 0)return 1;

        if(n < 0)return 0;

        return count(n - 1) + count(n -2);
    }

    //memoization

    private static int countWays(int n, int [] ways){
        if(n == 0) return 1;
        if( n < 0) return 0;

        if( ways[n] != -1) return ways[n];

        ways[n] = countWays(n -1, ways) + countWays( n - 2, ways);

        return ways[n];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ways[] = new int[n + 1];
        Arrays.fill(ways, -1);

        Thread t1 = new Thread(() -> {
            long start = System.nanoTime();
            int result = count(n);
            long end = System.nanoTime();

            System.out.println("Recursion Result: " + result);
            System.out.println("Recursion Time: " + (end - start) + " ns");
        });

        Thread t2 = new Thread(() -> {
            long start = System.nanoTime();
            int result = countWays(n, ways);
            long end = System.nanoTime();

            System.out.println("Memoization Result: " + result);
            System.out.println("Memoization Time: " + (end - start) + " ns");
        });

        // start threads
        t1.start();
        t2.start();

        // wait for both threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads finished execution");
    }
}
