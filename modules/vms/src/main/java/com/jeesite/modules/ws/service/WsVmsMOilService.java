package com.jeesite.modules.ws.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMOil;
import com.jeesite.modules.ws.dao.WsVmsMOilDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

import javax.annotation.Resource;

/**
 * 加油记录Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMOilService extends CrudService<WsVmsMOilDao, WsVmsMOil> {
	@Resource
	private WsVmsMOilDao wsVmsMOilDao;
	/**
	 * 获取单条数据
	 * @param wsVmsMOil
	 * @return
	 */
	@Override
	public WsVmsMOil get(WsVmsMOil wsVmsMOil) {
		return super.get(wsVmsMOil);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMOil 查询条件
	 * @param wsVmsMOil.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMOil> findPage(WsVmsMOil wsVmsMOil) {
		return super.findPage(wsVmsMOil);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMOil
	 * @return
	 */
	@Override
	public List<WsVmsMOil> findList(WsVmsMOil wsVmsMOil) {
		return super.findList(wsVmsMOil);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMOil
	 */
	@Override
	@Transactional
	public void save(WsVmsMOil wsVmsMOil) {
		super.save(wsVmsMOil);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMOil, wsVmsMOil.getId(), "wsVmsMOil_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsMOil, wsVmsMOil.getId(), "wsVmsMOil_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMOil
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMOil wsVmsMOil) {
		super.updateStatus(wsVmsMOil);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMOil
	 */
	@Override
	@Transactional
	public void delete(WsVmsMOil wsVmsMOil) {
		super.delete(wsVmsMOil);
	}

	public List<WsVmsMOil> findByVehicleId(String vehicleId) {
		return wsVmsMOilDao.findByVehicleId(vehicleId);
	}

	public Map<String,Object> getAmountByVehicleId(String vehicleId){
		return wsVmsMOilDao.getAmountByVehicleId(vehicleId);
	}
}