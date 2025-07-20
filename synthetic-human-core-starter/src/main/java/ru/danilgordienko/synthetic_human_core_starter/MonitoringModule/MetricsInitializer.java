package ru.danilgordienko.synthetic_human_core_starter.MonitoringModule;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
@RequiredArgsConstructor
public class MetricsInitializer {

    private final AndroidMetrics androidMetrics;
    private final BlockingQueue<Runnable> taskQueue;
    private final MeterRegistry registry;

    // инициализация метрик
    @PostConstruct
    public void init() {
        // биндим нашу очередь из ThreadPoolExecutor
        androidMetrics.bindQueue(taskQueue);
        // создаем фиктивного автора
        Counter.builder("android.commands.executed")
                .description("Commands executed per author")
                .tag("author", "unknown")
                .register(registry);
    }
}