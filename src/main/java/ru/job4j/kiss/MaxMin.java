package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        if (value.size() == 0) {
            throw new IllegalArgumentException();
        }
        T max = value.get(0);
        for (T t : value) {
            max = comparator.compare(max, t) > 0 ? max : t;
        }
        return max;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        if (value.size() == 0) {
            throw new IllegalArgumentException();
        }
        T min = value.get(0);
        for (T t : value) {
            min = comparator.compare(min, t) < 0 ? min : t;
        }
        return min;
    }
}
