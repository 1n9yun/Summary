# AbstractSequentialList

```java
public abstract class AbstractSequentialList<E> extends AbstractList<E>
```

* **"Sequential access" data store에 의해 backed되는 List 인터페이스의 구현의 노력을 덜어주는 skeletal 구현체를 제공한다.**
  * random access(such as array)는 AbstractList class를 우선적으로 사용해야 한다.
* **random access 메소드 (get, set, add, remove)를 구현하는 AbstractList와 반대되는 클래스**
* **List를 구현하기 위해서는 이 클래스를 상속하면서, listIterator, size 메소드의 구현만 제공하면 된다.**
  * 불면 리스트는 list Iterator의 hasNext, next, hasPrevious, previous, index 메소드만 구현하면 된다.
  * 가변 리스트는 list Iterator의 set 메소드를 추가구현하면 된다.
  * 가변크기 리스트는 list Iterator의 remove와 add 메소드를 추가 구현하면 된다.
* **일반적으로 void, collection 생성자를 제공해야 한다. **
  * Collection interface의 권장사항에 따라



## Methods

