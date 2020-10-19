> # Spring MVC Pattern
>> STS - Spring Legacy Project

* Consist Of
  * Controller
  * DTO
  * DAO
  * Service

* Directory

  * src/main/resources/mapper/mapper.xml

  * src/main/webapp/resources - 정적 요소 (CSS, JS, img)
  * src/main/webapp/WEB_INF - 서버영역 JVM
    * 클라이언트가 직접 접근 불가
  * src/main/webapp/views/~.jsp

* context.xml
  * servlet-context.xml
    * src/main/webapp/spring/appservlet/
    * V, C
  * root-context.xml
    * src/main/webapp/spring/
    * M (DAO, Service)