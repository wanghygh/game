package wang.huaiyu.echo.common.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import wang.huaiyu.echo.common.constant.Delay;
import wang.huaiyu.echo.common.util.QuartzUtil;
import wang.huaiyu.echo.task.OnlineNumberJob;

import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
public class QuartzConfigurator {

    @Autowired
    @Qualifier(value = "echoExecutor")
    private Executor executor;

    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTaskExecutor(executor);
        return bean;
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    @Bean(value = "scheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactory) {
        Scheduler scheduler = schedulerFactory.getScheduler();
        QuartzUtil.add(scheduler, "onlineNumber", OnlineNumberJob.class, Delay.ONLINE, -1);
        return scheduler;
    }
}
