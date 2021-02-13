# Data Binding 추상화

기술적인 관점: Property 값을 타겟 객체에 설정하는 기능

사용자 관점: 사용자 입력 값을 애플리케이션 도메인 모델에 동적으로 변환해 넣어주는 기능

**입력 값은 대부분 문자열인데, 그 값을 객체가 가지고 있는 ing, long, boolean, Date등 심지어 Event, Book같은 도메인 타입으로 변환해서 넣어주는 기능.**

## PropertyEditor

* Spring 3.0 이전까지 DataBinder가 변환 작업에 사용하던 인터페이스
* Thread-safe하지 않음,(상태 정보를 저장하고 있기 때문에 Bean으로 등록해서 사용하면 안됨(Thread scope로 사용한면 괜찮?))
* Object와 String 간의 변환만 가능
  * 좀 더 일반적인 경우의 Binding을 위해 Converter 인터페이스가 생김

## Converter

* S타입을 T타입으로 변환할 수 있는 매우 일반적인 변환기
* 상태 정보 없음 == stateless == Thread-safe
* `ConverterRegistry`에 등록해서 사용
  * 직접 쓸일은 거의 없음

```java
public class StringTokEventConverter implements Converter<String, Event> {
    @Override
    public Event convert(String source){
        Event event = new Event();
        event.setId(Integer.parseInt(source));
        return event;
    }
}
```

## Formatter

* PropertyEditor 대체제
* 얘도 Thread-safe
* Object와 String간의 변환을 담당
* 문자열을 Locale에 다라 다국화하는 기능도 제공(optional)
* FormatterRegistry에 등록해서 사용

```java
public class EventFormatter implements Formatter<Event> {
    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        Event event = new Event();
        int id = Integer.parseInt(text);
        event.setId(id);
        return event;
    }
    
    @Override
    public String print(Event object, Locale locale){
        return object.getId().toString();
    }
}
```

## ConversionService

* 실제 변환 작업은 이 인터페이스를 통해서 Thread-safe하게 사용할 수 있음.
* Spring MVC, Bean(value) 설정, SpEL에서 사용한다.
* DefaultFormattingConversionService
  * FormatterRegistry
  * ConversionService
  * 여러 기본 Converter와 Formatter를 등록해 줌.
* 웹 애플리케이션인 경우 DefaultFormattingConversionService를 상속하여 만든 **WebConversionService**를 빈으로 등록해 줌.
* Formatter와 Converter Bean을 찾아 자동으로 등록해 준다.
* 실제로 사용할 일은 없을 것 

