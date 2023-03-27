package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsRVehicleDevice;
import com.jeesite.modules.ws.dao.WsVmsRVehicleDeviceDao;

/**
 * 车辆设备关联表Service
 * @author oqm
 * @version 2023-02-03
 */
@Service
public class WsVmsRVehicleDeviceService extends CrudService<WsVmsRVehicleDeviceDao, WsVmsRVehicleDevice> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsRVehicleDevice
	 * @return
	 */
	@Override
	public WsVmsRVehicleDevice get(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		return super.get(wsVmsRVehicleDevice);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsRVehicleDevice 查询条件
	 * @param wsVmsRVehicleDevice.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsRVehicleDevice> findPage(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		return super.findPage(wsVmsRVehicleDevice);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsRVehicleDevice
	 * @return
	 */
	@Override
	public List<WsVmsRVehicleDevice> findList(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		return super.findList(wsVmsRVehicleDevice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsRVehicleDevice
	 */
	@Override
	@Transactional
	public void save(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		super.save(wsVmsRVehicleDevice);
	}
	
	/**
	 * 更新状态
	 * @param wsVmsRVehicleDevice
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		super.updateStatus(wsVmsRVehicleDevice);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsRVehicleDevice
	 */
	@Override
	@Transactional
	public void delete(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		super.delete(wsVmsRVehicleDevice);
	}
	
}