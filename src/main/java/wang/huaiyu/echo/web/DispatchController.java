package wang.huaiyu.echo.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.huaiyu.echo.common.abstraction.AbstractController;
import wang.huaiyu.echo.common.abstraction.IHandler;
import wang.huaiyu.echo.common.config.SpringContextConfigurator;
import wang.huaiyu.echo.common.constant.ResultCode;
import wang.huaiyu.echo.common.data.RequestData;
import wang.huaiyu.echo.common.data.ResultData;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 分发控制器
 */
@RestController
public class DispatchController extends AbstractController {

    /**
     * 分发
     *
     * @param servletRequest servlet请求对象
     * @return 结果对象
     */
    @CrossOrigin
    @RequestMapping(value = "echo", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultData dispatch(HttpServletRequest servletRequest) {
        RequestData request = this.parse(servletRequest);
        Map<String, Object> data = request.getData();
        if (null == data || StringUtils.isEmpty(data.get("op"))) {
            return new ResultData(ResultCode.INVALID_PARAMETER);
        }
        if (StringUtils.isEmpty(data.get("accessToken"))) {
            return new ResultData(ResultCode.INVALID_TOKEN);
        }
        String op = data.get("op").toString();
        IHandler handler;
        try {
            handler = (IHandler) SpringContextConfigurator.getBean(op);
        } catch (Exception e) {
            return new ResultData(ResultCode.METHOD_NOT_FOUND);
        }
        try {
            return handler.preHandler(request);
        } catch (Exception e) {
            return new ResultData(ResultCode.FAIL);
        }
    }
}
