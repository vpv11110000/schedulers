package ru.vpvexamples.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@ConfigurationProperties(prefix = "vpvexamples.schedulers.scheduling")
public class SchedulingConfig implements SchedulingConfigurer {

    @Value(value = "${threadpooltasks_cheduler_size:1}")
    private Integer threadPoolTasksSchedulerSize;

    @Autowired
    Environment env;

    @Bean()
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler rv = new ThreadPoolTaskScheduler();
        rv.setPoolSize(threadPoolTasksSchedulerSize);
        rv.initialize();
        return rv;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
    }
}
