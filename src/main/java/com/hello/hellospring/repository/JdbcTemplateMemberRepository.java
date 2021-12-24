package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateMemberRepository implements  MemberRepository{

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);

    public JdbcTemplateMemberRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String,Object> map = new HashMap<>();
        map.put("name", member.getName());
        Number key = insert.executeAndReturnKey(map);

        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?"
                ,rowMapper,id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?"
                ,rowMapper,name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", rowMapper);
    }

//    private RowMapper<Member> memberRowMapper(){
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setName(rs.getString("name"));
//            return member;
//        };
//    }
}
