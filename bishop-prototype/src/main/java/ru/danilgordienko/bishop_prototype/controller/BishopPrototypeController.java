package ru.danilgordienko.bishop_prototype.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilgordienko.bishop_prototype.service.BishopPrototypeService;
import ru.danilgordienko.synthetic_human_core_starter.CommandModule.Command;
import ru.danilgordienko.synthetic_human_core_starter.CommandModule.CommandHandler;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/commands")
public class BishopPrototypeController {

    private final CommandHandler commandHandler;
    private final BishopPrototypeService bishopPrototypeService;

    // эндпоинт для теста командного модуля
    @PostMapping
    public ResponseEntity<String> handleCommand(@Valid @RequestBody Command command) {
        log.info("Received command {}", command);
        commandHandler.handleCommand(command);
        log.info("Command successfully executed");
        return ResponseEntity.ok("Command executed");
    }

    //эндпоинт для теста аудита
    @GetMapping
    public ResponseEntity<String> test() {
        bishopPrototypeService.test("Test");
        return ResponseEntity.ok("");
    }
}
