package com.jeesite.modules.ws.service;

import java.util.List;

import com.jeesite.modules.ws.dao.WsVmsMInsuranceDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMYearInspect;
import com.jeesite.modules.ws.dao.WsVmsMYearInspectDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

import javax.annotation.Resource;

/**
 * 年检表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMYearInspectService extends CrudService<WsVmsMYearInspectDao, WsVmsMYearInspect> {
	@Resource
	private WsVmsMYearInspectDao wsVmsMYearInspectDao;
	/**
	 * 获取单条数据
	 * @param wsVmsMYearInspect
	 * @return
	 */
	@Override
	public WsVmsMYearInspect get(WsVmsMYearInspect wsVmsMYearInspect) {
		return super.get(wsVmsMYearInspect);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMYearInspect 查询条件
	 * @param wsVmsMYearInspect.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMYearInspect> findPage(WsVmsMYearInspect wsVmsMYearInspect) {
		return super.findPage(wsVmsMYearInspect);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMYearInspect
	 * @return
	 */
	@Override
	public List<WsVmsMYearInspect> findList(WsVmsMYearInspect wsVmsMYearInspect) {
		return super.findList(wsVmsMYearInspect);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMYearInspect
	 */
	@Override
	@Transactional
	public void save(WsVmsMYearInspect wsVmsMYearInspect) {
		super.save(wsVmsMYearInspect);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMYearInspect, wsVmsMYearInspect.getId(), "wsVmsMYearInspect_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsMYearInspect, wsVmsMYearInspect.getId(), "wsVmsMYearInspect_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMYearInspect
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMYearInspect wsVmsMYearInspect) {
		super.updateStatus(wsVmsMYearInspect);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMYearInspect
	 */
	@Override
	@Transactional
	public void delete(WsVmsMYearInspect wsVmsMYearInspect) {
		super.delete(wsVmsMYearInspect);
	}

	public WsVmsMYearInspect getLastInspectByVehicleId(String vehicleId){
		return wsVmsMYearInspectDao.getLastInspectByVehicleId(vehicleId);
	}
}