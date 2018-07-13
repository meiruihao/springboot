package com.mrh.cheers.config.quartz;


import com.mrh.cheers.quartz.ScheduledTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时器配置
 */
//@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(ScheduledTask.class).withIdentity("scheduledTask").storeDurably().build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
                .withIdentity("uploadTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
