# Validation

`org.springframework.validation.Validator`

애플리케이션에서 사용하는 객체 검증용 인터페이스.

* 어떤 계층과도 관계가 없어 모든 계층(웹, 서비스, 데이터)에서 사용해도 괜찮다.
* 구현체 중 하나로, JSR-303(Bean Validation 1.0 ...), JSR-349을 지원한다.
* DataBinder에 들어가 바인딩할 때 같이 사용되기도 한다.

`public class EventValidator implements Validator`

* boolean supports(Class clazz): 어떤 타입의 객체를 검증할 때 사용할 것인지 결정
* void validate(Object obj, Errors e): 실제 검증 로직을 구현
  * ValidationUtils를 사용하면 편리함



Springboot 2.0.5 이상 버전 사용 시 `LocalValidatorFactoryBean`을 자동 등록

Validation Annotation 사용, 복잡한 로직이 필요하면 Validator 구현



## Example

```java
@Autowired
EventValidator eventValidator;

@PostMapping
public ResponseEntity createTodo(@RequestBody @Valid TodoDto todoDto,
                                 Errors errors
                                ){
    if(errors.hasErrors()) return badRequest(errors);
    eventValidator.validate(todoDto, errors);
    if(errors.hasErrors()) return badRequest(errors);
    
    // ...
}
```

