package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMUse;
import com.jeesite.modules.ws.dao.WsVmsMUseDao;

/**
 * 用车表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMUseService extends CrudService<WsVmsMUseDao, WsVmsMUse> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMUse
	 * @return
	 */
	@Override
	public WsVmsMUse get(WsVmsMUse wsVmsMUse) {
		return super.get(wsVmsMUse);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMUse 查询条件
	 * @param wsVmsMUse.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMUse> findPage(WsVmsMUse wsVmsMUse) {
		return super.findPage(wsVmsMUse);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMUse
	 * @return
	 */
	@Override
	public List<WsVmsMUse> findList(WsVmsMUse wsVmsMUse) {
		return super.findList(wsVmsMUse);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMUse
	 */
	@Override
	@Transactional
	public void save(WsVmsMUse wsVmsMUse) {
		super.save(wsVmsMUse);
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMUse
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMUse wsVmsMUse) {
		super.updateStatus(wsVmsMUse);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMUse
	 */
	@Override
	@Transactional
	public void delete(WsVmsMUse wsVmsMUse) {
		super.delete(wsVmsMUse);
	}
	
}