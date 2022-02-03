package com.jpa.jpashop.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
}
