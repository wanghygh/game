package wang.huaiyu.echo.common.data;

import java.io.Serializable;

public class UserData implements Serializable {

    private Long userId;
    private String nickname;
    private String avatar;
    private String sex;

    public UserData() {
    }

    public UserData(Long userId) {
        this.userId = userId;
    }

    public UserData(Long userId, String nickname, String avatar, String sex) {
        this.userId = userId;
        this.nickname = nickname;
        this.avatar = avatar;
        this.sex = sex;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
