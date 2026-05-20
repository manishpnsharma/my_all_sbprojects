package com.example.employee;

public class Employee {
    Integer emplId;
    String emplastName;
    String empFirstName;
    Integer emplSalary;
    String empDepName;
    public Employee(Integer emplId, String emplastName, String empFirstName, String empDepName,Integer emplSalary) {
        this.emplId = emplId;
        this.emplastName = emplastName;
        this.empFirstName = empFirstName;
        this.emplSalary = emplSalary;
        this.empDepName = empDepName;
    }

    public String getEmpDepName() {
        return empDepName;
    }

    public void setEmpDepName(String empDepName) {
        this.empDepName = empDepName;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public String getEmplastName() {
        return emplastName;
    }

    public void setEmplastName(String emplastName) {
        this.emplastName = emplastName;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public Integer getEmplSalary() {
        return emplSalary;
    }

    public void setEmplSalary(Integer emplSalary) {
        this.emplSalary = emplSalary;
    }
}
