# Spring Security

> http://www.nextree.co.kr/p1886/



###  Until Spring Comes Security FilterChain

> https://jeong-pro.tistory.com/205

![jwt_auth_filter](..\..\img\jwt_auth_filter.png)



1. 클라이언트로부터의 요청이 오면 AppicationFilter(정의한 필터들) 객체들로 가게 된다.

2. 필터들을 거치다가 DelegatingFilterProxyRegistrationBean 필터를 만남

3. 이 필터는 DelegatingFilterProxy라는 클래스로 만들어진 스프링 Bean을 등록시켜주는 역할

   > 이 클래스는 Spring security가 모든 애플리케이션 요청을 감싸게 해서 모든 요청에 보안이 적용되게 하는 서블릿 필터 (Spring Framework에서 제공)
   
4. DelegatingFilterProxy 클래스인 **springSecurityFilterChain**이라는 이름의 스프링 빈을 등록하고 이후에는 이 DelegatingFilterProxy(springSecurityFilterChain)가 필터로 동작

   >
   >구체적으로, DelegatingFilterProxy가 처리를 위임하는 필터 클래스는 FilterChainProxy다. 이 클래스 내부에 체인으로 등록된 필터를 순서대로 수행
   >
   >(DelegatingFilterProxy → FilterChainProxy → List 구조)
   >
   >> → 앞에서 스프링 시큐리티가 만든 DelegatingFilterProxy라고 했는데 스프링 부트를 기준으로 설명해서 그렇고 그냥 스프링에서는 직접 web.xml파일에 필터를 등록을 한다.
   
5. springSecurityFilterChain은 스프링에서 보안과 관련된 여러 필터 리스트를 갖고 있는 객체로 필터 리스트를 순회하면서 필터링을 실시



### 보안의 핵심 - 인증과 권한 부여

* 인증
  * 사용자가 본인이 맞는지 확인하는 절차
  * 방식
    * 크리덴셜 기반 인증 : 사용자명과 비밀번호를 이용한 방식
    * 이중인증 : ATM을 이용할 때처럼 물리적인 카드와 사용자가 입력한 개인정보를 조합하는 방식
    * 하드웨어 인증 : 자동차 키와 같은 방식
* 권한 부여



### 필터

* ServletRequest 필터를 사용한 방식을 이용

* 필터들이 어플리케이션에 대한 모든 요청을 감싸서 처리

* 스프링 시큐리티에서 여러 개의 필터들은 아래 그림과 같이 체인 형태를 이루면서 동작

* 자동 설정 옵션을 사용하면 10개의 스프링 시큐리티 필터가 자동으로 설정됨

* **DelegatingFilterProxy** 

  * 스프링 시큐리티가 모든 어플리케이션 요청을 감싸게 해서 모든 요청에 보안이 적용되게 하는 서블릿 필터 (프레임워크에서 제공)

  * ``` xml
    web.xml에 다음과 같은 설정을 해주면 스프링 시큐리티가 요청을 감쌈
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```
    
  * | 필터                                 | 설명                                                         |
    | ------------------------------------ | ------------------------------------------------------------ |
    | SecurityContextPersistenceFilter     | SecurityContextRepository에서 SecurityContext를 로드하고 저장하는 일을 담당함 |
    | LogoutFilter                         | 로그아웃 URL로 지정된 가상URL에 대한 요청을 감시하고 매칭되는 요청이 있으면 사용자를 로그아웃시킴 |
    | UsernamePasswordAuthenticationFilter | 사용자명과 비밀번호로 이뤄진 폼기반 인증에 사용하는 가상 URL요청을 감시하고 요청이 있으면 사용자의 인증을 진행함 |
    | DefaultLoginPageGeneratingFilter     | 폼기반 또는 OpenID 기반 인증에 사용하는 가상URL에 대한 요청을 감시하고 로그인 폼 기능을 수행하는데 필요한 HTML을 생성함 |
    | BasicAuthenticationFilter            | HTTP 기본 인증 헤더를 감시하고 이를 처리함                   |
    | RequestCacheAwareFilter              | 로그인 성공 이후 인증 요청에 의해 가로채어진 사용자의 원래 요청을 재구성하는데 사용됨 SecurityContextHolderAwareRequestFilter HttpServletRequest를 HttpServletRequestWrapper를 상속하는 하위 클래스(SecurityContextHolderAwareRequestWrapper)로 감싸서 필터 체인상 하단에 위치한 요청 프로세서에 추가 컨텍스트를 제공함 |
    | AnonymousAuthenticationFilter        | 이 필터가 호출되는 시점까지 사용자가 아직 인증을 받지 못했다면 요청 관련 인증 토큰에서 사용자가 익명 사용자로 나타나게 됨 |
    | SessionManagementFilter              | 인증된 주체를 바탕으로 세션 트래킹을 처리해 단일 주체와 관련한 모든 세션들이 트래킹되도록 도움 |
    | ExceptionTranslationFilter           | 이 필터는 보호된 요청을 처리하는 동안 발생할 수 있는 기대한 예외의 기본 라우팅과 위임을 처리함 |
    | FilterSecurityInterceptor            | 이 필터는 권한부여와 관련한 결정을 AccessDecisionManager에게 위임해 권한부여 결정 및 접근 제어 결정을 쉽게 만들어 줌 |

  * 제일 마지막에 위치한 FilterSecurityInterceptor는 앞에 지나온 모든 필터들의 정보를 토대로 최종 결정을 내린다



### 커스터마이징

* 기본 옵션에서 벗어난 요구사항들을 충족시키기 위해 커스터마이징 하거나 확장이 가능
* 전체 스프링 시큐리티 필터 체인과 지원 인프라 스트럭쳐를 처음부터 하나씩 직접 설정해야 함
* 필터들은 순서가 존재하며 이 순서를 어길 경우 예상치 못한 결과가 나올 수 있으므로 특별한 경우가 아니라면 순서를 지키는것이 좋다.
* 필터체인 빈 의존관계 그림을 보고 설정하면 수월할 것