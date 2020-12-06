package com.ds.example.implementations;

import com.ds.example.interfaces.Collection;
import com.ds.example.interfaces.Deque;
import com.ds.example.interfaces.List;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public LinkedList() {

    }
    public LinkedList(Collection<? extends E> c){
        this();
        addAll(c);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

    }
}
