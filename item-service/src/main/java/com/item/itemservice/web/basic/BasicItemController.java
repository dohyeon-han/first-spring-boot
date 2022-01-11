package com.item.itemservice.web.basic;


import com.item.itemservice.domain.item.Item;
import com.item.itemservice.domain.item.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@RequestMapping("/basic/items")
@Controller
@RequiredArgsConstructor
public class BasicItemController {
    private ItemRepository repository;

    @GetMapping
    public String items(Model model){
        List<Item> items = repository.findAll();
        model.addAllAttributes(items);
        return "basic/items";
    }

    //controller 생성 직후 데이터가 들어가 있다. 테스트 시 미리 데이터 적용
    @PostConstruct
    public void init() {
        repository.save(new Item("itemA", 10000, 10));
        repository.save(new Item("itemB", 20000, 20));
    }

}
