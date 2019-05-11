package wang.huaiyu.echo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfigurator {

    /**
     * 线程池大小
     */
    private static final int corePoolSize = 256;

    /**
     * 线程池最大线程数
     */
    private static final int maxPoolSize = 512;

    /**
     * 线程队列容量(当线程池达到最大值时任务放入队列)
     */
    private static final int queueCapacity = 256;

    /**
     * 线程空闲时存活时长
     */
    private static final int keepAlive = 60;

    /**
     * 初始化线程池
     *
     * @return
     */
    @Bean(value = "echoExecutor")
    public Executor init() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.setThreadNamePrefix("echo-");
        executor.initialize();
        return executor;
    }
}
