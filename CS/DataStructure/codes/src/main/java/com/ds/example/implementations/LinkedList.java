package com.ds.example.implementations;

import com.ds.example.interfaces.Collection;
import com.ds.example.interfaces.Deque;
import com.ds.example.interfaces.List;

import java.io.Serializable;

public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {
    int size = 0;
    Node<E> first;
    Node<E> last;

    public LinkedList() {

    }
    public LinkedList(Collection<? extends E> c){
        this();
        addAll(c);
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
}
