# Environment

프로파일과 프로퍼티를 다루는 인터페이스.

`ApplicationContext extends EnvironmentCapable`

## Profile

테스트용에는 빈 A, 배포용에는 빈 B를 쓰고싶다.

`VM options: -Dspring.profiles.active="test"`

* Bean들의 그룹
* Environment의 역할은 활성화할 프로파일 확인 및 설정

`Configuration`, `Component Scan으로 추가되는 Bean에도 @Profile("~~")`

* 표현식
  * !, &, | 사용 가능 
    * ex) `@Profile("!prod & test") ...`

## Property

* 다양한 방법으로 정의할 수 있는 설정값
* Environment의 역할은 Property 소스 설정 및 Property 설정 가져오기



### 우선 순위

StandardServletEnvironment의 우선순위

* ServletConfig 매개변수
* ServletContext 매개변수
* JNDI(java:comp/env/)
* JVM 시스템 프로퍼티(-Dkey="value")
* JVM 시스템 환경변수(운영체제 환경변수)
* Configuration에 `@PropertySource("classpath:/...")` - `@Value("${app.name}")`

