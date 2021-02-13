# Spring Expression Language

## 구성

```java
ExpressionParser parser = new SpelExpressionParser();
StandardEvaluationContext context = new StandardEvaluationContext(bean);
Expression expression = parser.parseExpression("SpEL 표현식");
String value = expression.getValue(context, String.class);
```



## 문법

```java
@Value("#{1 + 1}")
int value;

@Value("#{'hello ' + 'world'}")
String greeting;

@Value("#{1 eq 1}")
boolean trueOrFalse;

@Value("hello")
String hello;

@Value("${my.value}")	// application.properties > my.value=1;
int myValue;

@Value("#{${my.value} eq 100}")
boolean isMyValue100;

// and, or, not, gt, 등 .... 있음 레퍼런스 참고
```

### Bean 참조

```java
@Component
public class Sample{
    int data = 100;
}

// ---

@Value("#{sample.data}")
int sampleData;
```



## 어디서 쓰나?

* @Value 애노테이션
* @ConditionalOnExpression 애노테이션
* Spring Security
  * 메소드 시큐리티, @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter
  * XML 인터셉터 URL 설정
  * ...
* Spring Data
  * @Query 애노테이션
* Thymeleaf
* ...



