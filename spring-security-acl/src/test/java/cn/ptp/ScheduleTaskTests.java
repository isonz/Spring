package cn.ptp;

import cn.ptp.task.ScheduledTasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class)
public class ScheduleTaskTests
{
    @Autowired
    private ScheduledTasks task;

    @Test
    public void test() throws Exception
    {
        task.reportCurrentTime();
    }

}
