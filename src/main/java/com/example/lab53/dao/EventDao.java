package com.example.lab53.dao;

import com.example.lab53.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<Event> findById(Long id){
        String sql = "select * from event where id =?;";
        return Optional.of(jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Event.class),id));
    }

    public List<Event> getAllEvents(){
        String sql = "select * from event;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class));
    }

    public Long create(Event event){
        String sql = "insert into (date_time, name, description) values (?, ?, ?); ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setTimestamp(1, Timestamp.valueOf(event.getDateTime()));
            ps.setString(2,event.getName());
            ps.setString(3, event.getDescription());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }
    public void delete(Long id){
        String sql = "delete from event id = ?;";
        jdbcTemplate.update(sql,id);
    }

    public List<Event> findBySubscribeId(String email){
        String sql = "select e.* "
                +"from event e"
                +"subscribe s"
                +"where s.email=?"
                +"and e.id = s.event_id;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class),email);
    }

}
