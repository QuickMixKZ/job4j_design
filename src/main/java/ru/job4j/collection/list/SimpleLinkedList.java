package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    int size;
    int modCount;
    Node<E> first;
    Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private Node<E> currentNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != last;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode == null ? first : currentNode.next;
                return currentNode.item;
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

