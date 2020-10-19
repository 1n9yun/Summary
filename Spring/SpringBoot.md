> # Spring Boot
> > starter project

* #### Intro

  * Spring Legacy 

    * 동기화된 상태의 서버. 즉 요청에 대해 기본적으로 화면(html)을 응답하는 형태였으나, Restful서버의 유행으로 비동기 요청에 대해 데이터를 응답하는 기능이 필요해졌음. 그래서 @ResponseBody, @Rescontroller 어노테이션들.
  * Spring Boot
    
    * 기존 레거시에서 개발자가 해야하는 셋팅. 톰캣 설치부터 시작해서 DB작업에 필요한 DataSource(커넥션 관리) 등 설정을 자동화 시켜 놓음.
    * 비동기 서버. 즉 화면에 대한 고려 없이 요청하면 무조건 데이터만 응답하는 RESTful 컨트롤러가 Default
  

---

* #### Setting

  * src/main/webapp/WEB-INF/views

  * src/main/resources/mapper

    * ``` xml-dtd
      <?xml version="1.0" encoding="UTF-8"?>
      <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
      <mapper namespace="ns">
      	<insert id="" resultType="" parameterType="" ...>
      	<delete>
      	...
      </mapper
      ```

  * src/main/resources/application.properties
    * server.servlet.context-path=(root)
    
    * server.port=9090
    
    * spring.datasource.url=jdbc:mysql://localhost:3306/live_db?serverTimezone=UTC&characterEncoding=UTF-8
    
    * spring.datasource.driver-class-name=
    
    * spring.datasource.username=
    
    * spring.datasource.password=
    
    * spring.mvc.view.prefix=/WEB-INF/views/
    
    * spring.mvc.view.suffix=.jsp
    
    * mybatis.mapper-locations=classpath:/**/mapper/ *.xml
    
      * || src/main/resources/mybatis-configuration.xml
    
      * ``` xml-dtd
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
        <configuration>
        	<mappers>
        		<mapper resource="mappers/ -.xml"/>
        	</mappers>
        </configuration>
        ```
    
    * mybatis.type-aliases-package=(dto package)com.ssafy.product.dto

---

* #### @Configuration

  * root-context.xml, servlet-context.xml 파일. 즉 객체 저장소 파일을 java파일로 대체
  * Spring 프로젝트에서 많이 씀