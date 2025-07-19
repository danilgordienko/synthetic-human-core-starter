package ru.danilgordienko.synthetic_human_core_starter.MonitoringModule;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
@RequiredArgsConstructor
public class MetricsInitializer {

    private final AndroidMetrics androidMetrics;
    private final BlockingQueue<Runnable> taskQueue;

    // биндим нашу очередь из ThreadPoolExecutor
    @PostConstruct
    public void init() {
        androidMetrics.bindQueue(taskQueue);
    }
}