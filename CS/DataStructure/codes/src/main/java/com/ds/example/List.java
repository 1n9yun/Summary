package com.ds.example;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

/*
todo:
    see AbstractList, AbstractSequentialList
 */

public interface List<E> extends Collection<E> {
    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
    void clear();
    boolean contains(Object o);
    boolean containsAll(Collection<?> c);

    /**
     *
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list
     *         specified object is also a list && both lists have the same size && all corresponding pairs of elements in the two lists are equal.
     */
    boolean equals(Object o);

    /**
     *
     * @return returns hash code value for this list.
     *         the hash code of a list is defined to be the result of the following calculation:
     *         <code>
     *             int hashCode = 1;
     *             for(E e : list)
     *                  hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
     *         </code>
     *         This ensures that list1.equals(list2) implies that list1.hashCode() == list2.hashCode() for any two lists.
     */
    int hashCode();
    boolean isEmpty();
    Iterator<E> iterator();
    boolean remove(Object o);
    boolean removeAll(Collection<?> c);
    boolean retainAll(Collection<?> c);
    int size();

    /*
    SIZED & ORDERED
     */
    default Spliterator<E> spliterator(){
        return null;
    }
    Object[] toArray();
    <T> T[] toArray(T[] a);


    /**
     * list의 특정 위치에 특정 element를 삽입
     * 현재 해당 위치에 있는 element와 그 뒤의 element들을 오른쪽으로 shift 한다.
     * @param index specified element가 삽입될 위치.
     * @param element 삽입될 element
     * @throws UnsupportedOperationException if the addAll operation is not supported by this list
     * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this list
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not permit null elemnets, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    void add(int index, E element);

    /**
    * 기존 element 들은 새 element 들의 오른쪽에 위치한다.
    * 이 동작이 진행되는 도중에 collection이 수정될 경우에는 이 동작이 정의되지 않는다.
    * (해당 collection이 List, 비어있지 않은 경우 발생)
    * @throws UnsupportedOperationException if the addAll operation is not supported by this list
     * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this list
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not permit null elemnets, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     *
     * @return true if this list changed as a result of the call
    * */
    boolean addAll(int index, Collection<? extends E> c);

    /**
     * returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    E get(int index);

    /**
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position.
     * @return 해당 위치의 이전 element
     * @throws UnsupportedOperationException if the et operation is not supported by this list.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
     */
    E set(int index, E element);

    /**
     *
     * 해당 element와 처음으로 일치한 index를 반환. 해당 element를 포함하고 있지 않다면 -1를 반환.
     * @param o 찾을 element
     * @return list에서 해당 element가 처음 나타난 위치
     * @throws ClassCastException if the type of the specified element is incompatible with this list
     * @throws NullPointerException if the specified element is nul and this list does not permit null elements.
     */
    int indexOf(Object o);

    /**
     *
     * 마지막으로 나타난 위치를 반환, 포함하고 있지 않다면 -1을 반환
     * @param o 찾을 element
     * @return list에서 해당 element가 마지막으로 나타난 위치
     * */
    int lastIndexOf(Object o);
    ListIterator<E> listIterator();
    
    /*
    해당 index에서 시작하는 listIterator를 반환
     */
    ListIterator<E> listIterator(int index);

    /**
     * 뒤의 element들을 왼쪽으로 shift
     * @param index 제거될 element의 위치
     * @return 제거된 element
     * @throws UnsupportedOperationException if the remove operation is not supported by this list
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    E remove(int index);

    /**
     * list에서 fromIndex를 포함하고 toIndex를 제외한 범위의 view를 반환
     * if fromIndex == toIndex: return empty list
     * 반환되는 subList는 해당 list에 의해 지원되고, 따라서 subList의 비 구조적 변경에 대해 해당 list에도 반영되며
     * and vice-versa. (반대도 그러하다.)
     *  (일반적으로 array에 존재하는 종류의) range operation의 필요성을 제거(?)
     *  range operation은 subList를 passing 함으로써 사용될 수 있다.
     *  ex) list.subList(from, to).clear(); // from ~ to의 elements 제거
     *
     * Collection의 모든 Algorithms를 subList에 적용할 수 있다.
     *
     * (?) The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
     * 기존 list가 구조적으로 변경되는 경우 subList는 정의되지 않는다??
     *      비 정상적인 결과를 얻을 수 있다.
     *      size가 변경된다거나, 등등의 경우
     *      ConcurrentModificationException 발생
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     */
    List<E> subList(int fromIndex, int toIndex);

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     * specified comparator가 null인 경우 natural ordering을 따른다.
     * this list must be modifiable.
     * but need not e resizable.
     * @implNote 입력 배열이 부분적으로 정렬되어 있을 때 nlog(n) 보다 훨씬 적은 수의 비교를 필요로 하는 안정적이고 적응적이며 반복적인 mergesort.
     *           무작위로 정렬되어 있는 경우 기존 mergesort의 성능을 제공
     *           거의 정렬되어 있는 경우 n 개의 비교가 필요하다.
     *           The implementation was adapted from Tim Peters's list sort for Python ( TimSort).
     *
     * 기본 구현체는 all elements in this list를 포함하는 array를 만들고 array를 sort후
     * list를 iterating하면서 각 element와 일치하는 position에 resetting 한다.
     * (이 과정은 LinkedList에서 sort하는 n^2log(n) 퍼포먼스를 피하기 위함이다.)
     *
     * @param c the Comparator used to compare list elements. A null value indicates that the elements' natural ordering should be used.
     * @since 1.8
     */
    default void sort(Comparator<? super E> c){

    }
    /**
     * replaces each element of this list with the result of applying the operator to that element.
     * errors or runtime exceptions thrown by the operator are relayed to the caller.
     * @param operator - the operator to apply to each element
     * @throws UnsupportedOperationException if this list is unmodifiable. Implementations may throw this exception if an element cannot be replaced or if, in general, modification is not supported.
     * @throws NullPointerException if the specified operator is null or if the operator result is null value and this list does not permit null elements.
     * @since 1.8
     */
    default void replaceAll(UnaryOperator<E> operator){

    }

    /*
    inherited methods

    from Collection
    parallelStream, removeIf, stream

    from Iterable
    forEach
     */

}
