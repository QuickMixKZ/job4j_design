package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.design.srp.AccountingReportEngine.RATE;

import org.junit.Test;
import ru.job4j.design.ocp.JsonReportEngine;
import ru.job4j.design.ocp.XMLReportEngine;
import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccountingReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * RATE).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHrGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Petr", now, now, 50);
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker);
        store.add(worker1);
        Report engine = new HrReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ProgrammerReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>\n"
                        + "\t<head>\n"
                        + "\t\t<title>Report for programmers</title>\n"
                        + "\t</head>")
                .append("<body>")
                .append("<p>")
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</b>")
                .append("</body>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJsonGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String dateString = formatter.format(now.getTime());
        Employee worker = new Employee("Petr", now, now, 50);
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker);
        store.add(worker1);
        Report engine = new JsonReportEngine(store);
        String template = "{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}";
        String expect = "{\"employees\":[" +
                String.format(Locale.US, template + ",", worker.getName(), dateString, dateString, worker.getSalary()) +
                String.format(Locale.US, template, worker1.getName(), dateString, dateString, worker1.getSalary()) +
                "]}";
        String actual = engine.generate(em -> true);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenXMLGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String dateString = formatter.format(now.getTime());
        Employee worker = new Employee("Petr", now, now, 50);
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker);
        store.add(worker1);
        Report engine = new XMLReportEngine(store);
        String actual = engine.generate(em -> true);
        String template =
                "    <employee name=\"%s\" hired=\"%s\" fired=\"%s\" salary=\"%.1f\"/>\n";
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append(String.format(Locale.US, template,
                        worker.getName(), dateString, dateString, worker.getSalary()))
                .append(String.format(Locale.US, template,
                        worker1.getName(), dateString, dateString, worker1.getSalary()))
                .append("</employees>\n");
        assertThat(actual, is(expect.toString()));
    }
}