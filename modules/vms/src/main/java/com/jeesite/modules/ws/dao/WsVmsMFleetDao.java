package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMFleet;

/**
 * 车队表DAO接口
 * @author oqm
 * @version 2023-02-03
 */
@MyBatisDao
public interface WsVmsMFleetDao extends CrudDao<WsVmsMFleet> {
	
}