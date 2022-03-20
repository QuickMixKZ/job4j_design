package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        modCount++;
        container[index]  = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T result = get(index);
        final int newSize = size - 1;
        if ((newSize) > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
            size = newSize;
            container[size] = null;
        }
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private T[] grow() {
        T[] newContainer;
        if (container.length > 0) {
            newContainer = Arrays.copyOf(container, container.length * 2);
        } else {
            newContainer =  (T[]) new Object[10];
        }
        return newContainer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }

        };
    }
}