package com.jeesite.modules.ws.timer.service;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.http.HttpClientUtils;
import com.jeesite.modules.ws.entity.WsVmsBMileage;
import com.jeesite.modules.ws.service.WsVmsBMileageService;
import com.jeesite.modules.ws.service.WsVmsBVehicleService;
import com.jeesite.modules.ws.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName YingYanService
 * @Author oqm
 * @Date 2023/3/9 14:49
 * @Description TODO
 * @Version 1.0
 */
@Service
public class YingYanService {
    private static String ak="ZPKvPKzrn9bE3kvGGkGEQiNGVSwtMARX";
    private static String service_id="235065";
    @Resource
    private WsVmsBVehicleService wsVmsBVehicleService;
    @Resource
    private WsVmsBMileageService wsVmsBMileageService;
    /*
     * @Author oqm
     * @Date 2023/3/9
     * @Param
     * @return
     * @Description 百度鹰眼前一天里程查询
     **/
    public void getVehicleDistance(){
        String baseUrl="https://yingyan.baidu.com/api/v3/track/getdistance";
        Date time = new Date(); // 获取当前时间
        String yesterday = DateUtils.getBeforeDate(time, -1);
        String startDate=yesterday+" 00:00:00";
        String startTime = DateUtils.dateToStamp(startDate);
        String endDate=yesterday+" 23:59:59";
        String endTime=DateUtils.dateToStamp(endDate);
        List<Map<String, Object>> deviceList = getDeviceList();
        for (int i = 0; i <deviceList.size() ; i++) {
            Map<String, Object> map = deviceList.get(i);
            String deviceCode = (String)map.get("deviceCode");
            String vehicleId = (String)map.get("vehicleId");
            List<WsVmsBMileage> mileageList = wsVmsBMileageService.getByVehicleIdAndDate(vehicleId, yesterday);
            if(mileageList.size()==0){
                String params="?ak="+ak+"&service_id="+service_id+"&entity_name="+deviceCode+"&start_time="+startTime+"&end_time="+endTime;
                String url=baseUrl+params;
                String result = HttpClientUtils.get(url);
                JSONObject jsonObject = JSONObject.parseObject(result);
                String status = jsonObject.getString("status");
                if("0".equals(status)){
                    Double distance = jsonObject.getDouble("distance")/1000;
                    BigDecimal dis = new BigDecimal(distance);
                    double dist = dis.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    WsVmsBMileage wsVmsBMileage = new WsVmsBMileage();
                    wsVmsBMileage.setMileage(dist);
                    wsVmsBMileage.setVehicleId(vehicleId);
                    wsVmsBMileage.setMileageType("1");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        wsVmsBMileage.setDate(sdf.parse(yesterday));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    wsVmsBMileageService.save(wsVmsBMileage);
                }
            }
        }
    }

    private List<Map<String, Object>>  getDeviceList(){
        return wsVmsBVehicleService.getDeviceList();
    }
}
