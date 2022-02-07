package com.jpa.type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS_ENTITY")
public class AddressEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Embedded
    Address address;

    public AddressEntity(String city, String street) {
        this.address = new Address(city, street);
    }
}
