package com.example.lab53.service;

import com.example.lab53.dao.EventDao;
import com.example.lab53.dao.SubscribeDao;
import com.example.lab53.dto.EventDto;
import com.example.lab53.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateService {
    private final DBConnectService dbService;
    private final EventDao eventDaoDao;
    private final SubscribeDao subscribeDao;

    private int executeUpdate(String query) throws SQLException {
        Statement statement = dbService.getConn().createStatement();
        return statement.executeUpdate(query);
    }

    private void createEventTable() throws SQLException {
        String dropTableQuery = " DROP TABLE IF EXISTS events;"
                + " DROP TABLE IF EXISTS subscribes;"
               ;
        String createTableQuery = "CREATE TABLE events ("
                + "id bigserial PRIMARY KEY,"
                + "name VARCHAR, "
                + "date_time timestamp "

                + ");";
        String clientEntryQuery = "";
        executeUpdate(dropTableQuery);
        executeUpdate(createTableQuery);
        executeUpdate(clientEntryQuery);
    }

    private void createSubscribeTable() throws SQLException {

        String createTableQuery = "CREATE TABLE subscribes ("
                + "id bigserial PRIMARY KEY,"
                + "email VARCHAR, "
                + "date_time timestamp "
                + "event_id bigint NOT NULL, "
                + "CONSTRAINT event_FK FOREIGN KEY(event_id) REFERENCES events (id) ON DELETE CASCADE,"
                + ");";
        String clientEntryQuery = "";
        executeUpdate(createTableQuery);
        executeUpdate(clientEntryQuery);
    }
}
