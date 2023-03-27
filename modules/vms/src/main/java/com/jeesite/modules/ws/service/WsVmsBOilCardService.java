package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsBOilCard;
import com.jeesite.modules.ws.dao.WsVmsBOilCardDao;

/**
 * 油卡表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsBOilCardService extends CrudService<WsVmsBOilCardDao, WsVmsBOilCard> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsBOilCard
	 * @return
	 */
	@Override
	public WsVmsBOilCard get(WsVmsBOilCard wsVmsBOilCard) {
		return super.get(wsVmsBOilCard);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsBOilCard 查询条件
	 * @param wsVmsBOilCard.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsBOilCard> findPage(WsVmsBOilCard wsVmsBOilCard) {
		return super.findPage(wsVmsBOilCard);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsBOilCard
	 * @return
	 */
	@Override
	public List<WsVmsBOilCard> findList(WsVmsBOilCard wsVmsBOilCard) {
		return super.findList(wsVmsBOilCard);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsBOilCard
	 */
	@Override
	@Transactional
	public void save(WsVmsBOilCard wsVmsBOilCard) {
		super.save(wsVmsBOilCard);
	}
	
	/**
	 * 更新状态
	 * @param wsVmsBOilCard
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsBOilCard wsVmsBOilCard) {
		super.updateStatus(wsVmsBOilCard);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsBOilCard
	 */
	@Override
	@Transactional
	public void delete(WsVmsBOilCard wsVmsBOilCard) {
		super.delete(wsVmsBOilCard);
	}
	
}