package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutIsTrue() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("user1", 1));
        assertTrue(map.put("user2", 2));
        assertTrue(map.put("user3", 3));
        assertTrue(map.put("user4", 4));
        assertTrue(map.put("user5", 5));
        assertTrue(map.put("user6", 6));
        assertTrue(map.put("user7", 7));
        assertTrue(map.put("user8", 8));
        assertTrue(map.put("user9", 9));
    }

    @Test
    public void whenPutIsFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("user1", 1));
        assertFalse(map.put("user1", 2));
    }

    @Test
    public void whenGetIsValue() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        assertEquals(value, map.get(key));
    }

    @Test
    public void whenGetIsNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        assertNull(map.get("user2"));
    }

    @Test
    public void whenRemoveIsTrue() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        assertTrue(map.remove(key));
    }

    @Test
    public void whenRemoveIsFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        assertFalse(map.remove("user2"));
    }

    @Test
    public void whenIteratorHasNext() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenIteratorHasNoNext() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenHasNext() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(key, iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNoNext() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String key = "user1";
        Integer value = 1;
        map.put(key, value);
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(key, iterator.next());
        iterator.next();
    }

}