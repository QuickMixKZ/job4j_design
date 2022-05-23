package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ProgrammerReportEngine implements Report {

    private Store store;

    public ProgrammerReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>");
        text.append("<html>\n"
                + "\t<head>\n"
                + "\t\t<title>Report for programmers</title>\n"
                + "\t</head>");
        text.append("<body>");
        text.append("<p>");
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</b>");
        text.append("</body>");
        return text.toString();
    }
}
