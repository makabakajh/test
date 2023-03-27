package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMRepair;

/**
 * 维修保养表DAO接口
 * @author oqm
 * @version 2023-02-06
 */
@MyBatisDao
public interface WsVmsMRepairDao extends CrudDao<WsVmsMRepair> {
	
}