package com.jeesite.modules.ws.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsBVehicle;
import com.jeesite.modules.ws.dao.WsVmsBVehicleDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

import javax.annotation.Resource;

/**
 * 车辆Service
 * @author oqm
 * @version 2023-02-03
 */
@Service
public class WsVmsBVehicleService extends CrudService<WsVmsBVehicleDao, WsVmsBVehicle> {
	@Resource
	private WsVmsBVehicleDao wsVmsBVehicleDao;
	/**
	 * 获取单条数据
	 * @param wsVmsBVehicle
	 * @return
	 */
	@Override
	public WsVmsBVehicle get(WsVmsBVehicle wsVmsBVehicle) {
		return super.get(wsVmsBVehicle);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsBVehicle 查询条件
	 * @param wsVmsBVehicle.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsBVehicle> findPage(WsVmsBVehicle wsVmsBVehicle) {
		return super.findPage(wsVmsBVehicle);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsBVehicle
	 * @return
	 */
	@Override
	public List<WsVmsBVehicle> findList(WsVmsBVehicle wsVmsBVehicle) {
		return super.findList(wsVmsBVehicle);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsBVehicle
	 */
	@Override
	@Transactional
	public void save(WsVmsBVehicle wsVmsBVehicle) {
		super.save(wsVmsBVehicle);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsBVehicle, wsVmsBVehicle.getId(), "wsVmsBVehicle_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsBVehicle, wsVmsBVehicle.getId(), "wsVmsBVehicle_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsBVehicle
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsBVehicle wsVmsBVehicle) {
		super.updateStatus(wsVmsBVehicle);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsBVehicle
	 */
	@Override
	@Transactional
	public void delete(WsVmsBVehicle wsVmsBVehicle) {
		super.delete(wsVmsBVehicle);
	}

	public List<Map<String,Object>> getLicenseByDeviceCode(List<String> deviceCodes){
		return wsVmsBVehicleDao.getLicenseByDeviceCode(deviceCodes);
	}

	public List<Map<String,Object>> getVehicleList(){
		return wsVmsBVehicleDao.getVehicleList();
	}
	public List<Map<String,Object>> getVehicleCost(String vehicleId,String date){
		return wsVmsBVehicleDao.getVehicleCost(vehicleId,date);
	}
	public List<Map<String,Object>> getTotalCostByMonth(String startDate,String endDate){
		return wsVmsBVehicleDao.getTotalCostByMonth(startDate,endDate);
	}
	public List<Map<String,Object>> getTotalCostByQuarter(String startDate,String endDate){
		return wsVmsBVehicleDao.getTotalCostByQuarter(startDate,endDate);
	}
	public List<Map<String,Object>> getVehicleCostByMonth(String startDate,String endDate){
		return wsVmsBVehicleDao.getVehicleCostByMonth(startDate,endDate);
	}
	public List<Map<String,Object>> getVehicleCostByQuarter(String startDate,String endDate){
		return wsVmsBVehicleDao.getVehicleCostByQuarter(startDate,endDate);
	}
	public List<Map<String,Object>> getDeviceList(){
		return wsVmsBVehicleDao.getDeviceList();
	}
	public Map<String,Object> getYingYanMileageByVehicleId(String vehicleId,String date){
		return wsVmsBVehicleDao.getYingYanMileageByVehicleId(vehicleId,date);
	}
	public Map<String,String> getVehicleDriverByVehicleId(String vehicleId){
		return wsVmsBVehicleDao.getVehicleDriverByVehicleId(vehicleId);
	}
	public Map<String,Object> getVehicleInfoByVehicleId(String vehicleId){
		return wsVmsBVehicleDao.getVehicleInfoById(vehicleId);
	}
}