package com.ds.example.interfaces;

import java.util.Iterator;

public interface Deque<E> extends Queue<E>{
    boolean add(E e);
    boolean contains(Object o);

//    반대방향 iterator
    Iterator<E> descendingIterator();
    Iterator<E> iterator();
    E element();

    boolean offer(E e);
    E getLast();
    E getFirst();

    void addFirst(E e);
    void addLast(E e);

    boolean offerFirst(E e);
    boolean offerLast(E e);
    E peek();
    E peekFirst();
    E peekLast();
    E poll();
    E pollFirst();
    E pollLast();
    E pop();
    void push(E e);
    E remove();
    boolean remove(Object o);
    E removeFirst();
    boolean removeFirstOccurrence(Object o);
    E removeLast();
    boolean removeLastOccurrence(Object o);
    int size();
}
