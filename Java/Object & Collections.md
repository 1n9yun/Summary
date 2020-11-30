# Object & Collections

## Equals, HashCode

* ### Object Class

  * Equals는 Primitive Type의 경우 단순 값 비교, Reference Type의 경우 주소를 비교한다.
  * HashCode는 주소를 Hashing 한다.

* ### Java에서의 HashCode 규약

  * Equals가 True면 HashCode도 같아야 함.
  * Equals가 False라도 HashCode가 같아도 됨.

* ### Equals와 HashCode Override

  * 같은 값을 가진 객체라도 서로 다른 HashCode를 가질 수 있기 때문에 Hash를 사용하는 Collection에서 의도대로 동작하지 않을 수 있다. 
  * 따라서 내용이 같은지 확인하도록 Equals를 Override 했더라도 HashCode도 함께 Override 하는 것이 좋다.
  * Hash Collections는 HashCode를 먼저 비교 후 Equals를 비교한다.

## StringBuilder, StringBuffer

* ### String

  * 내부에 charSquence가 final로 선언되어 있기 때문에 수정 불가능
  * \+ 연산 시 새로운 String 객체를 만들어 복사한다.
  * 연산에 사용된 두 String은 Garbage가 된다.

* ### StringBuilder

  * 버퍼의 남은 공간이 충분하다면 그대로 값을 복사해서 넣어줌
  * 부족하다면 버퍼의 크기를 두배로 늘리고 복사
  * Thread-Safe, Syncronize를 지원하지 않아 Buffer보다 빠르다.
  * 싱글 스레드 환경이나 멀티 스레드를 신경쓰지 않아도 되는 경우 사용

* ### StringBuffer

  * Builder와 같지만 Syncronize 지원과 Thread-Safe하다는 점이 다르다.

## LinkedHashMap, TreeMap

* ### LinkedHashMap

  * HashMap과 같은 동작
  * 내부에 Key-Value 쌍이 입력된 순서대로 저장되는 LinkedList를 가지고 있음

* ### TreeMap

  * Key를 기준으로 Red-Black 트리로 구현되어 있음
  * Red-Black 트리는 이진 탐색 트리의 일종으로 중위 순회 시 정렬된 순서로 순회하여 정렬을 유지할 수 있음

