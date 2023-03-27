package com.jeesite.modules.ws.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsRVehicleDrives;
import com.jeesite.modules.ws.dao.WsVmsRVehicleDrivesDao;

import javax.annotation.Resource;

/**
 * 车辆司机关联表Service
 * @author oqm
 * @version 2023-02-03
 */
@Service
public class WsVmsRVehicleDrivesService extends CrudService<WsVmsRVehicleDrivesDao, WsVmsRVehicleDrives> {
	@Resource
	public WsVmsRVehicleDrivesDao wsVmsRVehicleDrivesDao;
	/**
	 * 获取单条数据
	 * @param wsVmsRVehicleDrives
	 * @return
	 */
	@Override
	public WsVmsRVehicleDrives get(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		return super.get(wsVmsRVehicleDrives);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsRVehicleDrives 查询条件
	 * @param wsVmsRVehicleDrives.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsRVehicleDrives> findPage(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		return super.findPage(wsVmsRVehicleDrives);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsRVehicleDrives
	 * @return
	 */
	@Override
	public List<WsVmsRVehicleDrives> findList(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		return super.findList(wsVmsRVehicleDrives);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsRVehicleDrives
	 */
	@Override
	@Transactional
	public void save(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		super.save(wsVmsRVehicleDrives);
	}
	
	/**
	 * 更新状态
	 * @param wsVmsRVehicleDrives
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		super.updateStatus(wsVmsRVehicleDrives);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsRVehicleDrives
	 */
	@Override
	@Transactional
	public void delete(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		super.delete(wsVmsRVehicleDrives);
	}
	@Transactional(readOnly = false)
	public void insertBatch(List<WsVmsRVehicleDrives> wsVmsRVehicleDrivesList){
		wsVmsRVehicleDrivesDao.insertBatch(wsVmsRVehicleDrivesList);
	}

	public List<Map<String,Object>> getMainDriverByVehicleId(String vehicleId){
		return wsVmsRVehicleDrivesDao.getMainDriverByVehicleId(vehicleId);
	}
	public List<String> getDriverByDrivers(String vehicleId,List<String> driverList){
		return wsVmsRVehicleDrivesDao.getDriverByDrivers(vehicleId,driverList);
	}
	public String getDriverByMainDriverId(String mainDriverId){
		return wsVmsRVehicleDrivesDao.getDriverByMainDriverId(mainDriverId);
	}
}