# Authentication

* SpringSecurityBase.md에 있는 필터들이 자동 생성(기본 기능) 됨 -> 커스터마이징(상속)하여 사용
* 나는 이 중 UsernamePasswordAuthenticationFilter를 커스터마이징
* 인증되면 SecurityContextHolder에 등록되고 권한에 따른 요청 필터링 가능
* ![springsecurity_architecture](https://user-images.githubusercontent.com/38209225/96408521-a581d400-121e-11eb-86ff-c28e0ea1b5dc.png)
  1. AuthenticationFilter에서 Authentication 객체에 인증 결과를 만들어 반환해줌
     * Manager는 등록된 Provider들을 돌면서 인증하는데 하나라도 실패하면 인증 안됨
  2. **UsernamePasswordAuthenticationToken** (Authentication의 구현체)
  3. attemptAuthentication 메소드에서 인증 시도
  4. authenticationManager에 등록된 Provider들을 이용해 인증(Provider, Manager도 커스터마이징 가능)
  5. 이 때 SpringSecurity가 이용하는 UserDetailsService Interface를 구현하여 사용(DB와 비교 등)
  6. UserDetails Interface를 구현하여 인증 결과 User 객체가 반환 됨(이게 UserPrincipal)
  7. 인증 결과를 Provider에 반환
  8. Manager에 결과 반환
  9. Filter에 결과를 반환하고 FilterChain이 계속 진행 됨
  10. **AuthenticationSuccessHandler**를 만들어 SecurityContextHolder에 인증된 Context(Authentication)를 등록
      * 여러가지 SuccessHandler 객체가 기본 정의 돼있는데 각각이 뭔지는 찾아보기
      * 나는 **SavedRequestAwareAuthenticationSuccessHandler**를 상속받아 정의



++ **AuthenticationEntryPoint**를 정의하여 인증 과정에서의 ExceptionHandling



---

관련 파일 in Stew

* com.ssafy.study.config.security
  * JwtSecurityConfig - Authentication, Authorization, Provider, Manager, Handler 등등 등록
  * JwtAuthenticationFilter - 인증 필터
  * CustomAuthenticationProvider - 인증하는 방법 (DB와 비교했음)
  * JwtAuthenticationSuccessHandler - 인증 성공 시 실행
    * 여기서 Jwt 토큰을 Header에 넣고, Redis에 저장하는 등
* com.ssafy.study.user.model
  * UserPrincipal(UserDetails의 구현체)
* com.ssafy.study.user.service
  * UserPrincipalDetailsService
    * 기본 Provider 중에 이 Service를 등록하면 알아서 DB와 비교하는 것도 있었음