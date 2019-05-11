package wang.huaiyu.echo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import wang.huaiyu.echo.common.constant.TopicName;
import wang.huaiyu.echo.topic.GameInitTopicReceiver;
import wang.huaiyu.echo.topic.ServiceOfflineTopicReceiver;

import java.util.concurrent.Executor;

@EnableCaching
@Configuration
public class RedisConfigurator {

    @Autowired
    @Qualifier(value = "echoExecutor")
    private Executor executor;

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory, MessageListenerAdapter serviceOffline, MessageListenerAdapter gameInit) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setTaskExecutor(executor);
        container.addMessageListener(serviceOffline, new PatternTopic(TopicName.SERVICE_OFFLINE_TOPIC));
        container.addMessageListener(gameInit, new PatternTopic(TopicName.GAME_INIT));
        return container;
    }

    @Bean(value = "serviceOffline")
    public MessageListenerAdapter serviceOfflineTopic(ServiceOfflineTopicReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receive");
    }

    @Bean(value = "gameInit")
    public MessageListenerAdapter gameInitTopic(GameInitTopicReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receive");
    }

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }
}
