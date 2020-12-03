# AbstractList

* **"Random access" data store에 의해 backed되는 List 인터페이스의 구현의 노력을 덜어주는 skeletal 구현체를 제공한다.**
  * sequential access(such as linked list)는 AbstractSequentialList class를 우선적으로 사용해야 한다.
* **불변 List의 구현을 위해서는 이 클래스를 상속하고 get(int), size() 메소드의 구현만을 제공해야 한다.**
* **가변 List의 구현을 위해서는 추가적으로 set(int, E) 메소드를 override해야 한다.**
  * 그렇지 않은 경우 UnsupportedOperationException을 throw해야 한다.
  * List가 가변 size인 경우 추가적으로 add(int, E), remove(int) 메소드를 구현해야 한다.
* **일반적으로 void, collection 생성자를 제공해야 한다. **
  * Collection interface의 권장사항에 따라
* **다른 추상 collection 구현체와 다르게 iterator 구현을 제공할 필요는 없다.**
  * iterator와 listIterator는 이 클래스에 의해 구현된다.
    * "Random access" 메소드인 get, set, add, remove 위에서



## Methods

