package com.example;

/*
 Functional Interface:
 Only one abstract method is allowed.
 Used with Lambda Expression.
*/
@FunctionalInterface
interface MyCalculator {
    int calculate(int a, int b);
}
