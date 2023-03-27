package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMInsurance;

/**
 * 保险表DAO接口
 * @author oqm
 * @version 2023-02-06
 */
@MyBatisDao
public interface WsVmsMInsuranceDao extends CrudDao<WsVmsMInsurance> {
    WsVmsMInsurance getLastInsuranceByVehicleId(String vehicleId);
}