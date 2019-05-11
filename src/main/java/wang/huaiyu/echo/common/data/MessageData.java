package wang.huaiyu.echo.common.data;

import wang.huaiyu.echo.common.constant.MessageType;

import java.io.Serializable;

public class MessageData<T> implements Serializable {

    private Long timeStamp;
    private Long fromId;
    private Long toId;
    private MessageType type;
    private T content;

    public MessageData() {
    }

    public MessageData(MessageType type, Long toId, T content) {
        this.timeStamp = System.currentTimeMillis();
        this.toId = toId;
        this.type = type;
        this.content = content;
    }

    public MessageData(MessageType type, Long fromId, Long toId, T content) {
        this.timeStamp = System.currentTimeMillis();
        this.fromId = fromId;
        this.toId = toId;
        this.type = type;
        this.content = content;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
