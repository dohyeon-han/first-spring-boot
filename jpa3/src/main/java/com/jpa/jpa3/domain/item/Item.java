package com.jpa.jpa3.domain.item;

import com.jpa.jpa3.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    @Builder.Default
    private int quantity = 0;

    @ManyToMany(mappedBy = "items")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity){
        this.quantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.quantity - quantity;
        if(restStock<0)
            throw new IllegalArgumentException("상품이 부족합니다.");
        this.quantity = restStock;
    }
}
