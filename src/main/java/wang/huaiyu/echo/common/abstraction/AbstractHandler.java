package wang.huaiyu.echo.common.abstraction;

import wang.huaiyu.echo.common.constant.ResultCode;
import wang.huaiyu.echo.common.data.RequestData;
import wang.huaiyu.echo.common.data.ResultData;

/**
 * 处理器抽象
 */
public abstract class AbstractHandler implements IHandler {

    @Override
    public ResultData preHandler(RequestData request) {
        if (!this.checkParam(request)) {
            return new ResultData(ResultCode.INVALID_PARAMETER);
        }
        if (!this.checkToken(request)) {
            return new ResultData(ResultCode.INVALID_TOKEN);
        }
        return this.doHandler(request);
    }

    /**
     * 校验参数
     *
     * @param request 请求对象
     * @return 是否正确
     */
    protected abstract boolean checkParam(RequestData request);

    /**
     * 校验令牌
     *
     * @param request 请求对象
     * @return 是否正确
     */
    protected boolean checkToken(RequestData request) {
        // DO VERIFY TOKEN

        return true;
    }

    /**
     * 处理
     *
     * @param request 请求对象
     * @return 结果对象
     */
    protected abstract ResultData doHandler(RequestData request);
}
