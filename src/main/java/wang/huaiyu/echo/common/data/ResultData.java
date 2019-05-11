package wang.huaiyu.echo.common.data;

import wang.huaiyu.echo.common.constant.ResultCode;

import java.io.Serializable;

/**
 * 请求结果对象
 *
 * @param <T>
 */
public class ResultData<T> implements Serializable {

    private Integer code;
    private String memo;
    private T data;

    public ResultData(ResultCode code) {
        this.code = code.getCode();
        this.memo = code.getMemo();
    }

    public ResultData(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
