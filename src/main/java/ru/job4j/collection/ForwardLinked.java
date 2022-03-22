package ru.job4j.collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        boolean result;
        if (size == 0 || size == 1) {
            result = false;
        } else {
            Node<T> previous = null;
            Node<T> currentNode = head;
            Node<T> nextNode;
            while (currentNode != null) {
                nextNode = currentNode.next;
                currentNode.next = previous;
                previous = currentNode;
                currentNode = nextNode;
            }
            head = previous;
            result = true;
        }
        return result;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> deletedNode = head;
        T deletedValue = head.value;
        head = head.next;
        deletedNode.value = null;
        deletedNode.next = null;
        size--;
        return deletedValue;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}