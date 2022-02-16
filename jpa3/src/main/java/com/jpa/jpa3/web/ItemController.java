package com.jpa.jpa3.web;

import com.jpa.jpa3.domain.item.Book;
import com.jpa.jpa3.domain.item.Item;
import com.jpa.jpa3.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = {"/home","/"})
    public String home(){
        return "home";
    }

    @GetMapping("/items/new")
    public String createForm(){
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute Book item){
        System.out.println(item.toString());
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model){
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("item") Book item){
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
