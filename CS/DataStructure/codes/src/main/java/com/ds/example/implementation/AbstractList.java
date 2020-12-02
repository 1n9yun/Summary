package com.ds.example.implementation;

import com.ds.example.Collection;
import com.ds.example.List;

import java.util.Iterator;
import java.util.ListIterator;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    public abstract E get(int index);

    protected int modCount;

    public boolean add(E e){
        add(size(), e);
        return true;
    }
    public void add(int index, E element){
        throw new UnsupportedOperationException();
    }
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o){
        ListIterator<E> it = listIterator();
    }

    public boolean addAll(int index, Collection<? extends E> c){

    }

    public void clear(){

    }

    private class Itr implements Iterator<E> {
//        next의 호출에 반환될 후속 element의 index
        int cursor = 0;
//        가장 최근에 next or previous의 호출에 반환된 element의 index
//        이 element가 remove의 호출에 의해 삭제된다면 -1로 reset
        int lastRet = -1;
//        iterator가 backing하는 List가 가져야할 modCount라고 여기는 값
//        이런 expectation이 violate되면 concurrent modification을 감지한 것.
        int expectedModCount = modCount;

        public boolean hasNext() {return cursor != size();}
    }
}
