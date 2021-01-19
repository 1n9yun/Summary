> # Others
>
> > 기타



* #### Filter

  * Filter와 스프링 인터셉터의 공통점
    * 특정 URI에 접근할 때 제어하는 용도
  * 차이점
    * 실행 시점에 속하는 영역(Context)에서 차이
    * Filter는 동일한 웹 어플리케이션의 영역내에서 필요한 자원들 활용
    * 인터셉터는 스프링에서 관리되기 때문에 스프링 내의 모든 객체(빈) 접근이 가능

* Interceptor와 스프링 인터셉터의 차이점

  * AOP는 객체 동작의 사전 혹은 사후처리를 하는데 JoinPoint나 ProceedingJoinPoint 활용
  * Interceptor는 HttpServletRequest와 HttpServletResponse를 파라미터로 받는 구조

