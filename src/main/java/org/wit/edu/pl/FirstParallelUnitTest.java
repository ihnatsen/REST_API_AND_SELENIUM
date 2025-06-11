package org.wit.edu.pl;

import java.math.BigInteger;

public class FirstParallelUnitTest{

    public void run() throws Exception{
        System.out.println("FirstParallelUnitTest first() start => " + Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("FirstParallelUnitTest first() end => " + Thread.currentThread().getName());
    }

    public static long calculateFibonacciNumber(int n){
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        return calculateFibonacciNumber(n-1) + calculateFibonacciNumber(n-2);

    }
}