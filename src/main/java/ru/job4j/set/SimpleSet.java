package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean result = true;
        for (int i = 0; i < set.size(); i++) {
            if (value != null && value.equals(set.get(i))) {
                result = false;
                break;
            } else if (value == null && value == set.get(i)) {
                result = false;
                break;
            }
        }
        if (result) {
            set.add(value);
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (int i = 0; i < set.size(); i++) {
            if (value != null && value.equals(set.get(i))) {
                result = true;
                break;
            } else if (value == null && value == set.get(i)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}