package com.item.itemservice.web.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.itemservice.domain.item.Item;
import com.item.itemservice.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class BasicItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository repository;

    @AfterEach
    void clear() {
        repository.clearStore();
    }

    @Test
    void item_입력_post() throws Exception {
        Item item = new Item("C", 500, 3);
        mockMvc.perform(post("/basic/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void item_수정_post() throws Exception {
        Item item = new Item("C", 500, 3);
        Item editItem = new Item("D", 1000, 6);
        repository.save(item);
        mockMvc.perform(post("/basic/items/3/edit").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(editItem)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}