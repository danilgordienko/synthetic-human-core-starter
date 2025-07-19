package ru.danilgordienko.synthetic_human_core_starter.CommandModule;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilgordienko.synthetic_human_core_starter.MonitoringModule.AndroidMetrics;

import java.util.concurrent.ExecutorService;

//@Service
@RequiredArgsConstructor
@Slf4j
public class CommandHandler {

    private final ExecutorService executor;
    private final AndroidMetrics androidMetrics;

    // главный метод обработки команд
    public void handleCommand(Command command) {
        if (command.getPriority().equals(CommandPriority.COMMON)) {
            executeLate(command);
        } else {
            executeImmediately(command);
        }
        // добавляем команду в метрику
        androidMetrics.incrementAuthorCommand(command.getAuthor());
    }

    // выполнение критических команд
    public void executeImmediately(Command command) {
        log.info("Critical Command {} executing", command);
    }

    // выполнение обычных команд
    private void executeLate(Command command) {
        executor.submit(() -> {
            log.info("Common Command {} executing", command);
        });
    }
}
