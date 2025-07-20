package ru.danilgordienko.synthetic_human_core_starter.AuditModule;


import lombok.RequiredArgsConstructor;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.danilgordienko.synthetic_human_core_starter.service.TestService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuditAspectTest {

    @Autowired
    TestService testService;


    @Test
    void aspectLogMessageTest() {
        LogCaptor logCaptor = LogCaptor.forClass(AuditAspect.class);

        testService.greet("Test");

        assertThat(logCaptor.getInfoLogs())
                .anyMatch(msg -> msg.contains("method: greet") &&
                        msg.contains("args: [Test]") &&
                        msg.contains("result: Hello, Test"));
    }
}
