> # Spring Framework

* #### Spring Project
	* Legacy - 고전적인 형태의 프로젝트
	
	* boot - 최근 유행하는 형태의 프로젝트(RESTful 서버 작업 위주의 작업에 쓰이는 프로젝트)
	
---

* #### Framework
	* 제약이 많음. 규칙이 엄격하고 많음. 그 규칙만 지키면 개발은 편함(귀찮은 작업을 해줌)
	* 응집도는 up 결합도는 down

---

* #### 특징
	* 객체지향 설계기법이 많이 녹아있음. (스프링이 지키라는 규칙을 지키다보면 디자인 패턴 기법을 나도 모르게 쓰고있음.)

---

* #### Spring Framework가 해주는 대표적인 작업
	> IoC : 개발자가 control 하던걸 Framework가 함

	* DI (Dependancy Injection) - 의존성 주입(여기서 나오는 제어의 역전 (IoC)) : 객체 관리(서블릿, 컨트롤러, 서비스, Dao) 

---

* #### 프로젝트 설정

  > pom.xml

---

* #### xml

  * servletContext.xml

    * controller, viewResolver
    * MVC 요청 흐름 관련
    * DispatcherServlet의 클래스 창고

  * rootContext.xml : Model과 관련

    * DBUtil

  * mapper.xml

    * 보통 디비 테이블당 하나씩 생성 (member-mapper.xml, board-mapper.xml ...)
    * SQL 명령어 작성 (java파일에 적는 경우도 있음)

    * myBatipse 플러그인 설치하면 mapper xml위에 세줄 자동완성해줌

---

* #### root-context.xml

  * 모델, 디비 관련된 스프링 객체 저장 창고
  * dataSource객체 (디비 커넥션 담당)
  * sqlSessionFactoryBean객체 (마이바티스 설정 담당)
  * sqlSessionTemplate (insert할 때 ?,?,? 채우기나 ResultSet에서 칼럼 읽어서 Dto에 담기 같은 노가다 기능 구현된 클래스)
    * Dao 클래스 작성 시 편해짐