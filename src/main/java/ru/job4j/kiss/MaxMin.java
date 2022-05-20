package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator.reversed());
    }

    private <T> T find(List<T> value, Comparator<T> comparator) {
        if (value.size() == 0) {
            throw new IllegalArgumentException();
        }
        T result = value.get(0);
        for (T t : value) {
            result = comparator.compare(result, t) > 0 ? result : t;
        }
        return result;
    }
}
