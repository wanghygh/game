package wang.huaiyu.echo.common.abstraction;

import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;
import wang.huaiyu.echo.common.data.UserData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * web socket 接口
 */
public interface IWebSocket {

    /**
     * 在线人数
     */
    AtomicInteger ONLINE_NUMBER = new AtomicInteger(0);

    /**
     * 当前连接对象
     */
    Map<String, IWebSocket> WEB_SOCKET_MAP = new ConcurrentHashMap<>();

    /**
     * 打开连接前
     *
     * @param session      会话
     * @param parameterMap 参数集合
     */
    void preOpen(Session session, ParameterMap parameterMap);

    /**
     * 关闭连接前
     *
     * @param session 会话
     */
    void preClose(Session session);

    /**
     * 接收消息前
     *
     * @param session 会话
     * @param message 消息
     */
    void preMessage(Session session, String message);

    /**
     * 连接用户对象
     *
     * @return 用户对象
     */
    UserData user();

    /**
     * 会话
     *
     * @return 会话
     */
    Session session();
}
