package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMVehicleLicense;
import com.jeesite.modules.ws.dao.WsVmsMVehicleLicenseDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 行驶证Service
 * @author oqm
 * @version 2023-02-14
 */
@Service
public class WsVmsMVehicleLicenseService extends CrudService<WsVmsMVehicleLicenseDao, WsVmsMVehicleLicense> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMVehicleLicense
	 * @return
	 */
	@Override
	public WsVmsMVehicleLicense get(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		return super.get(wsVmsMVehicleLicense);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMVehicleLicense 查询条件
	 * @param wsVmsMVehicleLicense.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMVehicleLicense> findPage(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		return super.findPage(wsVmsMVehicleLicense);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMVehicleLicense
	 * @return
	 */
	@Override
	public List<WsVmsMVehicleLicense> findList(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		return super.findList(wsVmsMVehicleLicense);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMVehicleLicense
	 */
	@Override
	@Transactional
	public void save(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		super.save(wsVmsMVehicleLicense);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMVehicleLicense, wsVmsMVehicleLicense.getId(), "wsVmsMVehicleLicense_image");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMVehicleLicense
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		super.updateStatus(wsVmsMVehicleLicense);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMVehicleLicense
	 */
	@Override
	@Transactional
	public void delete(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		super.delete(wsVmsMVehicleLicense);
	}
	
}