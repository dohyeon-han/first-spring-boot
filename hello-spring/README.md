# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
- https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard

### Spring legacy, Spring boot
- 우선 legacy는 WAS를 따로 설치를 해야하지만 boot는 WAS가 내장되어있어 따로 설치하지 않아도 된다.
- 또한 legacy는 xml 파일, configuration 설정 등 개발자가 개발을 위해 해야하는 설정 작업들이 있었다면 boot는 그 설정을 미리해준다.

### Unit test
- 메소드 별로 해당 코드가 잘 작동하는지 확인한다.
- 주로 given - when - then 순서로 시험한다.

### 생성자 주입, 필드 주입, Setter 주입
- @Autowired를 통해 필드 주입을 할 수 있지만, 이는 순환 참조의 위험이 있고 final 선언이 불가능하다.
- Setter 주입은 호출이 가능하므로 의존 관계가 동적으로 변할 위험이 있다.
- 생성자 주입을 가장 권장하고 있다.

```Java
@Component
public class A {
    @Autowired // 필드 주입
    private B b;

    public void func() {
        b.bb();
    }
}

@Component
public class A {
    private final B b;
    
    public void setA(B b){ // Setter 주입
      this.b = b;
    }
    public void func() {
        b.bb();
    }
}

@Component
public class A {
    private final B b;
    
    public A(B b){ // 생성자 주입
      this.b = b;
    }
    public void func() {
        b.bb();
    }
}
```

### JPA
- JPA(Java Persistence API)란 자바 ORM(Object-Relational Mapping) 기술에 대한 표준 명세로, JAVA에서 제공하는 API이다. 
- JPA는 구현체가 아닌 인터페이스이며 이를 구현한 구현체로는 Hibernate, OpenJPA 등이 있다.
- CRUD 같은 반복되는 코드의 양을 줄여 개발 생산성을 더욱 높일 수 있다.

### Spring Data JPA
- 인터페이스만을 사용하여 기본적인 CRUD를 사용할 수 있다.
