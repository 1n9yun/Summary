# ApplicationEventPublisher

이벤트 프로그래밍에 필요한 인터페이스 제공, 옵저버 패턴 구현체

`ApplicationContext extends ApplicationEventPublisher` - `publishEvent(ApplcationEvent event)`

## 이벤트 만들기

* ApplicationEvent 상속받은 클래스 생성
* Spring 4.2부터는 클래스를 상속받지 않아도 사용 가능

## 이벤트 발생 방법

* `ApplicationEventPublisher.publishEvent();`

## 이벤트 처리 방법

* `ApplicationListener\<Event>`를 구현한 클래스를 만들고 Bean으로 등록
* Spring 4.2부터는 `@EventListener`를 사용해서 Bean의 메소드에 사용
* 기본적으로는 `Synchronized`
* 순서를 정하고 싶다면 `@Order` 사용
* 비동기적으로 사용하고 싶다면 `@Async`사용, 이 때 `@Order`는 의미가 없어진다.

## 기본 제공 이벤트

* `ContextRefreshedEvent`: `ApplicationContext`를 초기화했거나 리프레시 했을 때 발생
* `ContextStartedEvent`: `ApplicationContext`를 start()하여 라이프사이클 Bean들이 시작 신호를 받은 시점에 발생.
* `ContextStoppedEvent`: `ApplicationContext`를 stop()하여 라이프사이클 Bean들이 정지 신호를 받은 시점에 발생.
* `ContextClosedEvent`: `ApplicationContext`를 close()하여 Singleton Bean 소멸되는 시점에 발생.
* `RequestHandledEvent`: HTTP 요청을 처리했을 때 발생.