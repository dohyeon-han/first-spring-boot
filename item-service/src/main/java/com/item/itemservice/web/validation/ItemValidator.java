package com.item.itemservice.web.validation;

import com.item.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    //클래스 검증
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    //target 검증
    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        //검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required", null, null);
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 10000) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }
        if (item.getPrice() != null && item.getQuantity() != null) {
            int totalPrice = item.getQuantity() * item.getPrice();
            if (totalPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, totalPrice}, null);
            }
        }
    }
}
