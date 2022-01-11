package com.item.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository repository = new ItemRepository();

    @AfterEach
    void clear(){
        repository.clearStore();
    }


    @Test
    void item_저장(){
        Item item = new Item("A",999,10);

        repository.save(item);

        assertEquals(item,repository.findById(item.getId()));
    }

    @Test
    void item_전체(){
        Item item1 = new Item("A",999,10);
        Item item2 = new Item("B",2000,2);

        repository.save(item1);
        repository.save(item2);

        List<Item> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    void item_수정(){
        Item item = new Item("A",999,10);
        Item update = new Item("B",2000,2);

        Long id = repository.save(item).getId();
        repository.update(id, update);

        Item result = repository.findById(id);
        assertEquals(result.getItemName(),update.getItemName());
        assertEquals(result.getPrice(),update.getPrice());
        assertEquals(result.getQuantity(),update.getQuantity());
    }
}