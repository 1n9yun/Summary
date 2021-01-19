# AbstractCollection

```java
public abstract class AbstractCollection<E> extends Object implements Collection<E>
```

* **Collection 인터페이스의 skeletal 구현체를 제공**
  * 인터페이스를 구현하는 수고를 덜기 위해
* **불변 collection을 구현하기 위해서는 이 클래스를 상속받고 iterator와 size 메소드의 구현만 필요하다.**
  * iterator 메소드는 hasNext, next의 구현만

* **가변 collection을 구현하기 위해서는 추가적으로 이 클래스의 add 메소드를 override 해야 한다.**
  * iterator 메소드는 remove 메소드를 추가 구현
* **일반적으로 void, Collection 생성자는 제공해야 한다.**
  * Collection 인터페이스의 권장 사항에 따라



## Methods

