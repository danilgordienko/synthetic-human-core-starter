package ru.danilgordienko.synthetic_human_core_starter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danilgordienko.synthetic_human_core_starter.CommandModule.Command;
import ru.danilgordienko.synthetic_human_core_starter.CommandModule.CommandHandler;


// контролер чтоб протестить обработку команд
@RestController
@RequiredArgsConstructor
@RequestMapping("api/commands")
@Slf4j
public class CommandHandlerController {

    private final CommandHandler commandHandler;

    @PostMapping
    public ResponseEntity<String> handleCommand(@Valid @RequestBody Command command) {
        log.info("Received command {}", command);
        commandHandler.handleCommand(command);
        log.info("Command successfully executed");
        return ResponseEntity.ok("Command executed");
    }
}
