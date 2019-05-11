package wang.huaiyu.echo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfigurator {

    @Bean
    public ServerEndpointExporter exporter() {
        return new ServerEndpointExporter();
    }
}
