package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(load(key)));
    }

    public V get(K key) {
        V result;
        SoftReference<V> softReference = cache.getOrDefault(key, new SoftReference<>(null));
        result  = softReference.get();
        if (result == null) {
            result = load(key);
            put(key, result);
        }
        return result;
    }

    protected abstract V load(K key);

}
