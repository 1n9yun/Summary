> # REST API 
> > for Spring

* #### Basic

    ``` java
    @RequestMapping("/test")
    public String test(){
    	// prefix + "test" + suffix
        // WEB-INF/views/ + test + .jsp
        // 단순 페이지 forward
        return "test";
    }
    ```

---

* #### ResponseBody
  
    ``` java
    @RequestMapping("/test")
    @ResponseBody
    public String foo(){
        // hello 라는 문자열 데이터를 응답으로
        return "hello";
    }
    ```
    
* #### RestController
	``` java
	// 이 클래스 안의 모든 함수는 @ResponseBody를 붙인 것 처럼 동작
	// 리턴값은 text, array, json
	@RestController
	public class TestController{
		Person p = new Person("홍길동", 23, "서울");
	    
	    // JSON
	    return p;
	}
	```

* #### PathVariable

  ``` java
  // {no}, {uname} 경로변수
  @RequestMapping("/board/{no}/{uname}")
  public String getPath(@PathVariable("no") integer no, @PathVariable("uname") String uname){
      return no + "hi" + uname;
  }
	```

* #### ResponseEntity<Type>

  ``` java
  @ReqeustMapping("/good")
  public ResponseEntitiy<String> test(){
      // 서버 상태 임의로 변경 가능
      // 서버 상태를 정해서 주고 싶다면 이걸 쓰고 아니면 그냥 데이터 리턴
      return new ResponseEntity<>("좋아요", HttpStatus.OK);
      return new ResponseEntity<>("좋아요", HttpStatus.INTERNAL_SERVER_ERROR);
  }
  ```

* #### RequestBody

  > ``` xml
  > <!-- JSON Converter VO -> JSON -->
  > <dependency>
  >     <groupId>com.fasterxml.jackson.core</groupId>
  >     <artifactId>jackson-databind</artifactId>
  >     <version>2.9.6</version>
  > </dependency>
  > 
  > <!-- XML Converter VO -> XML -->
  > <dependency>
  >     <groupId>com.fasterxml.jackson.dataformat</groupId>
  >     <artifactId>jackson-dataformat-xml</artifactId>
  >     <version>2.9.6</version>
  > </dependency>
  > ```

  ``` java
  @PostMapping(...)
  // 클라이언트(내의 javascript)가 보낸 JSON데이터를 VO로 변환하는 역할
  public String create(@RequestBody ReplyVO vo){
      return "";
  }
  ```

---

* #### Swagger

  * REST API 개발 시 문서를 자동으로 만들어주는 프레임워크

  * 간단한 설정으로 프로젝트에 지정한 **URL들을 HTML화면으로 확인**

  * API에 대한 **메뉴얼 자동 생성**

  * **API테스트 가능** (이거 때문에 쓰는거임)

  * Java, Python, Node.js등 다양한 언어를 지원

  * ``` xml
    <!-- Swagger 2 추가 -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.9.2</version>
    </dependency>
    ```

  * SwagConfig.java
  
    * ``` java
      // 전에 ~-context.xml파일을 대체하는 annotation, 여기가 스프링의 객체 저장소 중 하나가 된다는 이야기
      @Configuration
      @EnableSwagger2
      public class SwagConfig {
      //	자바로 사용할 때는 @Bean
      	
      	@Bean
      	public Docket postApi() {
      		return new Docket(DocumentationType.SWAGGER_2)
      				.groupName("public-api")
      				.apiInfo(apiInfo())
      				.select()
                  .apis(RequestHandlerSelectors.basePackage("com.ssafy.bookboot.controller"))
      				.build();
      	}
      	
      	private ApiInfo apiInfo() {
      		return new ApiInfoBuilder().title("스웨거 스타트!!!")
      				.description("스웨거를 추가해놓으면 포스트맨을 안써도 테스트가 좀 더 편해염")
      				.version("1.0")
      				.build();
      	}
      }
      ```
  
    