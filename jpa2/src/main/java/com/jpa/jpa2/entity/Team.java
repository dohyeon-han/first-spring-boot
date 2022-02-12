package com.jpa.jpa2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String teamName;

    @Column(name = "member_id")
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members;

    public void addMember(Member member) {
        if(members == null) members = new ArrayList<>();
        members.add(member);
        member.setTeam(this);
    }
}
