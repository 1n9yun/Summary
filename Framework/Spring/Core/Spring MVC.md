# Spring MVC

![MVC model](https://mdn.mozillademos.org/files/16042/model-view-controller-light-blue.png)

## MVC

### Model

어떤 데이터를 보여줄 지 정의 해준다.

Model은 '데이터'를 정의하는 부분으로, 데이터를 조회하는 것 뿐만 아니라 삽입, 변경, 삭제등의 작업을 수행하고 그 실제적인 과정이 일어나는 비즈니스 로직 전반적인 부분을 정의

### View

데이터의 보여주는 방식을 정의

View는 Client들이 실제로 보는 화면을 표시해주는 부분으로, 보여줄 데이터나 텍스트 데이터를 표시해주거나 Client들의 입력을 받기위한 입력 폼들을 정의해 줌

### Controller

Client의 Request에 따른 Response를 정의 해주며 모델 및 뷰를 업데이트하는 로직을 포함하고 있다.

Controller는 Client의 요청을 받아들여 각 요청에따라 데이터를 설정해주거나 화면을 출력해주는 등의 전체적인 연결을 관리해주는 응답을 정의해 줍니다.

### Process in Spring

#### DispatcherServlet

Spring MVC는 하나의 컨트롤러로 Client들의 Request를 처리해주는 패턴인 Front Controller Pattern으로 구성되어있다. 그 Front Controller가 DispatcherServlet이며 요청을 처리하기 위한공유 알고리즘을 제공하는데, 실제 작업은 적절한 컴포넌트들에게 위임하여 수행한다.

![DispatcherServlet](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/images/mvc-context-hierarchy.png)

##### Root Context

* ContextLoaderListener에서 생성
* Spring 최상단에 위치한 Context
* 공통적인 기능들을 가진 Bean을 생성하여 다른 Servlet Context에서 참조가 가능 (`@Service`, `@Repository`, `@Configuration`, `@Component`)

##### Servlet Context

* DispatcherServlet에서 생성
* Servlet 단위로 생성되는 Context
* 서블릿 내에서만 사용하는 Bean 생성(`@Controller`, Interceptor)

DispatcherServlet은 설정에 따라 여러개 생성이 가능하며 url parttern을 통해 목적에 맞게 나누어 관리한다. 예를 들면 웹 페이지를 위한 서블릿과 Rest기반 웹 서비스를 따로 관리하기위한 DispatcherServlet을 생성할 수 있고 생성한 DispatcherServlet 마다 Servlet Context가 생성됩니다.

Bean을 찾을 때 Servlet Context를 먼저 스캔 후 Root Context를 스캔하여 Bean을 찾게되며 Servlet Context에서는 Root Context의 Bean을 참조할 수 있지만 반대로는 불가능.

이런 이유로 참조가 불가능한 컨텍스트(Root Context > Servlet Context, Servlet Context > 달느 Servlet Context)에 설정된 Bean을 참조하려고 하면 `Not found bean` 에러가 날 수 있으므로 본인이 사용하고자 하는 Bean의 목적과 저장위치를 잘 알고 사용해야 한다.

---

#### Special Bean Type

DispatcherServlet은 특수한 요청을 처리하기 위한 Bean들이 있다. 그 중 위의 모식도에서 Servlet Context가 가지고 있는 ViewResolver와 HanlderMapping Bean의 역할에 대해서 알아본다.

##### HandlerMapping

요청에 따른 전 후처리를 위한 interceptor 핸들링을 위한 Bean으로써 RequestMappingHandlerMapping(@RequestMapping), SimpleUrlHandlerMapping(URI path patterns handler)가 포함되어 있다.위 기능을 통해 Controller에서 URI 매핑이 가능

##### ViewResolver

String기반 view 이름 검색을 통해 파일을 찾아 DispatcherServlet에 전달해주는 기능

---

#### DispatcherServlet Request 처리과정

![request process](https://taes-k.github.io/images//posts/trick_basic/2019-05-15-about-spring-mvc/3.png)