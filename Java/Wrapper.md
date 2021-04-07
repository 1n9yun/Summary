# Wrapper Class

Primitive Type(byte, int, boolean, long, char, short, float, double, void)를 Object 타입으로 wrapping한 것

제네릭 등에 사용된다.

## Boxing, UnBoxing

int를 Integer에 넣는 것

Integer에서 int를 꺼내는 것

### Auto

sicne 1.5,

```java
// Auto Boxing
Integer myInt = 10; // new Integer(10);

// Auto Unboxing
int myNum = myInt; // myint.intValue();
```

이런 기능 덕분에 신경쓰지 않고 산술 연산 등을 그대로 사용할 수 있다.

## 장단

항상 Wrapper 클래스를 사용하면 안되나?

안된다. 객체 생성 비용, 참조하는 비용 등.. 기본형을 사용해도 된다면 기본형을 사용하는 것이 좋다.



