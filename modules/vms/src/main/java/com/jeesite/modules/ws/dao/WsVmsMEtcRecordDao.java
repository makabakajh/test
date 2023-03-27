package com.jeesite.modules.ws.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.ws.entity.WsVmsMEtcRecord;
import org.apache.ibatis.annotations.Param;

/**
 * etc记录DAO接口
 * @author oqm
 * @version 2023-02-14
 */
@MyBatisDao
public interface WsVmsMEtcRecordDao extends CrudDao<WsVmsMEtcRecord> {
    WsVmsMEtcRecord getByPassId(@Param("passId") String passId);
}