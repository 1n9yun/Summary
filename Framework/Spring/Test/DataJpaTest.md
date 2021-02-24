# DataJpaTest

## `@DataJpaTest`

기본적으로, `@Entity`클래스들과 설정들, Spring Data JPA Repository들을 스캔

`classpath`에 임베디드 데이터베이스가 사용가능하면 설정함

이 어노테이션이 사용되면 표준 `@Component`, `@ConfigurationProperties`빈들은 스캔되지 않는다. 이 때 `@EnableConfigurationProperties`를 사용



기본적으로, data JPA tests는 transactional하고 각 테스트가 끝나면 롤백된다. 원하지 않으면 아래와 같이 disable 가능

```java
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class ExamplenonTransactionalTests{
    
}
```

Data JPA tests는 `TestEntityManager`빈을 주입할 수 있으며 표준 JPA `EntityManager`의 대안으로 테스트만을 위해 디자인되었으며 사용된다. `@DataJpaTest` 인스턴스의 밖에서 사용하고 싶다면 `@AutoConfigureTestEntityManager`를 사용

예시 : 

```java
@DataJpaTest
class ExampleRepositoryTests{
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;
    
    @Test
    void testExample() throws Exception {
        this.entityManager.persist(new User("sboot", "1234"));
        User user = this.repository.findByUsername("sboot");
        assertThat(user.getUsername()).isEqualTo("sboot");
        assertThat(user.getVin()).isEqualsTo("1234");
    }
}
```

인메모리 내장 db는 일반적으로 테스트가 잘 동작하며 빠르고 어떤 설치도 필요하지 않다. 하지만 실제 db로 테스트하고 싶다면 `@AutoCOnfigureTestDatabase` 어노테이션을 사용할 수 있다.

```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ExampleRepositoryTests{
    
}
```



