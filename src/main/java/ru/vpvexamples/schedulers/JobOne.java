package ru.vpvexamples.schedulers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Component
@Profile(value = {"profile-job-1"})
@ConditionalOnProperty(prefix = "scheduling.", name = "on")
public class JobOne implements Runnable, SchedulingConfigurer {

    private static Logger log = Logger.getLogger(JobOne.class.getName());

    @Value(value = "${scheduling.jobs.cron_1:0/3 * * * * *}")
    private String cron;

    AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run() {
        log.info("JOBS_1: "+Integer.toString(i.getAndIncrement()));
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        log.info("Init JobOne: " + cron);
        taskRegistrar.addCronTask(this, cron);
    }

}
