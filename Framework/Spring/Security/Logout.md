# Handling Logouts

> https://docs.spring.io/spring-security/site/docs/5.4.0-M1/reference/html5/#jc-logout

* **default**

  * Invalidating the HTTP Session
  * Cleaning up any RememberMe authentication that was configured
  * Clearing the `SecurityContextHolder`
  * Redirect to /login?logout

* **Customize**

  * Custom LogoutSuccessHandler를 명시하면 logoutSuccessUrl()은 무시된다.
  * addLogoutHandler(SecurityContextLogoutHandler ... )
  * CookieClear

* **LogoutSuccessHandler**

  * SimpleUrlLogoutSuccessHandler
  * HttpStatusReturningLogoutSuccessHandler
    * REST API 시나리오에 적합할 수 있음
    * 리다이렉트 URL 대신 HTTP status code를 리턴해 줄 수 있음
    * default code는 200

  

  