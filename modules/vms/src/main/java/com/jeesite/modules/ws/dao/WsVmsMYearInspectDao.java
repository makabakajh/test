package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMYearInspect;

/**
 * 年检表DAO接口
 * @author oqm
 * @version 2023-02-06
 */
@MyBatisDao
public interface WsVmsMYearInspectDao extends CrudDao<WsVmsMYearInspect> {
	WsVmsMYearInspect getLastInspectByVehicleId(String vehicleId);
}