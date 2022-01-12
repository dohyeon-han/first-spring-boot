package com.item.itemservice.domain.dto;

import com.item.itemservice.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemApiResponse {
    Boolean isSuccess;
    int code;
    String message;
    Item result;
}
