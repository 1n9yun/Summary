package com.ds.example;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
todo:
    see iterator, spliterator, stream, parallelStream
        AbstractCollection
 */
public interface Collection<E> extends Iterable<E>{
    /**
    returns true if this collection changed as a result of the call.
    returns false if this collection does not permit duplicates and already contains the specified element.
    @param e
    @return a boolean
    @throws UnsupportedOperationException if the add operation is not supported by this collection
    @throws ClassCastException - if the class of the specified element prevents it from being added to this collection
    @throws NullPointerException - if the specified element is null and this collection does not permit null elements
    @throws IllegalArgumentException - if some property of the element prevents it from being added eto this collection
    @throws IllegalStateException - if the element cannot be added at this time due to insertion restrictions.
     */
    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
    void clear();
    
    /*
    returns true if this collection contains the specified element.
    더 공식적으로는, 최소 하나의 (o == null ? e == null : o.equals(e))인 e를
    포함하면 true 리턴

    ClassCastException (optional) - if the type of the specified element is incompatible with this collection
    NullPointerException (optional)
     */
    boolean contains(Object o);
    boolean containsAll(Collection<?> c);

    /*
    compares the specified object with this collection for equality
    List, Set interface를 구현하지 않는 collection은 List, Set을 구현한 Collection과 비교할 때
    false를 반환해야 한다.
    See Also :
        Object.equals(), Set.equals(), List.equals()
     */
    boolean equals(Object o);

    /*
    See Also:
        Object.hashCode(), Object.equals()
     */
    int hashCode();
    
    /*
    returns true if this collection contains no elements.
     */
    boolean isEmpty();

    /*
    returns an iterator over the elements in this collection.
    리턴되는 elements들의 순서에 대한 보증이 없다. (이 컬렉션이 보증을 제공하는 일부 클래스의 인스턴스가 아닌 경우.)
     */
    Iterator<E> iterator();

    /*
    spliterator 메소드가 splitarator를 return할 수 없는 경우에 overridden 되어야 함
    IMUUTABLE, CONCURRENT or late-binding 인 경우.
     */
    default Stream<E> parallelStream(){
        return null;
    }
    /*
    remove a single instance of the specified element from this collection, if it is present
    more formally, removes an element e such that (o == null ? e == null : o.equals(e)), if this collection contains one or more such elements.
    returns true if this collection contained the specified element (equivalently, collection에 변화를 준 call인 경우)
     */
    boolean remove(Object o);
    boolean removeAll(Collection<?> c);

    /*
    returns true if any elements were removed
    since 1.8 ~
     */
    default boolean removeIf(Predicate<? super E> filter){
        return false;
    }

    /*
    retains only the elements in this collection that are contained in the specified collection
    in other words, removes from this collection all of its elements that are not contained in the sprcified collection
    returns true if this collection changed as a result of the call
     */
    boolean retainAll(Collection<?> c);
    
    /*
    returns the number of elements in this collection.
    if more than Integer.MAX_VALUE elements,
    returns Integer.MAX_VALUE
     */
    int size();

    /*
    병렬 처리를 위한 iterator interface
    since 1.8 ~
     */
    default Spliterator<E> spliterator(){
        return null;
    }
    default Stream<E> stream(){
        return null;
    }
    /*
    returns an array containing all of the elements in this collection.
    만약 iterator가 순서에 대한 보증이 있다면 return되는 elements들 또한 같은 순서를 가진다.
    array based API와 collection based API의 다리 역할
     */
    Object[] toArray();
    <T> T[] toArray(T[] a);

//    + forEach from iterable
}