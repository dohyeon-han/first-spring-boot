# 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술
https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-2

## 검증
- 프론트에서 값을 검증한다 해도 취약점이 존재하기 때문에 백엔드에서의 검증은 필수적이다.
### BindingResult
- @ModelAttribute의 바인딩에 문제가 발생하면 FieldError를 생성해 BindingResult에 넣는다.
### FieldError, ObjectError
- BindingResult에 해당 객체를 추가하여 오류메세지를 발생시킬 수 있다.
```JAVA
bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
```
### rejectValue(), reject()
- FieldError 대신 rejectValue를, ObjectError 대신 reject를 사용할 수 있다.
```JAVA
errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
```
### Bean Validation
- null, 빈칸, 특정 범위 등의 조건은 간단히 어노테이션으로 조작할 수 있다.
```JAVA
public class Item {
    ..
    ..
    @NotBlank
    private String itemName;
    
    @NotNull // integer는 @NotBlank 불가
    @Range(min = 1000, max = 1000000)
    private Integer price;
    
    @NotNull
    @Max(9999)
    private Integer quantity;
    ..
    ..
```
- Bean Validation은 여러 요소가 합쳐져 나타난 오류에 대해서는 처리하기가 어렵다.
- 이 경우 직접 오류를 추가해야 한다.

## Cookie, Session
- 쿠키는 클라이언트에 정보를 저장하기 때문에 비밀번호 같은 중요한 정보를 저장하는 것은 위험하다.
- 세션의 경우는 sessionId를 쿠키에 저장하고 필요한 정보는 세션에 저장한다.
- 조금 더 안전한 방법이기는 하나 사용자가 많을 경우 로드 밸런싱으로 인한 세션 불일치, 과부하 등의 문제가 있다.
- 세션은 직접 구현할 수도 있지만 서블릿에서 HttpSession 기능을 가지고 있으므로 이를 이용한는 것이 더 좋다.
```JAVA
public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model){
    ..
}
```
- @SessionAttribute를 이용해 바로 세션의 값을 얻을 수도 있다.

## Servlet Filter
- 로그인이 되지 않은 경우 특정 페이지의 접근을 막아야한다.
- 이를 컨트롤러나 aop를 통해 적용할 수도 있지만 공통된 기능은 필터를 작성하는 것이 편하다.
### 필터 적용 순서
- HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러
### doFilter
- 필터링 설정한 서블릿을 실행할 때마다 호출되는 메소드로서 실제 필터링 기능을 구현하는 메소드이다.
- 해당 메서드 내부에 try-catch를 사용하고 내부에 chain.doFilter()를 호출하여야 다음 서블릿 혹은 필터를 호출할 수 있다.
```JAVA
public void doFilter(...){
    try{
        log.info("REQUEST [{}][{}]", uuid, requestURI);
        chain.doFilter(request,response);
    }catch (Exception e){
        throw e;
    }finally {
        log.info("RESPONSE [{}][{}]", uuid, requestURI);
    }
```
- 필터는 Bean으로 등록해야 한다.
- httpResponse.sendRedirect(url)를 통해 해당 url로 이동시킬 수 있다.

## Interceptor
- preHandler, postHandler, afterCompletion로 구성된다.
- preHandler -> controller -> postHandler -> afterCompletion 순으로 실행된다.
- afterCompletion은 항상 호출되며 ex로 예외를 받아 출력할 수 있다.
![image](https://user-images.githubusercontent.com/63232876/149337016-2a0cacbe-9f68-45ca-92e2-dc9e31b8a6f3.png)

출처:https://www.boostcourse.org/web316/lecture/254347?isDesc=false
- addInterceptors()를 override하여 등록한다.

## ArgumentResolver
- 어노테이션을 만들어 로그인된 세션인지 확인할 수 있다.
- 기존의 @SessionAttribute를 대체할 수 있다.
### supportsParameter(), resolveArgument()
- ArgumentResolver는 supportsParameter, resolveArgument메서드를 구현해야 한다.
- supportsParameter()로 어노테이션이 설정한 어노테이션인지 확인한다.
- return값이 true이면 resolveArgument()를 수행한다.
- resolveArgument()는 Object타입의 값을 return하는데 그 값이 어노테이션의 인자값이 된다.

