# IoC - Inversion Of Control

> https://develogs.tistory.com/19

* Dependency와 Dependency Inversion, Dependency Injection 개념



## Dependency

![Dependency](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbSHQ5T%2Fbtqz8YGPMTD%2FLep8mGYmFR678izmDODldK%2Fimg.png)

* Client A는 Service B를 의존
* Class의 관계에서 Service는 특정 기능을 API를 통해 제공해주는 Class
* Client는 그 Service를 이용하는 Class
* 의존한다 = A가 B를 멤버 변수나 로컬 변수로 가지고 있거나 파라미터로 전달되거나 B의 메소드를 호출하는 것들을 의미함
* B가 변경되면 A는 B를 강하게 Dependency하고 있으므로 예상치 못한 동작을 하는 등 영향을 받게 됨
* 이는 A를 재사용하기 어렵게 만들기 때문에 A는 Component/Service가 될 수 없다.
* 여기서 Component는 다른 프로젝트에서도 바로 재사용이 가능한 수준의 모듈
* 재사용하기 위해서는 A에서 B를 사용하는 부분을 수정해야 함

![관리되지 않은 Dependency](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcLBLHm%2Fbtqz7omD3Ej%2FwaJzKwjUoVSj2XcBI0pqy1%2Fimg.png)

* 더 큰 문제는 우리 프로젝트에서 대부분의 Class들의 의존 관계가 위와 같다는 것
* Leaf에 존재하는 Class말고는 재사용할 수 있는 코드가 전혀 없다는 뜻



## Dependency Inversion Principle(DIP)

> 1. 고차원 모듈은 저차원 모듈에 의존하면 안된다.
>
> 2. 이 모듈 모두 다른 추상화된 것에 의존해야 한다.
>
> 3. 추상화 된 것은 구체적인 것에 의존하면 안된다.
>
> 4. 구체적인 것이 추상화된 것에 의존해야 한다.
>
> * Martin, Robert C.

* 첫 번째 그림에서 DIP를 적용하면 아래와 같은 순서로 적용된다.

![3](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FQfg65%2Fbtqz6WxbFcR%2FouTK87HP6sEvWZgRUKJ7o1%2Fimg.png)

* (1) A가 B를 바라보는 Dependency를 제거한다.

![4](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F2FV9A%2FbtqAcczBSPk%2FkM2gphKnBPYKP710jiCWCK%2Fimg.png)

* (2), (3) A는 Abstract를 Reference하지만 Abstract는 B를 Dependency하면 안된다.

![5](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FTMAIz%2FbtqAbmJdmLq%2F8UkEkmPwJlI9wvYdnSLRvK%2Fimg.png)

* (4) B가 Abstract를 Inherit하도록 하여 Dependency를 Inversion한다.

---

![example](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmaVkd%2FbtqAa7ZOG5l%2FxXdZ66mMdA41LfandY5SRk%2Fimg.png)

* Dependency가 정리되지 않아 의존성 부패가 발생한 Application Architecture의 모습
* Class들이 전부 스파게티처럼 엮여있어 모두 재사용 불가능
* 가독성도 떨어지며 유지 보수성도 좋지 않다.
* Dependency가 매우 강하기 때문에 각 클래스들은 Mock으로 대체될 수 없다 Unit Test도 불가능한 코드가 대부분
* 점점 악화될 것

![DIP 적용](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FAW8WX%2FbtqAfS0wQ77%2FvC1ZTw3aY1XHs8OCKGAINK%2Fimg.png)

* DIP가 적용되어 의존성이 잘 정리된 Application Architecture

* ``` swift
  class SwitchButton { 
      let lamp: SwitchButtonInterface = Lamp()
      ... 
  }
  ```

  위와 같이 Concrete Class인 Lamp를 직접 생성하여 아직 Class Dependency가 남아있을 수 있다.

* Factory Pattern을 적용하여 Class Dependency를 제거

* ```swift
  class SwitchButton { 
      let lamp: SwitchButtonInterface = Factory.getObject() 
      ... 
  }
  ```

* 하지만 이제 Lamp대신 Factory를 강하게 Dependency하고 있다.

* 다른 프로젝트에서 재사용 하려면 Factory를 수정해서 적용해야 한다.

* 아직도 Component가 되지 못한다.

* 이런식으로하면 모든 클래스들이 각각 자신만의 Factory를 갖게 될지도 모름



## DI (Dependency Injection)

* 의존성 주입

* ```c
  int main(int argc, char* argv[]){}
  ```

  C언어의 Entry Point인 main함수, argument로 argc와 argv를 주입받고 있다.

* 외부로부터 전달받는 것을 의존성 주입이라고 한다.

* ```swift
  class SwitchButton {
      let switchHandler: SwitchButtonInterface
      init(switchHandler: SwitchButtonInterface) {
          self.switchHandler = switchHandler
      } 
  }
  ```

* Constructor Injection을 적용한 예시

* DI는 크게 Constructor / Interface / Method Injection로 사용되지만 좀 더 명확한 Constructor Injection을 선호한다.

* 이제 재사용 가능한 Component가 되었다.



## Inversion Of Control (IoC)

* ```swift
  main() {
      let button = SwitchButton(switchHandler: Lamp())
  }
  ```

* Client가 Lamp를 생성해 SwitchButton에 주입

* SwitchButton은 Lamp를 모르게 됐지만 Client가 Lamp를 생성하고 SwitchButton과의 관계를 설정하고있는 오히려 더 이상해졌다.

* Client는 Lamp를 알 이유도 없으며 알아서도 안되는데..



![일반적인 제어 방향](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdzl7ib%2FbtqAhVdoch5%2FNBmMY1kz168Gv8npVZITyk%2Fimg.png)

* IoC가 적용되지 않은 일반적인 프로그램의 흐름
* Entry Point에서 다음에 사용할 Obejct를 결정하고, 생성하고, 생성된 Object의 메서드를 호출
* 그 Object 메서드 안에서는 또다시 다음에 사용할 것을 결정하고 호출하는 식의 작업이 반복
* 즉 Service를 사용하는 Client쪽에서 모든걸 제어하고 있는 구조
* 제어의 역전(IoC)란 이러한 제어의 흐름을 Inversion하는 것을 의미

![Inversion](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb7Zs3t%2FbtqAi9IMxW9%2FZhQHt5MD86gwFWvzzKawQ1%2Fimg.png)

* 역전된 제어의 흐름
* Entry Point에서 IoC Container에게 모든 관계 설정에 대한 책임을 위임
* 컴파일 타임의 static한 class Dependency가 런타임의 dynamic한 Object dependency로 변경됨



![IoC 적용](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbCCWkv%2FbtqAjNkWkqX%2Fzkp2Hl3ey1duXVyV4MC7v1%2Fimg.png)

* Client가 IoC Container에게 필요한 Object를 요청
* IoC Container는 SwitchButton이 필요한 Object를 생성하고 관계를 wiring하여 전달한다.
* 각 Class들이 다른 class에 대한 dependency가 모두 사라졌으니 모든 Class들은 Component가 될 수 있다.
* 분리된 모든 Class들은 전부 Mock으로 대체될 수 있어 Testability도 높아졌다
* IoC만 바뀌면 dynamic하게 전혀 다른 동작을 하는 프로그램이 될 수도 있다.

