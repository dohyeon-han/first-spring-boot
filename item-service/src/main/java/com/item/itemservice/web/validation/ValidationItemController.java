package com.item.itemservice.web.validation;


import com.item.itemservice.domain.item.Item;
import com.item.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequestMapping("/validation/items")
@Controller
@RequiredArgsConstructor
public class ValidationItemController {
    private final ItemRepository repository;
    private final ItemValidator itemValidator;

    @GetMapping
    public String items(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items", items);
        return "validation/items";
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable Long itemId) {
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/addForm";
    }

//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes, Model model) {
//        // 검증 오류 보관
//        Map<String, String> errors = new HashMap<>();
//
//        // 검증 로직
//        if (!StringUtils.hasText(item.getItemName())) {
//            errors.put("itemName", "상품 이름은 필수입니다.");
//        }
//        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
//            errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다");
//        }
//        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
//            errors.put("quantity", "수량은 최대 9,9999 까지 허용합니다.");
//        }
//
//        // 특정 필드가 아닌 복합 룰 검증
//        if (item.getPrice() != null && item.getQuantity() != null) {
//            int resultPrice = item.getPrice() * item.getQuantity();
//            if (resultPrice < 10000) {
//                errors.put("global", "가격 * 수량의 합은 10,000 이상이어야 합니다. 현재 값 = " + resultPrice);
//            }
//        }
//
//        // 검증에 실패하면 다시 입력 폼으로
//        if (!errors.isEmpty()) {
//            model.addAttribute("errors", errors);
//            return "validation/addForm";
//        }
//
//        // 성공 로직
//        Item savedItem = repository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/items/{itemId}";
//    }

    //BindingResult 사용, Map 생성을 하지 않아도 되며, 자동으로 model에 저장
    //@Validated로 검증 (ItemValidator.class)
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//    //검증 로직
//    if(!StringUtils.hasText(item.getItemName())) {
//        //bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
//        bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
//    }
//    if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
//        //bindingResult.addError(new FieldError("item", "price", "가격은 1,000원 ~ 1,000,000 까지 허용합니다."));
//        bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000 ~ 1,000,000 까지 허용합니다."));
//    }
//    if (item.getQuantity() == null || item.getQuantity() >= 10000) {
//        //bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999까지 허용합니다."));
//        bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(), false, null, null, "수량은 최대 9,9999 까지 허용합니다."));
//    }
//
//    if (item.getPrice() != null && item.getQuantity() != null) {
//        int totalPrice = item.getQuantity() * item.getPrice();
//        if (totalPrice < 10000) {
//            //bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이여야 합니다. 현재 값 = " + totalPrice));
//            bindingResult.addError(new ObjectError("item",null, null, "가격 * 수량의 합은 10,000 이상이어야 합니다. 현재 값 = " + totalPrice));
//        }
//    }
        log.info("bindingResult ={}", bindingResult);

        //글로벌 검증
        if (validatePriceMultiQuantity(item, bindingResult)) return "validation/addForm";

        //검증 통과, 상품 저장
        Item savedItem = repository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute Item item,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //글로벌 검증
        if (validatePriceMultiQuantity(item, bindingResult)) return "validation/editForm";

        repository.update(itemId, item);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

    private boolean validatePriceMultiQuantity(@ModelAttribute @Validated Item item, BindingResult bindingResult) {
        if (item.getPrice() != null && item.getQuantity() != null) {
            int totalPrice = item.getQuantity() * item.getPrice();
            if (totalPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, totalPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) return true;
        return false;
    }
}
