package com.jeesite.modules.ws.timer.task;

import com.jeesite.modules.ws.timer.service.YingYanService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName YingYanTask
 * @Author oqm
 * @Date 2023/3/9 14:46
 * @Description TODO
 * @Version 1.0
 */
@Component
public class YingYanTask {
    @Resource
    private YingYanService yingYanService;

    @Scheduled(cron = "0 0 2 * * ?")
//    @Scheduled(initialDelay = 10000, fixedDelay = 20000000)
    public void getDistance(){
        yingYanService.getVehicleDistance();
    }
}
