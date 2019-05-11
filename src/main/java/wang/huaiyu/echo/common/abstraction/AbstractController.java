package wang.huaiyu.echo.common.abstraction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import wang.huaiyu.echo.common.data.RequestData;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractController {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    /**
     * 解析参数
     *
     * @param servletRequest servlet请求对象
     * @return 请求对象
     */
    protected RequestData parse(HttpServletRequest servletRequest) {
        RequestData request = new RequestData();
        String data = servletRequest.getParameter("data");
        if (!StringUtils.isEmpty(data)) {
            request.setData(JSON.parseObject(data, new TypeReference<Map<String, Object>>() {
            }));
        }
        return request;
    }
}
