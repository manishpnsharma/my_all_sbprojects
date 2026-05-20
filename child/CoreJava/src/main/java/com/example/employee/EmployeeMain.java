package com.example.employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeMain {
    public static void main(String[] args) {

        List<Employee> employeeList = List.of(
                new Employee(null, "Smith", "John", "IT",50000),
                new Employee(2, null, "Jane", "CS" ,60000),
                new Employee(3, "Brown", null, "IT",55000),
                new Employee(4, "Wilson", "Dave", null,7000),
                new Employee(5, "Johnson", "Emily", "CS",null),
                new Employee(5, "Johnson", "EmilyFF", "CS",8000),
                new Employee(6, "Anderson", "Tim", "IT",62000));
        sortByEmployeeName(employeeList);
    }

    public static void sortByEmployeeName(List<Employee> employeeList) {
        List<Employee> employeenameList = employeeList.stream()
                .sorted(
                        Comparator
                                .comparing(Employee::getEmpFirstName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                                .thenComparing(Employee::getEmplSalary, Comparator.nullsLast(Comparator.reverseOrder()))
                                .thenComparing(Employee::getEmplId, Comparator.nullsLast(Comparator.reverseOrder()))
        ).collect(Collectors.toList());
        employeenameList.forEach((k) -> System.out.println(" "+ k.getEmpFirstName() + " " + k.getEmplSalary()));


        // Group employees by department
        Map<String, List<Employee>> byDept = employeeList.stream()
                .filter(e -> e.getEmplSalary() != null)
                .filter(e -> e.getEmpDepName() != null)
                .filter(e -> e.getEmpFirstName() != null)
                .filter(e -> e.getEmplId() != null)
                .filter(e -> e.getEmplastName() != null)
                .collect(Collectors.groupingBy(Employee::getEmpDepName));

        System.out.println("===========================");
// Compute sum of salaries by department
        Map<String, Integer> totalByDept = employeeList.stream()
                .filter(e -> e.getEmplSalary() != null)
                .filter(e -> e.getEmpDepName() != null)
                .filter(e -> e.getEmpFirstName() != null)
                .filter(e -> e.getEmplId() != null)
                .filter(e -> e.getEmplastName() != null)
                .collect(Collectors.groupingBy(Employee::getEmpDepName,
                        Collectors.summingInt(Employee::getEmplSalary)));
        totalByDept.forEach((k,v) -> System.out.println(" "+ k + " " + v));
    }
}
