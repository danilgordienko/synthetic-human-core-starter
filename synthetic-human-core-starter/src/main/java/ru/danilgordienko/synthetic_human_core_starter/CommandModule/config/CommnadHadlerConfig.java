package ru.danilgordienko.synthetic_human_core_starter.CommandModule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class CommnadHadlerConfig {

    @Bean
    public BlockingQueue<Runnable> taskQueue() {
        return new ArrayBlockingQueue<>(10);
    }


    @Bean
    public ExecutorService threadPoolExecutor() {
        return new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                taskQueue(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
