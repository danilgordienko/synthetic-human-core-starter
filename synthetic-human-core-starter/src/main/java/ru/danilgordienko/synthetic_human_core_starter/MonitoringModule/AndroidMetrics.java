package ru.danilgordienko.synthetic_human_core_starter.MonitoringModule;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class AndroidMetrics {

    private final MeterRegistry registry;
    private final Map<String, Counter> counters = new ConcurrentHashMap<>();

    // биндим очередь, загруженность которой будем отслеживать
    public void bindQueue(BlockingQueue<?> queue) {
        Gauge.builder("android.task.queue.size", queue, BlockingQueue::size)
                .description("Current number of commands in queue")
                .register(registry);
    }

    // распеределение задач по авторам
    public void incrementAuthorCommand(String author) {
        counters.computeIfAbsent(author, a -> Counter.builder("android.commands.executed")
                        .description("Commands executed per author")
                        .tag("author", a)
                        .register(registry))
                .increment();
    }
}
