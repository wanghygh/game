package wang.huaiyu.echo.topic;

import org.springframework.stereotype.Service;
import wang.huaiyu.echo.common.abstraction.AbstractReceiver;

@Service
public class ServiceOfflineTopicReceiver extends AbstractReceiver {

    public void receive(String message) {
        // DO SERVER OFFLINE

    }
}
