# Filter, Interceptor, AOP

* Filter는 DispatcherServlet으로 가기 전 필터링 로직을 수행
* Interceptor는 Controller와 매핑되기 전 로직을 수행
* 위 두가지는 실행 흐름에 간섭하는 역할. AOP도 그렇다
* 셋은 실행 시점이 다르다.
* ![실행시점](https://media.vlpt.us/images/damiano1027/post/33de7368-c96f-4f1d-bb33-f70c33af6793/image.png)

* #### Filter

  * DispatcherServlet에 들어가기 전 WebApplication단에서 실행
  * WebApplication 단의 기능만 사용 가능
  * 주로 인코딩 변환 처리, XSS방어 등의 요청에 대한 처리로 사용

* #### Interceptor

  * Spring Application단에서 실행
  * AOP 적용 이전 시점에서 실행
  * Spring framework 요소들에 접근이 가능하고 기능을 사용할 수 있다
  * 로그인체크, 권한 체크, 실행시간 계산, 작업 로그확인 등 업무처리에 많이 사용된다.

* #### AOP

  * Spring Application단에서 실행 Interceptor 적용 이후에 실행
  * Spring framework 요소들에 접근이 가능하고 기능을 사용할 수 있다
  * OOP를 보완하기위해 나온 개념
  * 객체 지향 프로그래밍을 했을 때 줄일 수 없는 중복을 줄이기 위해 관점에서 바라보고 처리한다.
  * 주로 로깅, 트랜잭션, 에러 처리 등 비즈니스단의 메서드에서 조금 더 세밀하게 조정하고 싶을 때 사용
  * Interceptor와 Filter는 주소로 대상을 구분해서 걸러내야하는 반면, AOP는 주소 파라미터, 애노테이션 등 다양한 방법으로 대상 지정
  
  * 포인트 컷
  
    * @Before: 대상 메서드의 수행 전
  
      @After: 대상 메서드의 수행 후
  
      @After-returning: 대상 메서드의 정상적인 수행 후
  
      @After-throwing: 예외발생 후
  
      @Around: 대상 메서드의 수행 전/후
  
    

---

## ViewResolver

* 스프링 프로젝트에서 View도 개발하거나 건드리기 위해 사용하는 것
* Controller에서 Model 객체에 값을 담고 jsp에서는 해당 값을 참조하는 방식으로 작동
* RESTful 서버와 같이 API만 만들고 싶다면 ViewResolver는 필요 없다.

