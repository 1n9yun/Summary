# Resource

`org.springframework.core.io.Resource`

* java.net.URL을 감싸 추상화한 것
* Spring 내부에서 많이 사용하는 인터페이스

추상화의 이유는 

* `classpath` 기준으로 리소스를 읽어오는 기능의 부재
* ServletContext를 기준으로 상대 경로로 읽어오는 기능 부재
* 새로운 핸들러를 등록하여 특별한 URL 접두사를 만들어 사용할 수 있지만 구현이 복잡하고 편의성 메소드가 부족하다.



## 구현체

* UrlResource: 기본으로 지원하는 프로토콜 http, https, ftp, file, jar.
* ClassPathResource: 지원하는 접두어 classpath
* FileSystemResource
* ServletContextResource: 웹 애플리케이션 루트에서 상대 경로로 리소스를 찾는다.

## Resource 읽어오기

* Resource의 타입은 location 문자열과 ApplicationContext의 타입에 따라 결정
  * ClassPathXmlApplicationContext => ClassPathResource
  * FileSystemXmlApplicationContext => FileSystemResource
  * WebApplicationContext => ServletContextResource

