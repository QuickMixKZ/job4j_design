package ru.job4j.kiss;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MaxMinTest {

    @Test
    public void whenTenIsMax() {
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.max(List.of(1, 5, 10, 9, 4), Integer::compare);
        assertEquals(Integer.valueOf(10), result);
    }

    @Test
    public void whenMinusFiveIsMin() {
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.min(List.of(1, 5, 10, -5, 4), Integer::compare);
        assertEquals(Integer.valueOf(-5), result);
    }
}