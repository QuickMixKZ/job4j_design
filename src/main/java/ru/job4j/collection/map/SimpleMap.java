package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        modCount++;
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key));
        boolean result = table[index] == null;
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
       }
       return result;
    }

    private int hash(K key) {
        int h = key == null ? 0 : key.hashCode();
        return key == null ? 0 : (h) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        table = Arrays.copyOf(table, capacity);
    }

    private int getIndex(int hash) {
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && hash(table[i].key) == hash) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public V get(K key) {
        int index = getIndex(hash(key));
        return index != -1 ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        modCount++;
        int index = getIndex(hash(key));
        boolean result = index != -1;
        if (result) {
            MapEntry<K, V> entry = table[index];
            entry.key = null;
            entry.value = null;
            table[index] = null;
            count--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}