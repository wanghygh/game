package wang.huaiyu.echo.common.abstraction;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import org.yeauty.annotation.OnClose;
import org.yeauty.annotation.OnMessage;
import org.yeauty.annotation.OnOpen;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;
import wang.huaiyu.echo.common.constant.MessageType;
import wang.huaiyu.echo.common.constant.ResultCode;
import wang.huaiyu.echo.common.data.MessageData;
import wang.huaiyu.echo.common.data.UserData;

/**
 * web socket 抽象
 */
public abstract class AbstractWebSocket implements IWebSocket {

    private Session session;
    private UserData user;

    @OnOpen
    @Override
    public void preOpen(Session session, ParameterMap parameterMap) {
        ONLINE_NUMBER.incrementAndGet();
        if (!this.checkParam(parameterMap)) {
            session.sendText(JSON.toJSONString(new MessageData<>(MessageType.SINGLE, null, ResultCode.INVALID_PARAMETER.getMemo())));
            session.close();
            return;
        }
        if (!this.checkToken(parameterMap.getParameter("accessToken"))) {
            session.sendText(JSON.toJSONString(new MessageData<>(MessageType.SINGLE, null, ResultCode.INVALID_TOKEN.getMemo())));
            session.close();
            return;
        }
        this.session = session;
        this.WEB_SOCKET_MAP.put(this.session.id().asLongText(), this);
        this.open(session, parameterMap);
    }

    /**
     * 校验参数
     *
     * @param parameterMap 参数集合
     * @return 参数是否正确
     */
    private boolean checkParam(ParameterMap parameterMap) {
        // DO VERIFY PARAMETER

        return true;
    }

    /**
     * 校验令牌
     *
     * @param token 令牌
     * @return 令牌是否有效
     */
    private boolean checkToken(String token) {
        // DO VERIFY TOKEN AND GET USER INFO

        this.user = new UserData();

        return true;
    }

    /**
     * 打开连接
     *
     * @param session      会话
     * @param parameterMap 参数集合
     */
    protected abstract void open(Session session, ParameterMap parameterMap);

    @OnClose
    @Override
    public void preClose(Session session) {
        ONLINE_NUMBER.decrementAndGet();
        if (WEB_SOCKET_MAP.containsKey(session.id().asLongText())) {
            IWebSocket server = WEB_SOCKET_MAP.get(session.id().asLongText());
            WEB_SOCKET_MAP.remove(session.id().asLongText());
            this.close(session, server.user());
        }
    }

    /**
     * 关闭连接
     *
     * @param session 会话
     * @param user    用户对象
     */
    protected abstract void close(Session session, UserData user);

    @OnMessage
    @Override
    public void preMessage(Session session, String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        MessageData data = JSON.parseObject(message, MessageData.class);
        IWebSocket server = this.WEB_SOCKET_MAP.get(session.id().asLongText());
        data.setFromId(server.user().getUserId());
        this.message(session, data);
    }

    /**
     * 接收消息
     *
     * @param session 会话
     * @param message 消息
     */
    protected abstract void message(Session session, MessageData message);

    @Override
    public UserData user() {
        return this.user;
    }

    @Override
    public Session session() {
        return this.session;
    }
}
