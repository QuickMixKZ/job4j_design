package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedList<E> implements List<E> {

    int size;
    int modCount;
    Node<E> first;
    Node<E> last;

    @Override
    public void add(E value) {
        size++;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

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
            public E next() {
                return null;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

