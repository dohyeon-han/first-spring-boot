package com.jpa.cascade;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Parent parent;
}
