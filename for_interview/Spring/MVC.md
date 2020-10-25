# Spring MVC Architecture

## Request LifeCycle

![request lifecycle](https://media.vlpt.us/images/damiano1027/post/94f7d7a6-f4b7-4c22-97f4-35e6791b8963/image.png)

* (1) Filter

  * Web Application의 전역적인 로직을 담당
  * 전체적인 필터링(설정)
  * DispatcherServlet에 들어가기 전 Web Application 단에서 실행

* (2) DispatcherServlet

  * 모든 Request를 우선적으로 받아 처리해주는 서블릿
  * HandlerMapping에게 Request에 대해 매핑할 Controller 검색 요청
  * HandlerMapping으로부터 Controller 정보를 반환받아 해당 Controller와 매핑
  * 말 그대로 Request에 대해 어떤 컨트롤러로 매핑시킬 것인지 배치하는 역할

* (3) HandlerMapping

  * 검색을 요청받은 Controller를 찾아 정보를 리턴

* (4) HandlerInterceptor

  * Request가 Controller에 매핑되기 전 앞단에서 부가적인 로직을 끼워넣는다.
  * 주로 세션, 쿠키, 권한 인증 로직에 많이 사용된다.

* (5) Controller

  * Request에 대해 어떤 로직(Service)로 처리할 것인지 결정, 그에맞는 Service를 호출
  * Service Bean을 스프링 컨테이너로부터 주입받아야 한다. Service Bean의 메소드를 호출해야 하기 때문

* (6) Service

  * 데이터 처리 및 가공을 위한 비즈니스 로직을 수행
  * Repository를 통해 DB에 접근하여 데이터의 CRUD를 처리하기도 한다.

  * Repository
    * DB에 접근하는 객체. DAO(Data Access Object)라고 부른다.

* (9) ViewResolver

  * Controller에서 리턴한 View의 이름을 DispatcherServlet으로부터 넘겨받고, 해당 View를 렌더링한다.
  * Configuration에서 Prefix, Infix로 /META-INF/views/*.jsp 로 만들어주는 것도 이녀석
  * DispatcherServlet에서는 해당 View화면을 Response한다.