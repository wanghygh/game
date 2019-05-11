package wang.huaiyu.echo.common.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReceiver.class);

    protected void print(String name, String message) {
        LOGGER.info("[{}] receive message [{}]", name, message);
    }
}
