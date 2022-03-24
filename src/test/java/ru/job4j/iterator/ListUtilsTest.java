package ru.job4j.iterator;


import org.junit.Test;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveEqualOrBiggerThanThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 1, 5, 2));
        ListUtils.removeIf(input, integer -> integer >= 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 1, 2)));
    }

    @Test
    public void whenReplaceToMinusOneIfLessThanZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, -5, -2, 3, 1, 5, -10));
        ListUtils.replaceIf(input, integer -> integer < 0, -1);
        assertThat(input, is(Arrays.asList(0, -1, -1, 3, 1, 5, -1)));
    }

    @Test
    public void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> secondList = new ArrayList<>(Arrays.asList(4, 5));
        ListUtils.removeAll(input, secondList);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

}