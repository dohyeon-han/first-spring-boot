package com.item.itemservice.web.validation;

import com.item.itemservice.domain.dto.ItemApiResponse;
import com.item.itemservice.domain.item.Item;
import com.item.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("validation/api/items")
@RestController
@RequiredArgsConstructor
public class ValidationItemApiController {
    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated Item item, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ItemApiResponse(false,2,"???",item);
        }
        return new ItemApiResponse(true,1,"정상",item);
    }
}
