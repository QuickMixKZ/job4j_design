package ru.job4j.design.ocp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private Store store;

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees();
        employees.setEmployees(store.findBy(filter));
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new CalendarAdapterJson());
        builder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapterJson());
        Gson gson = builder.create();
        return gson.toJson(employees);
    }
}
