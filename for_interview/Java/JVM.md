# JVM

* C/C++는 컴파일 플랫폼과 타겟 플랫폼이 다를 경우 프로그램이 동작하지 않는다.

* 크로스 컴파일 - 타겟 플랫폼에 맞춰 컴파일 하는 것

* Java는 JVM으로 문제를 해결

* 자바 바이트코드는 타겟 플랫폼에 산과 없이 JVM 위에서 동작

* 하지만 JVM은 타겟 플랫폼에 의존한다.
  * C/C++도 크로스 컴파일해서 배포하면 되는데 굳이 JVM??
  * 자바는 네트워크에 연결된 모든 디바이스에서 작동하는 것이 목적이었다.
  * 그래서 Java bytecode, JVM이 나왔다

  

## 구조

* Class Loader, GC, Runtime Data Area, Execution Engine

* ### Class Loader

  * 자바 컴파일러가 .java 파일을 컴파일하면 .class파일(바이트 코드)가 생성됨

  * 생성된 class파일들을 엮어 Runtime Data Area 형태로 메모리에 적재하는 역할

  * #### 절차

    * 어떤 메소드를 호출하는 문장을 만났는데, 그 메소드를 가진 클래스 바이트 코드가 아직 로딩된 적이 없다면 바로 JVM은 JRE 라이브러리 폴더에서 클래스를 찾는다.
    * 없으면 CLASSPATH 환경 변수에 지정된 폴더에서 클래스를 찾음.
    * 찾았으면 클래스 파일이 올바른지 바이트코드를 검증한다.
    * 올바른 바이트코드라면 메소드 영역으로 파일을 로딩한다.
    * 클래스 변수를 만들라는 명령어가 있으면 메소드 여역에 그 변수를 준비한다.
    * 클래스 블록이 있으면 순서대로 그 블록을 실행한다.
    * 이렇게 한 번 클래스의 바이트 코드가 로딩되면 JVM이 종료될 때까지 유지된다.

* ### Execution Engine

  * 메모리에 적재된 클래스들을 기계어로 변경해 명령어 단위로 실행하는 역할
  * 명령어를 하나 하나 실행하는 인터프리터 방식, 실행 시점에 자주 쓸만한 코드들을 기계어로 변환 시켜놓고 저장해서 사용하는 JIT 방식이 있다.

* ### Garbage Collector

  * Heap 메모리 영역에 생성 된 객체들 중 Reachability를 잃은 객체를 탐색 후 제거하는 역할
  * 자세한 내용 <a href="./GarbageCollector.md">참고</a>

* ### Runtime Data Area

  * #### Method Area

    * 클래스 멤버변수, 메소드 정보, Type 정보, Constant Pool, static, final 변수 등이 생성

  * #### Heap Area

    * 동적으로 생성된 오브젝트와 배열이 저장되는 곳
    * Garbage Collection의 대상이 되는 영역

  * #### Stack Area

    * 지역 변수, 파라미터 등이 생성되는 영역, 동적으로 객체를 생성하면 실제 객체는 Heap에 할당되고 해당 레퍼런스만 Stack에 저장된다 Stack은 스레드별로 독자적으로 가진다.
    * Heap에 있는 오브젝트가 Stack에서 참조할 수 없는 경우 GC의 대상이 된다.

  * #### PC Register

    * 현재 스레드가 실행되는 부분의 주소와 명령을 저장하고 있다.(CPU의 그것과 다름)
    * 자세한 내용 <a href="https://m.blog.naver.com/PostView.nhn?blogId=2000yujin&logNo=130156226754&proxyReferer=https%3A%2F%2Fwww.google.com%2F%5D(https://m.blog.naver.com/PostView.nhn?blogId=2000yujin&logNo=130156226754&proxyReferer=https%3A%2F%2Fwww.google.com%2F">참고</a>

  * #### Native Method Stack

    * 자바 외 언어로 작성된 네이티브 코드를 위한 메모리 영역

