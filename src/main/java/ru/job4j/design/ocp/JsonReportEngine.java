package ru.job4j.design.ocp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private Store store;

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = new ArrayList<>(store.findBy(filter));
        Gson lib = new GsonBuilder().create();
        return lib.toJson(employees);
    }
}
