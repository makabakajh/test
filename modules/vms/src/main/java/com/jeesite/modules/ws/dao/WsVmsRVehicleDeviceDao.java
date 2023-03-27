package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsRVehicleDevice;

/**
 * 车辆设备关联表DAO接口
 * @author oqm
 * @version 2023-02-03
 */
@MyBatisDao
public interface WsVmsRVehicleDeviceDao extends CrudDao<WsVmsRVehicleDevice> {
	
}