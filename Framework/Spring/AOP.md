# AOP

Aspected-oriented Programming(AOP)는 OOP를 보완하는 수단으로, 흩어진 Aspect를 모듈화할 수 있는 프로그래밍 기법.

흩어진 관심사(Crosscutting Concerns) // concerns: 여러 클래스에 흩어져있는 비슷한 코드들

## 주요 개념

* Aspect: 관심사들을 모듈화한 것
* Target: 적용이 되는 대상
* Advice: 모듈에 들어간 것, 해야할 일들
* Join Point: 메서드 실행 시점
* Point cut: 모듈에 들어간 것, 어디에 적용해야 하는지, Join Point의 구체적인 subset

## AOP 구현체

* Java
  * AspectJ
  * Spring AOP

## AOP 적용 방법

모듈화한 Aspect들을 적용하는 방법

* 컴파일
  * 별도의 컴파일이 한번 더 필요함
  * 런타임 중의 부하가 없음
* 로딩 타임 - 로드 타임 위빙?
  * 로드 시점에 바이트 코드를 끼워 넣음
  * 로드 타임 위버를 설정해줘야 함
  * 로드 타임 성능 부하
  * AspectJ를 사용할 수 있으므로 다양한 방식 ( 다양한 Join Point )
* 런타임 - Spring AOP가 사용하는 방법
  * A Bean을 감싼 Proxy Bean을 생성
  * 위에서 한거 다 필요없음 깔끔 간단 쉬움



## Proxy 기반 AOP (. Spring AOP)

* Spring Bean에만 AOP 적용 가능
* 모든 AOP 기능을 제공하는 것이 목적이 아니라, Spring IoC와 연동하여 엔터프라이즈 어플리케이션에서 가장 흔한 문제에 대한 해결책을 제공하는 것이 목적.

### Proxy Pattern

* 기존 코드의 변경 없이 접근 제어 또는 부가 기능의 추가를 위해 사용
* 기존 코드를 건드리지 않고 성능 측정

```java
// EventService.java
public interface EventService {
    void createEvent();
    void publishEvent();
    void deleteEvent();
}

// SimpleEventService.java
@Service
public class SimpleEventService implements EventService {
    @Override
    public void createEvent(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Created an Event");
    }
    
    @Override
    public void publishEvent(){
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Published an event");
    }
    
    @Override
    public void deleteEvent(){
        System.out.println("Deleted an event");
    }
}

// AppRunner.java
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    EventService eventService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createEvent();
        eventService.publishEvent();
    }
}
```

```java
// Real Subject와 같은 interface로 구현
// ProxySimpleEventService.java
@Primary
@Autowired
public class ProxySimpleEventService implements EventService{
    
    @Autowired
    SimpleEventService simpleEventService;
    // or EventService simpleEventService;   이름에 기반
    
    @Override
    public void createEvent() {
        long start = System.currentTimeMillis();
        simpleEventService.createEvent();
        System.out.println(System.currentTimeMillis() - begin);
    }
    @Override
    public void publishEvent() {
        long start = System.currentTimeMillis();
        simpleEventService.publishEvent();
        System.out.println(System.currentTimeMillis() - begin);
    }
    @Override
    public void deleteEvent() {
        System.out.println(System.currentTimeMillis() - begin);
    }
}
```

원하는 대로 원래 클래스를 수정하지 않고 만들었으나, Proxy class를 만드는데 들어간 수고와 중복 코드가 있고 다른 클래스에도 이 프록시가 필요하다면?

* Dynamic Proxy (Runtime에 Proxy를 동적으로 생성)
  * 이 방법을 기반으로 Spring IoC와 사용하여 해결 => Spring AOP
    * 기존 Bean을 대체하는 동적 Proxy Bean을 만들어 등록
    * `AbstractAutoProxyCreator implements BeanPostProcessor`

## Spring AOP

Spring annotation 기반 AOP

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

* `@Aspect`
* Bean으로 등록해야 하므로 `@Component` 추가

```java
// PerfAspect.java
@Component
@Aspect
public class PerfAspect{
    // 여기서 JoinPoint는 각 메소드 자체
    // 메서드를 감싸는 형태
    // PointCut 표현식
    @Around("execution(* me.1n9yun..*.EventService.*(..))") // or
    @Around("@annotation(PerfLogging)") // 기존 코드의 약간의 변경이 필요하지만 매우 편리
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }
}
```

포인트 컷 정의

* @Pointcut(표현식)
* 주요 표현식
  * execution
  * @annotation
  * bean
* 포인트컷 조합
  * &&, ||, !

```java
// 기본 값이 CLASS, CLASS 이상으로 줄 것
// 컴파일 한 class 파일에도 annotation 정보를 포함하고 있음
@Retention(RetentionPolicy.CLASS) // 명시
@Documented // Optional
@Target(ElementType.METHOD) // Optional
public @interface PerfLogging {
    
}
```

Advice 정의

* @Before
* @AfterReturning
* @AfterThrowing
* @Around