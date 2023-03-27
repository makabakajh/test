package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMOil;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 加油记录DAO接口
 * @author oqm
 * @version 2023-02-06
 */
@MyBatisDao
public interface WsVmsMOilDao extends CrudDao<WsVmsMOil> {
    List<WsVmsMOil> findByVehicleId(@Param("vehicleId") String vehicleId);
    Map<String,Object> getAmountByVehicleId(@Param("vehicleId")String vehicleId);
}