# Index

* DB

* Clustered Index, non-Clustered Index

* ### Clustered Index

  * PK
  * 순서대로
  * 한 테이블에 하나
  * 범위 검색에 좋다
  * 존재하는 PK 사이에 INSERT할 경우 한칸씩 다 밀고 해야해
  * 왜 Auto_Increment인지 알 수 있다.
  * ...

* ### non-Clustered Index

* ### Example

  * email을 PK로 가져갈 경우 DB 성능에 이슈
  * PK는 Clustered index기 때문에 

* ### Advanced

  * explain 으로 MySQL이 어떻게 이 쿼리를 처리할 지 설명해주면 그걸 보고 튜닝해볼 수 있음
  *  B-Tree, Page in InnoDB
  * Cardinality
  * 복합키
  * innodb_buffer_pool_size
  * log_throttle_queries_not_using_indexes