package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsRVehicleDrives;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆司机关联表DAO接口
 * @author oqm
 * @version 2023-02-03
 */
@MyBatisDao
public interface WsVmsRVehicleDrivesDao extends CrudDao<WsVmsRVehicleDrives> {
    List<Map<String,Object>> getMainDriverByVehicleId(@Param("vehicleId") String vehicleId);
    List<String> getDriverByDrivers(@Param("vehicleId") String vehicleId,
                                    @Param("driverList") List<String> driverList);
    String getDriverByMainDriverId(String mainDriverId);
}