package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Java8FeaturesDemo {
    public static void main(String[] args) {
        System.out.println("===== Java 8 Features Demo =====");
        List<Employee> employees = Arrays.asList(new Employee(1, "Amit", "IT", 75000), new Employee(2, "Neha", "HR", 50000), new Employee(3, "Rahul", "IT", 90000), new Employee(4, "Priya", "Finance", 65000), new Employee(5, "Karan", "Finance", 45000));

        // 1. Lambda Expression with Functional Interface
        System.out.println("\n1. Lambda Expression + Functional Interface");

        MyCalculator add = (a, b) -> a + b;
        MyCalculator multiply = (a, b) -> a * b;

        System.out.println("Addition: " + add.calculate(10, 20));
        System.out.println("Multiplication: " + multiply.calculate(10, 20));

        // 2. forEach with Lambda
        System.out.println("\n2. forEach with Lambda");

        employees.forEach(emp -> System.out.println(emp));

        // 3. Method Reference
        System.out.println("\n3. Method Reference");

        employees.forEach(System.out::println);

        // 4. Stream filter
        System.out.println("\n4. Employees with salary greater than 60000");

        List<Employee> highSalaryEmployees = employees.stream().filter(emp -> emp.getSalary() > 60000).collect(Collectors.toList());

        highSalaryEmployees.forEach(System.out::println);

        // 5. Stream map
        System.out.println("\n5. Employee names only");

        List<String> employeeNames = employees.stream().map(Employee::getName).collect(Collectors.toList());

        employeeNames.forEach(System.out::println);

        // 6. Stream sorted
        System.out.println("\n6. Employees sorted by salary ascending");

        List<Employee> sortedBySalary = employees.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());

        sortedBySalary.forEach(System.out::println);

        // 7. Sort by salary descending
        System.out.println("\n7. Employees sorted by salary descending");

        List<Employee> sortedBySalaryDesc = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());

        sortedBySalaryDesc.forEach(System.out::println);

        // 8. Find max salary
        System.out.println("\n8. Employee with highest salary");

        Optional<Employee> highestPaidEmployee = employees.stream().max(Comparator.comparing(Employee::getSalary));

        highestPaidEmployee.ifPresent(System.out::println);

        // 9. Optional Example
        System.out.println("\n9. Optional Example");

        Optional<Employee> optionalEmployee = findEmployeeByName(employees, "Rahul");

        optionalEmployee.ifPresent(emp -> System.out.println("Employee found: " + emp));

        Employee defaultEmployee = findEmployeeByName(employees, "John").orElse(new Employee(0, "Default Employee", "NA", 0));

        System.out.println("Result when employee not found: " + defaultEmployee);

        // 10. Grouping using Collectors
        System.out.println("\n10. Group employees by department");

        Map<String, List<Employee>> employeesByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        employeesByDepartment.forEach((department, empList) -> {
            System.out.println("Department: " + department);
            empList.forEach(System.out::println);
        });

        // 11. Average salary by department
        System.out.println("\n11. Average salary by department");

        Map<String, Double> averageSalaryByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        averageSalaryByDepartment.forEach((department, avgSalary) -> System.out.println(department + " : " + avgSalary));

        // 12. Count employees by department
        System.out.println("\n12. Count employees by department");

        Map<String, Long> employeeCountByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        employeeCountByDepartment.forEach((department, count) -> System.out.println(department + " : " + count));

        // 13. Default and static methods in interface
        System.out.println("\n13. Default and Static Methods in Interface");

        Vehicle car = new Car();
        car.start();
        car.stop();

        Vehicle.serviceInfo();

        // 14. Date and Time API
        System.out.println("\n14. Date and Time API");

        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(10);

        System.out.println("Today: " + today);
        System.out.println("After 10 days: " + futureDate);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Current Date Time: " + currentDateTime.format(formatter));

        // 15. CompletableFuture
        System.out.println("\n15. CompletableFuture Example");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Data loaded asynchronously";
        });

        future.thenAccept(result -> System.out.println("Result: " + result));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted");
        }
    }

    public static Optional<Employee> findEmployeeByName(List<Employee> employees, String name) {
        return employees.stream().filter(emp -> emp.getName().equalsIgnoreCase(name)).findFirst();
    }
}

