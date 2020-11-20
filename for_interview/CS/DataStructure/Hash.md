## Hash

* ### HashTable

  * HashFunction에 의해 Key의 고유 인덱스를 생성, 각 인덱스에 Value를 저장

  * #### Collision 발생 시

    * Open Addressing
      * 재해싱 결과 인덱스 또는 다음 인덱스에 저장 등 다른 위치를 찾아 저장하는 방법
    * Seperate Chaining
      * Linked - 충돌 인덱스에 리스트 형태로 이어 붙이는 것
      * Tree - 충돌 인덱스에 트리 형태로 저장하는 것, Red-Black Tree
      * Key-Value 쌍이 적을 경우 Linked가 효율적일 수 있음.
      * Tree는 메모리를 많이 차지함

