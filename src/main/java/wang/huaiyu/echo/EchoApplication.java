package wang.huaiyu.echo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(value = "wang.huaiyu.echo.domain.repository")
@EnableFeignClients
@SpringCloudApplication
public class EchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

}
