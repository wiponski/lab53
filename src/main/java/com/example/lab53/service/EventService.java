package com.example.lab53.service;

import com.example.lab53.dao.EventDao;
import com.example.lab53.dto.EventDto;
import com.example.lab53.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventDao eventDao;

    private List<EventDto> getAll(){
      return   eventDao.getAllEvents().stream()
                .map(event -> EventDto.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .description(event.getDescription())
                        .dateTime(event.getDateTime())
                        .build())
                        .collect(Collectors.toList());

    }

//    @After
//    private LocalDateTime timeOfSubscribe(){
//
//    }

}
