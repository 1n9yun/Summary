package com.ds.example.implementations;

import com.ds.example.interfaces.Collection;
import com.ds.example.interfaces.Deque;
import com.ds.example.interfaces.List;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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

    private void linkFirst(E e){
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;

        if(f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }
    private void linkLast(E e){
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;

        if(l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
    private E unlinkFirst(Node<E> f){
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if(next == null)
            last = null;
        else
            next.prev = null;

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
        Node<E> prev;
        E item;
        Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    Node<E> node(int index){
        if(index < (size >> 1)){
            Node<E> x = first;
            for(int i = 0; i<index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        private int expectedModCount = modCount;

        ListItr(int index){
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() { return nextIndex < size; }
        public E next(){
            checkForComodification();
            if(!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() { return nextIndex > 0; }
        public E previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() { return nextIndex; }
        public int previousIndex() { return nextIndex - 1; }

        public void remove(){
            checkForComodification();
            if(lastReturned == null)
                throw new IllegalStateException();
        }

        final void checkForComodification(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
