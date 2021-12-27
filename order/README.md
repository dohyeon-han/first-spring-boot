# 스프링 핵심 원리 - 기본편
- https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8

## 스프링 부트
- 스프링 부트는 스프링 프레임워크를 보다 편하게 사용할 수 있게 해주는 도구이다.
- '객체 지향 프로그래밍'을 할 수 있도록 도와준다.

## 객체 지향 프로그래밍
- 프로그램을 다양한 객체로 처리하는 프로그래밍 방법론 중 하니이다.
### 특징
- 추상화
  - 객체의 특징적인 부분만 뽑아내는 작업이다.
  - Java에서 interface를 예로 들 수 있다.
  - 코드의 재사용성을 높이고, 중복된 코드를 방지할 수 있다.
- 캡슐화
  - 코드를 캡슐처럼 감싸 내부와 외부로 나누는 것이다.
  - 코드의 재사용성을 높이고, 정보를 은닉할 수 있다.
- 상속성
  - 부모와 자식간의 관계를 만들어 부모의 속성과 메소드를 자식에서도 사용할 수 있게 한다.
- 다형성
  - 상속을 통해 메소드를 받더라도 이 메소드를 다른 형태로 바꿀 수 있다.
  - Overriding를 통해 부모 클래스로부터 받은 메소드를 인자는 그대로 두되, 새 기능으로 바꿀 수 있다.
  - Overroading을 적용하면 같은 이름의 메소드를 인자의 개수에 따라 다르게 구현 할 수 있다.
### 객체 지향 설계의 5원칙 : SOLID
- SRP (Single Responsibility Principle): 단일 책임 원칙
  - 하나의 메소드 혹은 클래스는 하나의 책임을 가져야한다.
  - 코드의 변경이 있을 때 다른 클래스로의 연쇄작용이 줄어든다.
- OCP (Open / Closed Principle): 개방 / 폐쇄 원칙
  - 코드는 수정과 추가에는 열려있지만, 수정과 삭제에는 닫혀있다.
  - 요구사항에 변경이 발생하더라도 기존 구성 요소의 수정은 최소화해야하며, 확장은 쉬워야한다.
- LSP (Liskov Substitution Principle): 리스코프 치환 원칙
  - 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
  - 예를 들어 자동차 인터페이스의 엑셀은 앞으로 가라는 기능인데 이를 뒤로 가게 구현하면 LSP에 위반되는 것처럼, 인터페이스 규약을 따라야 한다.
- ISP (Interface Segregation Principle): 인터페이스 분리 원칙
  - 사용하지 않는 인터페이스는 구현하지 말아야 한다.
  - 어떤 클래스가 상속을 받을 때는 가능한 최소한의 인터페이스만 사용해야한다.
  - 하나의 일반적인 인터페이스보다는 여러개의 구체적인 인터페이스가 낫다.
- DIP (Dependnecy Inversion Principle): 의존관계 역전 원칙
  - 프로그래머는 구체화에 의존하지 않고, 추상화에 의존해야 한다.
  - 구현체와의 관계를 느슨하게 해야 한다.

## 스프링 컨테이너, 빈
- ApplicationContext는 스프링 컨테이너이다.
- XML 또는 애노테이션을 사용해 생성한다. 최근에는 애노테이션 기반으로 많이 사용한다고 한다.
- 스프링 컨테이너는 Bean Factory, ApplicationContext로 나뉘지만 Bean Factory를 직접적으로 쓰는 경우가 거의 없어서 일반적으로 ApplicationContext를 스프링 컨테이너라고 한다.
### 스프링 컨테이너 생성 과정
  - 스프링 컨테이너 생성
  - 스프링 빈 등록 (빈 이름 - 빈 객체 쌍으로 저장, 빈 이름은 중복 불가)
  - 스프링 빈 의존 관계 설정 준비 (Bean으로 등록)
  - 스프링 빈 의존 관계 설정 완료 (DI 주입)

```JAVA
// AppConfig 클래스에 존재하는 빈들을 가져온다.
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
```
### 빈 조회, 상속
```JAVA
Object bean = ac.getBean("memberService"); // 이름으로 조회
Object bean = ac.getBean(MemberService.class); // 타입으로 조회(구체클래스보단 추상클래스의 타입으로 조회하는 것이 좋다(SOLID원칙))
Object bean = ac.getBean("memberService", MemberService.class); // 이름과 타입으로 조회
Map<String, Object> beans = ac.getBeansOfType(Object.class); // 해당 타입을 가진 모든 bean 조회
```
- 이름으로 빈 조회 시, 같은 이름의 빈이 존재한다면 NoUniqueBeanDefinitionException 예외가 발생한다.
- Object는 모든 객체의 최상위 클래스이므로 Object로 빈을 조회하면 모든 빈을 조회한다.
![image](https://user-images.githubusercontent.com/63232876/147448018-5a2690d2-ac08-46d5-9a1c-e89638d3f7b6.png)
### BeanFactory, ApplicationContext
- Bean Factory는 스프링 컨테이너의 최상위 인터페이스이다.
- 스프링 빈을 관리하고 조회하는 역할을 담당한다. 빈 조회에 사용한 getBean() 을 BeanFactory이 제공한다.
- ApplicationContext는 여러 인터페이스를 상속받아서 국제화, 이벤트, 리소스 조회 등의 기능을 제공한다. 
![image](https://user-images.githubusercontent.com/63232876/147449986-529d6496-22d7-43ad-8050-b54eb74cd32f.png)
- BeanFactory 나 ApplicationContext를 스프링 컨테이너라고 한다.
### XML
- 스프링 레거시에서 주로 사용한다.
- 컴파일 없이 빈 설정 정보를 변경할 수 있다는 장점이 있다.
```XML
<beans>
  <bean id="이름" class="class 파일 경로">
    <constructor-arg name="변수명" ref="등록할 빈 이름" />
</beans>
```

## 싱글톤 컨테이너
### 싱글톤 패턴
- 클라이언트의 요청마다 계속 같은 객체가 생성, 소멸되는 것은 비효율적이다.
- 싱글톤 패턴은 하나의 객체를 생성하여 사용되도록 한다.
- 하지만 싱글톤 패턴은 클라이언트가 구체 클래스에 의존하여 DIP를 위반한다.
- 내부 속성을 변경하기 어렵다.
### 싱글톤 컨테이너
- 스프링 컨테이너는 이를 해결하고 객체를 싱글톤으로 관리한다.
![image](https://user-images.githubusercontent.com/63232876/147454153-09dddae5-e0e2-4d8e-913d-0e7fe9925767.png)
### 주의사항
- 여러 클라이언트가 하나의 객체를 공유하므로 객체는 반드시 statless를 유지해야한다.
- 이를 지키지 않으면 race condition이 발생한다.

## @Configuration
- @Configuration은 객체의 싱글톤을 보장해준다.

## 빈 중복 등록
- 자동 vs 자동
  - @Component("")를 같은 이름으로 등록하면 오류가 발생한다.
- 수동 vs 자동
  - 이전에는 수동으로 등록한 빈이 자동으로 등록한 빈을 오버라이드 했지만, 지금은 오류가 발생하도록 바뀌었다.

## 의존 관계 자동 주입
- 생성자 주입, settet 주입, 필드 주입, 일반 메서드 주입이 있지만 주로 생성자 주입을 권장한다.
- 그 이유는 대부분의 의존 관계는 애플리케이션 종료 전까지 변경할 일이 없고, 필요한 의존 관계가 누락되었을 때, 컴파일 오류로 쉽게 고칠 수 있기 때문이다.
### lombok
- @RequiredArgsConstructor을 사용해 생성자를 없이 final을 사용하는 변수에 대해 자동으로 의존 관계를 주입해준다.
- @Getter, @Setter, @ToString으로 getter, setter, toString을 자동으로 생성할 수 있다.
### Bean 중복
- @Autowired는 타입으로 빈을 조회한다. 이때 동일한 타입이 존재하면 NoUniqueBeanDefinitionException 예외가 발생한다.
- @Autowired로 주입받을 필드명을 주입받을 bean의 필드명으로 바꾸면 정상적으로 작동한다.
```JAVA
@Autowired
private final DiscountPolicy rateDiscountPolicy;
```
- @Qualifier("")로 빈 이름을 지정할 수 있다. @RequiredArgsConstructor에서는 제대로 작동하지 않는다.
- @Primary로 우선순위를 정할 수도 있다.
