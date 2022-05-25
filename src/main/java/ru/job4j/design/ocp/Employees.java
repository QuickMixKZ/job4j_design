package ru.job4j.design.ocp;


import ru.job4j.design.srp.Employee;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {

    private List<Employee> employees;

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employees() { }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
