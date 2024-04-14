package com.vinaylogics.controllers;

import com.vinaylogics.dto.User;
import com.vinaylogics.publishers.KafkaMessagePublisher;
import com.vinaylogics.utils.CsvReaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producer-app")
@RequiredArgsConstructor
public class EventController {

    private final KafkaMessagePublisher publisher;

    @PostMapping("/publishNew")
    public ResponseEntity<?> publishEvent(@RequestBody User user) {
        try {
//            List<User> users = CsvReaderUtils.readDataFromCsv();
//            assert users != null;
//            users.forEach(publisher::sendEvents);
            publisher.sendEvents(user);
            return ResponseEntity.ok("Message published successfully");
        } catch (Exception exception) {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
