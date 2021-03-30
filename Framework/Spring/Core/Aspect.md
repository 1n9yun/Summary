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
| Introduction  | proxy 객체에 메소드나 필드를 추가한 것을 의미                |
| weaving       | Aspect를 Target object에 적용하는 것. 컴파일 시, 로드 타임, 런타임 시 적용할 수 있음.<br />Spring AOP는 런타임에 적용 |

## Mechanisms

스프링 AOP는 Proxy 기반으로 작동한다. (Proxy Factory 패턴)

...

## 적용

다음 예제는 성능을 진단하는 프로파일링을 위해 시간을 체크하는 Aspect 예제

```java
@Aspect
public class ProfilingAspect {

    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

    @Pointcut("execution(public * foo..*.*(..))")
    public void methodsToBeProfiled(){}
}
```

pointcut과 advice를 선언하여 aspect를 구성했다. pointcut에서 사용한 execution 명시자의 경우 표현식은 다음과 같다. `execution(수식어패턴? 래턴타입패턴 패키지패턴? 이름패턴(파라미터패턴))`

따라서 위예제의 경우 foo 패키지 내 모든 메서드들에 대해 profile advice가 동작하여 시간을 체크하게 된다.

### 실제 프로젝트에서의 사용

#### 로깅

```java
@Aspect
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(MessageAdvice.class);
  @Before("methodsToBeLogging()")
  public Object profile(ProceedingJoinPoint jp) throws Throwable {
      logger.info("메서드 :"+jp.getSignature().getName());
      logger.info("매개변수 :"+Arrays.toString(jp.getArgs());
  }

  @Pointcut("execution(public * foo..*.*(..))")
  public void methodsToBeLogging(){}
}
```

#### 에러처리

```java
@Aspect
public class ErrorHandlingAspect {

  @Around("methodsToBeErrorHandling()")
  public Object profile(ProceedingJoinPoint jp) throws Throwable {
      try {
          result = jp.proceed();
      } catch (Throwable e) {
      	logger.error("[" + jp.toString() + "]*" + e);
          //errorHandling ...
      }
        
  }

  @Pointcut("execution(public * foo..*.*(..))")
  public void methodsToBeErrorHandling(){}
}
```

