package wang.huaiyu.echo.common.abstraction;

import wang.huaiyu.echo.common.data.RequestData;
import wang.huaiyu.echo.common.data.ResultData;

/**
 * 处理器接口
 */
public interface IHandler {

    /**
     * 处理前
     *
     * @param request 请求对象
     * @return 结果对象
     */
    ResultData preHandler(RequestData request);
}
