package com.example;

/*
 Interface with default and static methods.
 Java 8 allows default and static methods inside interface.
*/
interface Vehicle {

    static void serviceInfo() {
        System.out.println("Vehicle service is required every 6 months");
    }

    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }
}
