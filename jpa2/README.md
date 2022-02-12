# JPAL

## JPQL

- 엔티티 객체를 조회하는 객체지향 쿼리이다.
- SQL을 추상화해서 특정 데이터베이스에 의존하지 않는다.

### 기본 문법과 쿼리 API

#### SELECT 문

- 대소문자 구분(JPQL 키워드 제외)
- 클래스 명이 아닌 엔티티 명 사용, 엔티티 명을 지정하지 않으면 클래스 명을 기본 값으로 사용
- 별칭을 필수로 사용
    ```java
    SELECT m.username FROM Member m    
    ```

#### TypeQuery, Query

- 조회 대상이 명확할 땐 TypedQuery, 명확하지 않을 땐 Query를 사용한다.
    ```java
    TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
    List resultList = query.getResultList();

    Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
    List resultList = query.getResultList();
    ```

### 파리미터 바인딩

```java
TypeQuery<Member> query=
        em.createQuery("SELECT m FROM Member m WHERE m.username = :username",Member.class);
        query.setParameter("username",usernameParam);
```

- Named parameter와 Positional parameter가 존재하는데 Named parameter 방식이 더 명확한 표현이다.

### PAGIN

```java
// 0번부터 시작
// 10번 ~ 29번까지의 데이터 조회
 Query newQuery=em.createQuery("SELECT new com.jpa.jpa2.MemberDto(m, m.username) FROM Member AS m "+
         "WHERE m.username = :username",MemberDto.class)
        .setParameter("username","user2");
        newQuery.setFirstResult(10);
        newQuery.setMaxResults(20);
```

### JPAL JOIN

```java
String query="SELECT m FROM Member JOIN m.team t WHERE t.name = :teamName"; // inner join
        String query="SELECT m FROM Member LEFT JOIN m.team t WHERE t.name; // outer join
```

- JPQL의 join은 SQL의 join과 다르게 연관 필드를 이용해 join한다.

### FETCH JOIN

- 일반 join을 사용하면 연관된 엔티티는 조회하지 않는다. 따라서 연관된 엔티티를 조회할 때마다 SQL을 한 번씩 더 사용하게 된다.
- fetch join을 사용하면 FetchType.LAZY를 설정하더라도 즉시 로딩으로 엔티티를 조회할 수 있고, 준영속 상태에서도 객체 그래프를 탐색할 수 있다.

### 경로 표현식

```java
SELECT o.member.team
        FROM Order o
        WHERE o.produnct.name='productA'and o.address.city='SEOUL'
```

- 해당 JPQL을 사용하면 3번의 join이 발생한다.
- join을 명시하지 않으면 항상 inner join이다.
- 컬렉션 값에서는 경로 탐색을 할 수 없다.
- 위처럼 묵시적 조인을 사용할 수 도 있지만 명시적으로 조인을 밝히는 것이 유지보수에 더 좋다

### 서브 쿼리

- WHERE, HAVING 절에 서브 쿼리를 사용할 수 있다.
- EXIST, ALL | ANY | SOME, IN 등을 사용할 수 있다.

## Criteria

- JPQL을 사용하도록 도와주는 빌더 클래서 API다.
- JPQL보다 안전하게 동적 쿼리를 생성할 수 있지만 직관적으로 이해하기 힘들다.
- 실무에서는 잘 안 쓰인다고 한다.

## QueryDSL
- 동적쿼리를 만들기 위해 JPQL을 사용하면 문자열로 처리를 해야하기 때문에 오류기 발생하기 쉽고, Criteria는 코드가 복잡하고 가독성이 떨어진다.
- QueryDSL는 이러한 문제를 해결해준다.
- Q타입을 이용해 쿼리문을 작성한다.
  ```java
  JPAQueryFactory query = new JPAQueryFactory(em);
  QMember member = QMember.member;
  List<Member> members = query.selectFrom(member)
          .where(member.username.like("%user%"))
          .fetch();
  ```
### 동적 쿼리
- BooleanBuilder를 사용하여 특정 조건에 따라 동적 쿼리를 생성할 수 있다.
  ```java
  BooleanBuilder builder = new BooleanBuilder();
        if(fetch2.size()!=0){
            builder.and(member.age.goe(10));
            if(fetch2.get(0).getUsername().contains("user"))
                builder.and(member.username.like("%user%"));
  }
  ```