# LinkedList

```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable
```

* **List와 Deque interface의 doubly-linked list의 구현체**

  * 모든 선택적인 list operation을 구현하고 null을 포함한 모든 element를 허용한다.

* **모든 동작은 doubly-linked list에 대해 예상 가능한 대로 동작한다.**

  * List에서 index되는 작업은 시작점 또는 끝점으로부터 list를 traverse할 것이다.
    * 지정된 index에 더 가까운 곳

* **이 구현체는 not synchronized**

  * 멀티 스레드가 한 linkedlist에 동시에 접근하여 최소 하나의 스레드가 list를 구조적으로 변경하는 경우 외부적으로 동기화되어야 한다.

    * 구조적 변경은 하나 또는 여러 element의 add, remove 동작
    * 단지 element의 값을 변경하는 것은 구조적 변경이 아니다.

  * This is typically accomplished by synchronizing on some object that naturally encapsulates the list. (what?)

    * 그러한 object가 없다면 List는 Collections.synchronizedList 메소드를 이용하여 "wrapped"되어야 한다.

      * List로의 비동기 접근을 방지하기 위해 creation time에 가장 잘 수행된다.

      * ```java
        List list = Collections.synchronizedList(new Linked(...));
        ```

* **이 클래스의 iterator, listIterator 메소드로부터 반환되는 iterator들은 fail-fast다.**(Iterator는 순차적 접근이 모두 끝나기 전에 콜렉션 객체에 변경이 일어날 경우 순차적 접근이 실패되면서 예외를 return하게 되는데 이를 fail-fast 방식이라고 부른다.)

  * Iterator가 생성된 이후 Iterator가 가지고 있는 add, remove 메소드를 제외한 어떤 방법으로든 List가 언제든 구조적으로 변경되면 ConcurrentModificationException을 throw한다.

  * 따라서 동시 수정에 직면했을 때 향후에 알 수없는 때에 임의적이고 비결정적인 행동을 위험하게 수행하기 보다는 iterator가 신속하고 깨끗하게 실패한다.

* **일반적으로, 비동기 동시 수정이 일어나면 hard guarantees할 수 없기 때문에 iterator의 fail-fast 동작을 보증할 수 없다.**

  * fail-fast iterator는 best-effort(시도는 하지만 결과는 보장하지 않는다.)에 기초하여 ConcurrentModificationException을 throw한다.
  * 따라서 정확성을 위해 이 exception에 의존하여 프로그램을 작성하는 것은 잘못될 것이다. 
    * fail-fast iterator는 버그를 감지하는데에만 사용되어야 한다.



## Methods

