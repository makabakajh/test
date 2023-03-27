package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMVehicleLicense;

/**
 * 行驶证DAO接口
 * @author oqm
 * @version 2023-02-14
 */
@MyBatisDao
public interface WsVmsMVehicleLicenseDao extends CrudDao<WsVmsMVehicleLicense> {
	
}