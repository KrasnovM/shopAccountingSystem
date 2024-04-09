package ru.krasnovm.shopAccountingSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public ResponseEntity<String> sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return new ResponseEntity<>(
                String.format("Hello %s!", name),
                HttpStatus.OK
        );
    }
}
