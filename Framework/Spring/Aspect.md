# Spring Aspect

## AOP (Aspect Oriented Programming) ?

공식 문서 중, 코어 기술의 AOP 부분을 보면,

* AOP란 OOP를 보완하는 구조
* OOP에서는 클래스 단위로 모듈화를 했다면 AOP에서는 '관점'으로 모듈화를 하는 구조
* '관점'은 type과 object와는 무관하게 '관심사항'으로 모듈화를 가능하게 한다.
* 스프링에서 AOP는 스프링 IoC를 보완하며 뛰어난 미들웨어 솔루션을 제공한다.

AOP는 클래스와 관계없이 여러 객체를 관통하는 '공통 관심 사항'들에 따라서 묶어서 관리해줄 수 있는 구조

결국 반복적인 코드들을 사용 목적(공통 관심 사항)에 따라서 공통된 통합 위임 코드를 작성하여 운영 및 관리할 수 있게끔 하려는데에 목적이 있다.

## 개념과 용어

| 용어          | 설명                                                         |
| ------------- | ------------------------------------------------------------ |
| Aspect        | 여러 객체를 관통하는 '공통 관심 사항'을 구현한 것을 의미한다. <br />Spring 에서는 설정을 통해 일반 클래스에 넣는 방식(schema-based approach) 혹은 어노테이션을 활용한 방식으로 클래스에 aspect를 줄 수 있다. |
| Join point    | 특정 작업이 시작되는 시점을 나타내는 포인트로, 메서드 호출이나 예외발생 등의 시점들을 의미한다. |
| Advice        | 특정 join point에서 실행되는 action.<br />실행 시점에 따라 'around', 'before', 'after'의 타입들을 가지고 있다. |
| Pointcut      | join point의 부분집합으로써 실제 Advice가 실행되는 join point들의 집합을 의미한다. |
| Target object | advice가 적용되어질 타겟 객체를 의미                         |
| AOP proxy     | Aspect를 구현하기 위해 AOP 프레임워크에서 만들어낸 객체를 의미한다. |
| Introduction  |                                                              |
| weaving       |                                                              |

