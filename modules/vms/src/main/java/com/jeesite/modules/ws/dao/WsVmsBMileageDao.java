package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsBMileage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 里程表DAO接口
 * @author oqm
 * @version 2023-02-13
 */
@MyBatisDao
public interface WsVmsBMileageDao extends CrudDao<WsVmsBMileage> {
	List<WsVmsBMileage> getVehicleIdAndDate(@Param("vehicleId") String vehicleId,
                                            @Param("date") String date);
}