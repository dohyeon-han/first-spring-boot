package com.item.itemservice.web.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ValidationItemApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void addItem() throws Exception {
        //given
        Item item1 = new Item("a", null, 10);
        Item item2 = new Item("a", 10000, 10);

        mockMvc.perform(post("http://localhost:8080/validation/api/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item1)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

        mockMvc.perform(post("http://localhost:8080/validation/api/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}