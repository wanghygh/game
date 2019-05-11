package wang.huaiyu.echo.common.data;

import java.io.Serializable;
import java.util.Map;

/**
 * 请求对象
 */
public class RequestData implements Serializable {

    private UserData user;
    private Map<String, Object> data;

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
