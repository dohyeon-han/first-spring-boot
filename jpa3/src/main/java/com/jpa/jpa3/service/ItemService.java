package com.jpa.jpa3.service;

import com.jpa.jpa3.domain.item.Item;
import com.jpa.jpa3.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.finaAll();
    }

    public Item findOne(Long id){
        return itemRepository.findById(id);
    }
}
