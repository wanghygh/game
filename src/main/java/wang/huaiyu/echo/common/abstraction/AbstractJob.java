package wang.huaiyu.echo.common.abstraction;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

public abstract class AbstractJob implements Job {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractJob.class);

    protected void print(String name, String... params) {
        if (0 < params.length) {
            Optional<String> context = Arrays.stream(params).reduce((a, b) -> a + " " + b);
            LOGGER.info("[{}] job execute [{}]", name, context.get());
        }
    }
}
