package org.wit.edu.pl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstParallelUnitTestTest {

    @Test
    void first() throws Exception {
        FirstParallelUnitTest test = new FirstParallelUnitTest();
        test.run();
    }

    @Test
    void second() throws Exception {
        FirstParallelUnitTest test = new FirstParallelUnitTest();
        test.run();
    }
    @Test
    void third() throws Exception {
        FirstParallelUnitTest test = new FirstParallelUnitTest();
        test.run();
    }
    @Test
    void fourth() throws Exception {
        FirstParallelUnitTest test = new FirstParallelUnitTest();
        test.run();
    }

    @Test
    void fifth() throws Exception {
        FirstParallelUnitTest test = new FirstParallelUnitTest();
        test.run();
    }

    @Test
    void sexeth() throws Exception {
        FirstParallelUnitTest test = new FirstParallelUnitTest();
        test.run();
    }
    @Test
    void fibonacci1(){
        System.out.println(FirstParallelUnitTest.calculateFibonacciNumber(50));
    }
    @Test
    void fibonacci2(){
        System.out.println(FirstParallelUnitTest.calculateFibonacciNumber(50));
    }
    @Test
    void fibonacci3(){
        System.out.println(FirstParallelUnitTest.calculateFibonacciNumber(50));
    }
    @Test
    void fibonacci4(){
        System.out.println(FirstParallelUnitTest.calculateFibonacciNumber(50));
    }
    @Test
    void fibonacci5(){
        System.out.println(FirstParallelUnitTest.calculateFibonacciNumber(50));
    }
}