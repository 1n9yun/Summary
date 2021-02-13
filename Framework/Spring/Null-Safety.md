# Null-Safety

Spring framework 5에 추가된 Null 관련 annotation

* @NonNull
* @Nullable
* @NonNullApi(패키지 레벨)
* @NonNullFields(패키지 레벨)

## 목적

(tool의 지원을 받아)컴파일 시점에 최대한 npe를 방지하는 것

## 사용

**IntelliJ - Preferences - Compiler option - Add runtime assertions for notnull-annotated methods and parameters - Configure annotations**에 Spring 관련 annotation들 추가해줘야 함

```java
@NonNullApi
package ~~;

public class StringService{
    @NonNull
    public String createString(@NonNull String name){
        return "hello " + name;
    }
}
```

Spring Data, Reactor에 사용되고 있다