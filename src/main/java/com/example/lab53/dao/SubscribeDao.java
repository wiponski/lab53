package com.example.lab53.dao;

import com.example.lab53.model.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubscribeDao {
    private final JdbcTemplate jdbcTemplate;
    public Optional<Subscribe> findById(Long id){
        return Optional.empty();
    }
    public Optional<Subscribe> findByEmail(String email){
        return Optional.empty();
    }
    public Long create (Subscribe subscribe){
        return null;
    }
    public void delete(Long id){

    }
}
