package com.jpa.jpa1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@SequenceGenerator(
        name="SEQ_GENERATOR",
        sequenceName = "SEQ",
        initialValue = 1,
        allocationSize = 10
)
@Entity(name = "jpa1_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GENERATOR")
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.ORDINAL)
    private Role roleTest;
}
