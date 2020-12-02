package com.ds.example.implementation;

import com.ds.example.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractCollection<E> implements Collection<E> {
    public abstract Iterator<E> iterator();
    public abstract int size();

//    이 구현체는 항상 UnsupportedOperationException을 throw
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for(E e : c){
            if(add(e)) modified = true;
        }
        return modified;
    }

    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        if(o == null){
            while(it.hasNext()){
                if(it.next() == null) return true;
            }
        }else {
            while(it.hasNext())
                if(o.equals(it.next()))
                    return true;
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for(Object e : c){
            if(!contains(e)) return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if(o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
            }
        }else{
            while(it.hasNext()){
                if(it.next().equals(o)){
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
//        c == null인 경우 throw npe.
        Objects.requireNonNull(c);

        boolean modified = false;
        Iterator<E> it = iterator();

        while(it.hasNext()){
            if(c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while(it.hasNext()){
            if(!c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Iterator<E> it = iterator();

        for(int i=0;i<arr.length;i++){
            if(it.hasNext()){
                arr[i] = it.next();
            }else
//                예상보다 elements가 적다면
                return Arrays.copyOf(arr, i);
        }

        return it.hasNext() ? finishToArray(arr, it) : arr;
    }
//    https://stackoverflow.com/questions/34658422/why-is-toarray-implemented-like-this-in-java
    private static <T> T[] finishToArray(T[] arr, Iterator<?> it){
        int i = arr.length;
        while(it.hasNext()){
            int cap = arr.length;
            if(i == cap){
                int newCap = cap + (cap >> 1) + 1;
                if(newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                arr = Arrays.copyOf(arr, newCap);
            }
            arr[i++] = (T)it.next();
        }
//        overallocated 됐다면 잘라주기
        return i == arr.length ? arr : Arrays.copyOf(arr, i);
    }
    private static int hugeCapacity(int minCapacity){
        if(minCapacity < 0) // 오버플로우
            throw new OutOfMemoryError("Required array size too large.");
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    public <T> T[] toArray(T[] a) {
        int size = size();
        T[] arr = a.length >= size ? a :
                (T[])java.lang.reflect.Array
                .newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for(int i=0;i<arr.length;i++){
//            예상보다 적다면?
            if(!it.hasNext()){
                if(a == arr){
                    arr[i] = null;
                }else if(a.length < i) {
                    return Arrays.copyOf(arr, i);
                }else{
                    System.arraycopy(arr, 0, a, 0, i);
                    if(a.length > i){
                        a[i] = null;
                    }
                }
                return a;
            }
            arr[i] = (T)it.next();
        }

        return it.hasNext() ? finishToArray(arr, it) : arr;
    }

    public void clear() {
        Iterator<E> it = iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }

    public String toString(){
        Iterator<E> it = iterator();
        if(!it.hasNext()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while(true){
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);

            if(!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
