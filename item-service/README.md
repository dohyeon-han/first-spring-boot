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
