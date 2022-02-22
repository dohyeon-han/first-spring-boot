package com.jpa.jpa3.domain.springDataRepository;

import com.jpa.jpa3.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}