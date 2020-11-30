# Red-Black Tree

* 이진 탐색 트리의 일종

* 일반적인 이진 탐색 트리의 편향 문제를 해결한 트리

* 삽입, 삭제, 검색에 O(logN)

* 삽입되는 노드는 Red

* ## 정의

  * Root Node는 Black
  * 모든 leaf Node(, NIL)는 Black
  * Red의 자식은 모두 Black
  * 각 노드에서의 Black Height는 같다.

* ## 조건

  * Double-Red는 용납 못한다.
    * Restructuring O(1)
      * Uncle Node가 Black인 경우
      * 삽입한 노드의 부모와 조부모 노드를 Sort 후 중간 노드를 부모로 하고 Black, 나머지 둘을 자식 노드로하고 Red
      * 각 노드의 자식 노드들을 붙인다.
    * Recoloring O(1), Propagation 가능성
      * Uncle Node가 Red인 경우
      * 조부모 노드를 Red, 조부모 노드의 모든 자식 노드들을 Black으로
      * 조부모 노드가 Root이면 Black으로