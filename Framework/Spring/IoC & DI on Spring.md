# IoC & DI & Beans on Spring

> https://taes-k.github.io/2019/05/11/how-spring-work/

## IoC

서비스 제어의 흐름을 역전시켰다는 뜻으로, 개발자가 아닌 프레임워크가 흐름을 제어하는 주체가 되어 필요할 때 코드를 호출하며 사용하게 된다.

## DI

한 객체가 다른 객체를 참조할 때 객체간 의존성을 가진다고 말한다.

의존성을 가질 때 하나의 객체 변화에 다른 객체가 영향을 받아 같이 변경해야할 수 있다.

스프링은 이러한 객체들 간 의존성을 줄이기위한 방법으로 IoC/DI를 하나로 묶어서 제공한다.

객체 선언 과정을 스프링 프레임워크가 미리 대신 해줌으로써(IoC) 객체를 가져다가(DI) 사용할 수 있게 해준다.

* Spring Container에 객체를 등록해두고 여기에 있는 객체들을 사용하게 된다.
* 그게 IoC Container

### 장점

* 코드가 깔끔해진다.
* 객체들 간 종속성이 감소
* 재사용성 증가
* 유닛 테스트가 쉬워진다

### 준비

DI를 사용할 객체를 프레임워크에 알려줘야 한다. 이 때 IoC Container에 등록되어지는 객체를 Bean이라고 하는데, 등록하기 위한 방법으로 3가지가 있다.

* XML-based configuration
* Annotation-based configuration : after Spring 2.5
* Java-based configuration : after Spring 3.0

```java
// java-based configuration

@Configuration
public class AppConfig{
    @Bean
    public MyService myService(){
        return new MyServiceImpl();
    }
}
```

```xml
// XML-based configuration

<beans>
    <bean id="myService" class="me.1n9yun.services.MyServiceImpl"/>
</beans>
```

어떤 방식으로 설정을 하든 똑같이 bean으로 등록이 가능하며 이 설정 과정을 통해 등록된 빈은 IoC Container를 생성할 때 함께 생성되어진다.

## Bean

### 사용법

```java
// Datasource Config

@Configuration
public class DatasourceConfig { 
    @Autowired
    ApplicationContext applicationContext;

    @Primary
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix = "spring.datasource") 
    public DataSource dataSource() { 
        return DataSourceBuilder.create().build(); 
    } 

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception { 
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); 
        sqlSessionFactoryBean.setDataSource(dataSource()); 
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/sql.xml")); 
   
        return sqlSessionFactoryBean.getObject(); 
    } 

    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception { 
        return new SqlSessionTemplate(sqlSessionFactory()); 
    } 
}
```

```java
// DAO

public class Dao{
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlSession;
}
```

일반적으로 DB Connection을 관리하는 Datasource는 하나의 객체로써 연결 풀을 관리하도록 하며 이를 위해 스프링에서는 Singleton 방식으로 IoC Container에 등록해 여러 Dao에 주입시켜 사용할 수 있도록 한다.

`@Bean`과 `@Autowired`를 이용해 IoC Container에 등록하고 주입받아 사용하는 것을 볼 수 있다.

### Scope

`Bean`들은 `Singleton`객체로 관리되며 `Bean`마다의 `Scope`설정을 통해 `life-cycle`과 객체의 생성 형태를 관리할 수 있다.

| scope              | 설명                                                      |
| ------------------ | --------------------------------------------------------- |
| singleton          | Spring IoC Container내에서 단 하나의 객체만 존재한다.     |
| prototype          | Spring IoC Container내에서 다수의 객체가 존재 가능하다.   |
| request            | HTTP Request life-cycle 내에 단 하나의 객체만 존재한다.   |
| session            | HTTP Session life-cycle 내에 단 하나의 객체만 존재한다.   |
| application        | ServletContext life-cycle 내에 단 하나의 객체만 존재한다. |
| web-socket session | WebSocket life-cycle 내에 단 하나의 객체만 존재한다.      |

singleton으로 사용하게 되면 하나의 객체가 여러 참조 위치에서 사용되기 때문에 변화가 생길경우 타 참조위치들에서의 변화를 동기화시키는데 비용이 들 수 있기 때문에 사용 목적에 따라 싱글톤으로 할지 비 싱글톤으로 사용할지  고려해야 한다.

일반적으로 싱글톤으로 사용하는 객체들의 예시로는 다음과 같다.

* 상태가 없는 객체
* 데이터를 불러오는 용도로 사용하는 객체
* 공유가 필요한 상태를 지닌 객체
* 사용빈도가 매우 높은 객체

#### Spring Component

위에서는 직접적인 경로 지정을 통한 Bean 등록 방법을 확인했다. 하지만 스프링에서는 스테레오 타입(stereo type)을 이용하여 해당 클래스를 식별하여 자동으로 Bean으로 등록해주는 기능 또한 제공하고 있다.

| 스테레오 타입 | 설명                                                         |
| ------------- | ------------------------------------------------------------ |
| @Component    | 기본 스테레오 타입, 일반적인 용도를 가진 컴포넌트들을 지칭해준다. |
| @Controller   | @Component에서 특화된 타입, Web MVC에서 Controller 컴포넌트를 지칭해준다. |
| @Service      | @Component에서 특화된 타입, 비즈니스 로직을 다루는 서비스 레이어를 지칭해준다. |
| @Repository   | @Component에서 특화된 타입, 데이터 접근 객체를 지칭해준다.   |

위의 스테레오 타입 어노테이션들을 통해 컴포넌트 클래스를 목적에 맞게끔 선언해주면 미리 세팅된 기능들을 부여받을 수 있으며 aspects에 더 연관성을 부여해 줄 수 있다.

### BeanFactory와 ApplicationContext

IoC Container == Spring Container == BeanFactory == ApplicationContext

정확히 말하면, IoC Container에는 2가지 종류가 있다. BeanFactory, ApplicationContext

ApplicationContext는 빈 팩토리 인터페이스를 상속시켜 확장한 클래스이며 빈팩토리의 모든 기능을 지원한다. 차이점은 다음과 같다.

| Feature                                               | BeanFactory | ApplicationContext |
| ----------------------------------------------------- | ----------- | ------------------ |
| Bean instantiation/wiring                             | Yes         | Yes                |
| Integrated lifecycle management                       | No          | Yes                |
| Automatic BeanPostProcessor registration              | No          | Yes                |
| Automatic BeanFactoryPostProcessor registration       | No          | Yes                |
| Convenient MessageSource access (for internalization) | No          | Yes                |
| Built-in ApplicationEvent publication mechanism       | No          | Yes                |

BeanPostProcessor와 BeanFactoryPostProcessor는 빈 등록 후 변경 및 후처리를 진행할 때 사용되는 클래스로, ApplicationContext의 경우 자동으로 해당 클래스를 감지하여 처리해 줄 수 있다.

ApplicationContext는 BeanFactory의 모든 기능들을 포함하고, AOP 서비스, 편리한 메시지 소스 접근, 내장 애플리케이션 이벤트 처리 메커니즘 등의 이유들을 바탕으로 공식문서에서는 BeanFactory보다는 ApplicationContext사용을 권장하고 있다.

## 정리

* Spring DI의 핵심 요소
* Bean의 생성과 소멸 관리(Dependency & life-cycle)
* Context 시작 시 모든 Singleton life-cycle Bean들을 미리 로딩 시켜 둠
* Spring의 AOP기능, 메시지 자원 핸들링, 이벤트 위임, 웹 어플리케이션에서 사용하기 위한 WebApplicationContext와 같은 특정 어플리케이션 컨텍스트를 이용한 통합과 같은 다른 기능을 추가 제공한다.