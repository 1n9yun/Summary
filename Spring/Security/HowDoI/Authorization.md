# Authorization

* config에 필터로 등록하여 사용
* **OncePerRequestFilter** 상속 - 각 요청이 한 번만 이 필터를 거치도록 하는 필터, 다른 필터도 있음.
* Jwt에 저장되어 있는 Username, Password로 DB와 비교하는 등 검사 후 Authentication 객체를 생성하여 Context에 등록함으로써 권한 부여



---

관련 파일 in Stew

* com.ssafy.study.config.security
  * JwtSecurityConfig에서 AuthenticationFilter 등록
  * JwtAuthorizationFilter