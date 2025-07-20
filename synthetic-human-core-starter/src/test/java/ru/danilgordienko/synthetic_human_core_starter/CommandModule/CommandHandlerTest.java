package ru.danilgordienko.synthetic_human_core_starter.CommandModule;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.danilgordienko.synthetic_human_core_starter.MonitoringModule.AndroidMetrics;

import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommandHandlerTest {

    @Mock
    private ExecutorService executor;

    @Mock
    private AndroidMetrics androidMetrics;

    @InjectMocks
    private CommandHandler commandHandler;

    @Test
    void executeCriticalCommandImmediatelyTest() {
        LogCaptor logCaptor = LogCaptor.forClass(CommandHandler.class);
        Command command = new Command("description", CommandPriority.CRITICAL, "author", "12:00");

        commandHandler.handleCommand(command);

        verify(androidMetrics).incrementAuthorCommand("author");
        assertThat(logCaptor.getInfoLogs())
                .anyMatch(msg -> msg.contains("Critical Command " + command+" executing"));
        verifyNoInteractions(executor);
    }

    @Test
    void executeCommonCommandWithExecutorTest() {
        LogCaptor logCaptor = LogCaptor.forClass(CommandHandler.class);
        Command command = new Command("description", CommandPriority.COMMON, "author", "12:00");

        when(executor.submit(any(Runnable.class))).thenAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
            return null;
        });
        commandHandler.handleCommand(command);

        verify(androidMetrics).incrementAuthorCommand("author");
        verify(executor).submit(any(Runnable.class));
        assertThat(logCaptor.getInfoLogs())
                .anyMatch(msg -> msg.contains("Common Command " + command + " executing"));
    }
}
