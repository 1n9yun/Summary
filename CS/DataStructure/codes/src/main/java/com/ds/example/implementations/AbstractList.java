package com.ds.example.implementations;

import com.ds.example.interfaces.Collection;
import com.ds.example.interfaces.List;
import com.ds.example.interfaces.RandomAccess;

import java.util.*;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    public abstract E get(int index);

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

    public Iterator<E> iterator() { return new Itr(); }
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }
    public ListIterator<E> listIterator(final int index) {
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

    public int indexOf(Object o) {
        ListIterator<E> it = listIterator();

        if(o == null){
            while(it.hasNext()){
                if(it.next() == null) return it.previousIndex();
            }
        }else{
            while(it.hasNext()){
                if(o.equals(it.next())) return it.previousIndex();
            }
        }

        return -1;
    }
    public int lastIndexOf(Object o){
        ListIterator<E> it = listIterator(size());

        if(o == null){
            while(it.hasPrevious()){
                if(it.previous() == null) return it.nextIndex();
            }
        }else{
            while(it.hasPrevious()){
                if(o.equals(it.previous())) return it.nextIndex();
            }
        }

        return -1;
    }

    public boolean addAll(int index, Collection<? extends E> c){
        rangeCheckForAdd(index);
        boolean modified = false;
        for(E e : c){
            add(index++, e);
            modified = true;
        }
        return modified;
    }
    public void clear(){
        removeRange(0, size());
    }

    protected void removeRange(int fromIndex, int toIndex){
        ListIterator<E> it = listIterator(fromIndex);

        for(int i=0;i<toIndex-fromIndex;i++){
            it.next();
            it.remove();
        }
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

        public boolean hasNext() {
            return cursor != size();
        }

        public E next(){
            checkForComodification();
            try{
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e){
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove(){
            if(lastRet < 0) throw new IllegalStateException();
            checkForComodification();

            try{
                AbstractList.this.remove(lastRet);
                if(lastRet < cursor) cursor--;
                lastRet = -1;
                expectedModCount = modCount;
            }catch (IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) { cursor = index; }

        public boolean hasPrevious() { return cursor != 0; }

        public E previous() {
            checkForComodification();
            try{
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e){
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() { return cursor; }
        public int previousIndex() { return cursor - 1; }

        public void set(E e){
            if(lastRet < 0) throw new IllegalStateException();
            checkForComodification();

            try{
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e){
            checkForComodification();

            try{
                int i = cursor;
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public List<E> subList(int fromIndex, int toIndex){
        return (this instanceof RandomAccess ?
                new RandomAccessSubList<>(this, fromIndex, toIndex) :
                new SubList<>(this, fromIndex, toIndex));
    }

    private void rangeCheckForAdd(int index){
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "index: " + index + ", size: " + size();
    }

    protected transient int modCount = 0;

}

class SubList<E> extends AbstractList<E> {
    private final AbstractList<E> l;
    private final int offset;
    private int size;

    SubList(AbstractList<E> list, int fromIndex, int toIndex){
        if(fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if(toIndex > list.size())
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if(fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");

        l = list;
        offset = fromIndex;
        size = toIndex - fromIndex;
        this.modCount = l.modCount;
    }

    public E set(int index, E element){
        rangeCheck(index);
        checkForComodification();
        return l.set(index + offset, element);
    }

    public E get(int index){
        rangeCheck(index);
        checkForComodification();
        return l.get(index + offset);
    }

    public int size(){
        checkForComodification();
        return size;
    }

    public void add(int index, E element){
        rangeCheckForAdd(index);
        checkForComodification();
        l.add(index + offset, element);
        this.modCount = l.modCount;
        size++;
    }

    public E remove(int index){
        rangeCheck(index);
        checkForComodification();
        E result = l.remove(index + offset);
        this.modCount = l.modCount;
        size--;
        return result;
    }

    protected void removeRange(int fromIndex, int toIndex){
        checkForComodification();
        l.removeRange(fromIndex + offset, toIndex + offset);
        this.modCount = l.modCount;
        size -= (toIndex - fromIndex);
    }

    public boolean addAll(Collection<? extends E> c){
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize == 0)
            return false;

        checkForComodification();
        l.addAll(index + offset, c);
        this.modCount = l.modCount;
        size += cSize;
        return true;

    }
    public Iterator<E> iterator() {
        return listIterator();
    }
    public ListIterator<E> listIterator(final int index){
        checkForComodification();
        rangeCheckForAdd(index);

        return new ListIterator<E>() {
            private final ListIterator<E> it = l.listIterator(index + offset);
            @Override
            public boolean hasNext() {
                return nextIndex() < size;
            }

            @Override
            public boolean hasPrevious() {
                return previousIndex() >= 0;
            }

            @Override
            public E next() {
                if(hasNext()){
                    return it.next();
                }else throw new NoSuchElementException();
            }

            @Override
            public E previous() {
                if(hasPrevious()){
                    return it.previous();
                }else throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() {
                return it.nextIndex() - offset;
            }

            @Override
            public int previousIndex() {
                return it.previousIndex() - offset;
            }

            @Override
            public void remove() {
                it.remove();
                SubList.this.modCount = l.modCount;
                size--;
            }

            @Override
            public void set(E e) {
                it.set(e);
            }

            @Override
            public void add(E e) {
                it.add(e);
                SubList.this.modCount= l.modCount;
                size++;
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex){
        return new SubList<>(this, fromIndex, toIndex);
    }

    private void rangeCheck(int index){
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private void rangeCheckForAdd(int index){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size();
    }
    private void checkForComodification() {
        if (this.modCount != l.modCount)
            throw new ConcurrentModificationException();
    }
}

class RandomAccessSubList<E> extends SubList<E> implements RandomAccess{
    RandomAccessSubList(AbstractList<E> list, int fromIndex, int toIndex){
        super(list, fromIndex, toIndex);
    }

    public List<E> subList(int fromIndex, int toIndex){
        return new RandomAccessSubList<>(this, fromIndex, toIndex);
    }
}
