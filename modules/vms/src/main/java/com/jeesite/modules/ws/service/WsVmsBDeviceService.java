package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsBDevice;
import com.jeesite.modules.ws.dao.WsVmsBDeviceDao;

/**
 * 设备Service
 * @author oqm
 * @version 2023-02-03
 */
@Service
public class WsVmsBDeviceService extends CrudService<WsVmsBDeviceDao, WsVmsBDevice> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsBDevice
	 * @return
	 */
	@Override
	public WsVmsBDevice get(WsVmsBDevice wsVmsBDevice) {
		return super.get(wsVmsBDevice);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsBDevice 查询条件
	 * @param wsVmsBDevice.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsBDevice> findPage(WsVmsBDevice wsVmsBDevice) {
		return super.findPage(wsVmsBDevice);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsBDevice
	 * @return
	 */
	@Override
	public List<WsVmsBDevice> findList(WsVmsBDevice wsVmsBDevice) {
		return super.findList(wsVmsBDevice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsBDevice
	 */
	@Override
	@Transactional
	public void save(WsVmsBDevice wsVmsBDevice) {
		super.save(wsVmsBDevice);
	}
	
	/**
	 * 更新状态
	 * @param wsVmsBDevice
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsBDevice wsVmsBDevice) {
		super.updateStatus(wsVmsBDevice);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsBDevice
	 */
	@Override
	@Transactional
	public void delete(WsVmsBDevice wsVmsBDevice) {
		super.delete(wsVmsBDevice);
	}
	
}