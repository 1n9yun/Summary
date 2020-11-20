# Transaction

> https://suhwan.dev/2019/06/09/transaction-isolation-level-and-lock/

* 데이터베이스의 데이터를 조작하는 작업의 단위
* ACID 원칙 보장
  * Atomicity
    * 작업이 부분적으로 성공하는 일이 없도록 보장하는 성질
  * Consistency
    * transaction이 끝날 때 DB의 여러 제약 조건에 맞는 상태를 보장하는 성질
  * Isolation
    * transaction이 진행되는 중간 상태의 ㄷ이터를 다른 transaction이 볼 수 없도록 보장하는 성질
    * lock
  * Durability
    * transaction이 성공했을 경우 해당 결과가 영구적으로 적용됨을 보장하는 성질
* But ACID 원칙은 종종 지켜지지 않는다
  * strict하게 지키려면 동시성이 매우 떨어지기 때문
  * 그래서 db엔진은 ACID 원칙을 희생하여 동시성을 얻을 수 있는 방법을 제공한다.
  * 바로 transaction의 isolation level



## Isolation Level

> https://joont92.github.io/db/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B2%A9%EB%A6%AC-%EC%88%98%EC%A4%80-isolation-level/

* 각 level에 따라 서로 다른 locking 전략을 취한다.
* ex) isolation level이 높아질수록 더 많이 더 빡빡하게 lock을 거는 것
* 각 level을 언제 사용해야 하는지, 위험성은 무엇인지 알기 위해서는 각 locking 전략을 파악해야 함

### InnoDB의 lock

* InnoDB는 transaction의 ACID 원칙과 동시성을 최대한 보장하기 위해 다양한 종류의 lock을 사용한다.

* 그 중에서 transaction isolation level을 이해하는 데에 필요한 내용만을 소개

* #### Row-level lock

* #### Record lock

* #### Gap lock

* #### Lock이 해제되는 타이밍



### READ UNCOMMITTED

### READ COMITTED

### REPEATABLE READ

### SERIALIZABLE

