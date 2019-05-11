package wang.huaiyu.echo.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import wang.huaiyu.echo.common.abstraction.AbstractJob;
import wang.huaiyu.echo.common.abstraction.IWebSocket;

public class OnlineNumberJob extends AbstractJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int onlineNumber = IWebSocket.ONLINE_NUMBER.get();
        this.print("onlineNumber", "current online number", String.valueOf(onlineNumber));
    }
}
