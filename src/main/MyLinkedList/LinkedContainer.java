package main.MyLinkedList;

import java.util.Iterator;

public class LinkedContainer<E> implements Linked<E>, Iterable<E>, DescendingIterator<E> {
    private Node<E> fstNode;
    private Node<E> lastNode;
    private int size = 0;

    public LinkedContainer() {
        lastNode = new Node<E>(null, fstNode, null);
        fstNode = new Node<E>(null, null, lastNode);
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

    @Override
    public void addLast(E e) {
        Node<E> prev = lastNode;
        prev.setCurrentElement(e);
        lastNode = new Node<E>(null, prev, null);
        prev.setNextElement(lastNode);
        size++;
    }

    @Override
    public void addFirst(E e) {
        Node<E> next = fstNode;
        next.setCurrentElement(e);
        fstNode = new Node<>(null, null, next);
        next.setPrevElement(fstNode);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getElementByIndex(int counter) {
        Node<E> target = fstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getElementByIndex(counter++);
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {
                return getElementByIndex(counter--);
            }
        };
    }
}
