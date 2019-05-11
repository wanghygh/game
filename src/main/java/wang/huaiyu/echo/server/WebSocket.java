package wang.huaiyu.echo.server;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.OnBinary;
import org.yeauty.annotation.ServerEndpoint;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;
import wang.huaiyu.echo.common.abstraction.AbstractWebSocket;
import wang.huaiyu.echo.common.data.MessageData;
import wang.huaiyu.echo.common.data.UserData;

@Component
@ServerEndpoint(prefix = "service.websocket")
public class WebSocket extends AbstractWebSocket {

    @Autowired
    @Qualifier(value = "scheduler")
    private Scheduler scheduler;

    @Override
    public void open(Session session, ParameterMap parameterMap) {
        // DO OPEN

    }

    @Override
    public void close(Session session, UserData user) {
        // DO CLOSE

    }

    @Override
    public void message(Session session, MessageData message) {
        switch (message.getType()) {
            case SINGLE: {

                break;
            }
            case ROOM: {

                break;
            }
            default:
                break;
        }
    }

    /**
     * 接收二进制消息
     *
     * @param session 会话
     * @param bytes   字节流
     */
    @OnBinary
    public void binary(Session session, byte[] bytes) {
        // DO BINARY

    }
}