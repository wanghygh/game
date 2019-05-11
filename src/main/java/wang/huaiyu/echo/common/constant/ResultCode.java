package wang.huaiyu.echo.common.constant;

public enum ResultCode {

    SUCCESS(200, null),
    FAIL(500, "系统异常"),

    INVALID_TOKEN(10001, "无效的令牌"),
    INVALID_PARAMETER(10002, "无效的参数"),

    METHOD_NOT_FOUND(10011, "方法不存在");

    private int code;
    private String memo;

    ResultCode(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public String getMemo() {
        return memo;
    }
}
