# first-spring-boot

## 인프런 강의
- https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard

## Unit test
- 메소드 별로 해당 코드가 잘 작동하는지 확인한다.
- 주로 given - when - then 순서로 시험한다.

## 생성자 주입, 필드 주입
- @Autowired를 통해 필드 주입을 할 수 있지만, 이는 순환 참조의 위험이 있고 final 선언이 불가능하다.
- 생성자 주입을 통해 이를 해결할 수 있다. 

```Java
@Component
public class A {
    @Autowired // 필드 주입
    private B b;

    public void jack() {
        b.bb();
    }
}
//위의 필드 주입을 아래의 생성자 주입으로 변경
@Component
public class A {
    private final B b;
    
    public A(B b){ // 생성자 주입
      this.b = b;
    }
    public void jack() {
        b.bb();
    }
}
```


