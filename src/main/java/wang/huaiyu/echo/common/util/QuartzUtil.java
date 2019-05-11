package wang.huaiyu.echo.common.util;

import org.quartz.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuartzUtil {

    /**
     * 添加任务
     *
     * @param scheduler 调度工厂
     * @param name      名称
     * @param clazz     类
     * @param delay     延时
     */
    public static void add(Scheduler scheduler, String name, Class<? extends Job> clazz, Integer delay) {
        add(scheduler, name, clazz, delay, 1);
    }

    /**
     * 添加任务
     *
     * @param scheduler 调度工厂
     * @param name      名称
     * @param clazz     类
     * @param delay     延时
     * @param repeat    重复
     */
    public static void add(Scheduler scheduler, String name, Class<? extends Job> clazz, Integer delay, Integer repeat) {
        add(scheduler, name, clazz, delay, repeat, new HashMap<>(1));
    }

    /**
     * 添加任务
     *
     * @param scheduler 调度工厂
     * @param name      名称
     * @param clazz     类
     * @param delay     延时
     * @param dataMap   数据
     */
    public static void add(Scheduler scheduler, String name, Class<? extends Job> clazz, Integer delay, Map<String, Object> dataMap) {
        add(scheduler, name, clazz, delay, 1, dataMap);
    }

    /**
     * 添加任务
     *
     * @param scheduler 调度工厂
     * @param name      名称
     * @param clazz     类
     * @param delay     延时
     * @param repeat    重复
     * @param dataMap   数据
     */
    public static void add(Scheduler scheduler, String name, Class<? extends Job> clazz, Integer delay, Integer repeat, Map<String, Object> dataMap) {
        try {
            if (scheduler.checkExists(new JobKey(name + "Job"))) {
                // 如果该任务已存在
                return;
            }
            // 数据
            JobDataMap map = new JobDataMap(dataMap);
            // 任务
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(name + "Job")
                    .storeDurably()
                    .usingJobData(map)
                    .build();
            // 触发器
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInMilliseconds(delay);
            if (-1 == repeat) {
                scheduleBuilder.repeatForever();
            } else {
                scheduleBuilder.withRepeatCount(repeat);
            }
            SimpleTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(name + "Trigger")
                    .withSchedule(scheduleBuilder)
                    .startAt(new Date(System.currentTimeMillis() + delay))
                    .build();
            // 注册
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除任务
     *
     * @param scheduler 调度工厂
     * @param name      名称
     */
    public static void remove(Scheduler scheduler, String name) {
        try {
            JobKey jobKey = new JobKey(name + "Job");
            if (!scheduler.checkExists(jobKey)) {
                // 如果该任务不存在
                return;
            }
            TriggerKey triggerKey = new TriggerKey(name + "Trigger");
            scheduler.pauseJob(jobKey);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
