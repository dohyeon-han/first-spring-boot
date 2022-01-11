package com.item.itemservice.web.basic;


import com.item.itemservice.domain.item.Item;
import com.item.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@RequestMapping("/basic/items")
@Controller
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository repository;

    @GetMapping
    public String items(Model model){
        List<Item> items = repository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable Long itemId) {
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = repository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item,
                       RedirectAttributes redirectAttributes){
        repository.update(itemId, item);
        redirectAttributes.addAttribute("status",true);
        return "redirect:/basic/items/{itemId}";
    }

    //controller 생성 직후 데이터가 들어가 있다. 테스트 시 미리 데이터 적용
    @PostConstruct
    public void init() {
        repository.save(new Item("itemA", 10000, 10));
        repository.save(new Item("itemB", 20000, 20));
    }
}
