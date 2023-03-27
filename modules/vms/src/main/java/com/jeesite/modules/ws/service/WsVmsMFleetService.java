package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMFleet;
import com.jeesite.modules.ws.dao.WsVmsMFleetDao;

/**
 * 车队表Service
 * @author oqm
 * @version 2023-02-03
 */
@Service
public class WsVmsMFleetService extends CrudService<WsVmsMFleetDao, WsVmsMFleet> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMFleet
	 * @return
	 */
	@Override
	public WsVmsMFleet get(WsVmsMFleet wsVmsMFleet) {
		return super.get(wsVmsMFleet);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMFleet 查询条件
	 * @param wsVmsMFleet.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMFleet> findPage(WsVmsMFleet wsVmsMFleet) {
		return super.findPage(wsVmsMFleet);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMFleet
	 * @return
	 */
	@Override
	public List<WsVmsMFleet> findList(WsVmsMFleet wsVmsMFleet) {
		return super.findList(wsVmsMFleet);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMFleet
	 */
	@Override
	@Transactional
	public void save(WsVmsMFleet wsVmsMFleet) {
		super.save(wsVmsMFleet);
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMFleet
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMFleet wsVmsMFleet) {
		super.updateStatus(wsVmsMFleet);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMFleet
	 */
	@Override
	@Transactional
	public void delete(WsVmsMFleet wsVmsMFleet) {
		super.delete(wsVmsMFleet);
	}
	
}