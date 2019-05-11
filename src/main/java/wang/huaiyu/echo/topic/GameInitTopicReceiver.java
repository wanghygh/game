package wang.huaiyu.echo.topic;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import wang.huaiyu.echo.common.abstraction.AbstractReceiver;

@Service
public class GameInitTopicReceiver extends AbstractReceiver {

    @Autowired
    @Qualifier(value = "scheduler")
    private Scheduler scheduler;

    public void receive(String message) {
        // DO INIT GAME

    }
}
