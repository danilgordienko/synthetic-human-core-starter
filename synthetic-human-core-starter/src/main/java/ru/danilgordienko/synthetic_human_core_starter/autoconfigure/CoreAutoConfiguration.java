package ru.danilgordienko.synthetic_human_core_starter.autoconfigure;


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.danilgordienko.synthetic_human_core_starter.AuditModule.AuditAspect;
import ru.danilgordienko.synthetic_human_core_starter.CommandModule.CommandHandler;
import ru.danilgordienko.synthetic_human_core_starter.ExceptionModule.ExceptionHandlerModule;
import ru.danilgordienko.synthetic_human_core_starter.MonitoringModule.AndroidMetrics;
import ru.danilgordienko.synthetic_human_core_starter.CommandModule.config.CommnadHadlerConfig;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@AutoConfiguration
@Import(CommnadHadlerConfig.class)
//@ConditionalOnClass({CommandHandler.class, AndroidMetrics.class})
public class CoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CommandHandler commandHandler(ExecutorService executorService,
                                         AndroidMetrics androidMetrics) {
        return new CommandHandler(executorService, androidMetrics);
    }

    @Bean
    @ConditionalOnMissingBean
    public AndroidMetrics androidMetrics(MeterRegistry meterRegistry,
                                         BlockingQueue<Runnable> taskQueue) {
        AndroidMetrics metrics = new AndroidMetrics(meterRegistry);
        metrics.bindQueue(taskQueue);
        return metrics;
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionHandlerModule exceptionHandlerModule() {
        return new ExceptionHandlerModule();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditAspect auditAspect() {
        return new AuditAspect();
    }
}