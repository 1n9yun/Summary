package com.ds.example.implementations;

import com.ds.example.interfaces.Collection;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractSequentialList<E> extends AbstractList<E>{
    protected  AbstractSequentialList() {

    }

    public E get(int index){
        try{
            return listIterator(index).next();
        } catch(NoSuchElementException exc){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    public E set(int index, E element){
        try{
            ListIterator<E> it = listIterator(index);
            E oldVal = it.next();
            it.set(element);
            return oldVal;
        } catch (NoSuchElementException e){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    public void add(int index, E element){
        try{
            listIterator(index).add(element);
        }catch (NoSuchElementException e){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    public E remove(int index){
        try{
            ListIterator<E> it = listIterator(index);
            E outCast = it.next();
            it.remove();
            return outCast;
        } catch(NoSuchElementException e){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    public boolean addAll(int index, Collection<? extends E> c){
        try{
            boolean modified = false;
            ListIterator<E> it1 = listIterator(index);
            Iterator<? extends E> it2 = c.iterator();

            while(it2.hasNext()){
                it1.add(it2.next());
                modified = true;
            }
            return modified;
        }catch (NoSuchElementException e){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    public Iterator<E> iterator() {
        return listIterator();
    }
    public abstract ListIterator<E> listIterator(int index);
}