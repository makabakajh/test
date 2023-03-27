package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMInsurance;
import com.jeesite.modules.ws.dao.WsVmsMInsuranceDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

import javax.annotation.Resource;

/**
 * 保险表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMInsuranceService extends CrudService<WsVmsMInsuranceDao, WsVmsMInsurance> {
	@Resource
	private WsVmsMInsuranceDao wsVmsMInsuranceDao;

	/**
	 * 获取单条数据
	 * @param wsVmsMInsurance
	 * @return
	 */
	@Override
	public WsVmsMInsurance get(WsVmsMInsurance wsVmsMInsurance) {
		return super.get(wsVmsMInsurance);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMInsurance 查询条件
	 * @param wsVmsMInsurance.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMInsurance> findPage(WsVmsMInsurance wsVmsMInsurance) {
		return super.findPage(wsVmsMInsurance);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMInsurance
	 * @return
	 */
	@Override
	public List<WsVmsMInsurance> findList(WsVmsMInsurance wsVmsMInsurance) {
		return super.findList(wsVmsMInsurance);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMInsurance
	 */
	@Override
	@Transactional
	public void save(WsVmsMInsurance wsVmsMInsurance) {
		super.save(wsVmsMInsurance);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMInsurance, wsVmsMInsurance.getId(), "wsVmsMInsurance_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsMInsurance, wsVmsMInsurance.getId(), "wsVmsMInsurance_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMInsurance
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMInsurance wsVmsMInsurance) {
		super.updateStatus(wsVmsMInsurance);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMInsurance
	 */
	@Override
	@Transactional
	public void delete(WsVmsMInsurance wsVmsMInsurance) {
		super.delete(wsVmsMInsurance);
	}

	public WsVmsMInsurance getLastInsuranceByVehicleId(String vehicleId){
		return wsVmsMInsuranceDao.getLastInsuranceByVehicleId(vehicleId);
	}
}