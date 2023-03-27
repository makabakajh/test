package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsBVehicle;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 车辆DAO接口
 * @author oqm
 * @version 2023-02-03
 */
@MyBatisDao
public interface WsVmsBVehicleDao extends CrudDao<WsVmsBVehicle> {
    List<Map<String,Object>> getLicenseByDeviceCode(@Param("deviceCodes") List<String> deviceCodes);
    List<Map<String,Object>> getVehicleList();
    List<Map<String,Object>> getVehicleCost(@Param("vehicleId") String vehicleId,
                                            @Param("date") String date);
    List<Map<String,Object>> getTotalCostByMonth(@Param("startDate") String startDate,
                                                 @Param("endDate") String endDate);
    List<Map<String,Object>> getTotalCostByQuarter(@Param("startDate") String startDate,
                                                 @Param("endDate") String endDate);
    List<Map<String,Object>> getVehicleCostByMonth(@Param("startDate") String startDate,
                                                 @Param("endDate") String endDate);
    List<Map<String,Object>> getVehicleCostByQuarter(@Param("startDate") String startDate,
                                                   @Param("endDate") String endDate);
    List<Map<String,Object>> getDeviceList();
    Map<String,Object> getYingYanMileageByVehicleId(@Param("vehicleId") String vehicleId,
                                                    @Param("date") String date);
    Map<String,String> getVehicleDriverByVehicleId(@Param("vehicleId") String vehicleId);

    Map<String,Object> getVehicleInfoById(@Param("vehicleId") String vehicleId);
}