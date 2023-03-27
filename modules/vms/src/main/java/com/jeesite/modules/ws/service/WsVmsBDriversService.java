package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsBDrivers;
import com.jeesite.modules.ws.dao.WsVmsBDriversDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 司机表Service
 * @author oqm
 * @version 2023-02-03
 */
@Service
public class WsVmsBDriversService extends CrudService<WsVmsBDriversDao, WsVmsBDrivers> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsBDrivers
	 * @return
	 */
	@Override
	public WsVmsBDrivers get(WsVmsBDrivers wsVmsBDrivers) {
		return super.get(wsVmsBDrivers);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsBDrivers 查询条件
	 * @param wsVmsBDrivers.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsBDrivers> findPage(WsVmsBDrivers wsVmsBDrivers) {
		return super.findPage(wsVmsBDrivers);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsBDrivers
	 * @return
	 */
	@Override
	public List<WsVmsBDrivers> findList(WsVmsBDrivers wsVmsBDrivers) {
		return super.findList(wsVmsBDrivers);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsBDrivers
	 */
	@Override
	@Transactional
	public void save(WsVmsBDrivers wsVmsBDrivers) {
		super.save(wsVmsBDrivers);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsBDrivers, wsVmsBDrivers.getId(), "wsVmsBDrivers_image");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsBDrivers
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsBDrivers wsVmsBDrivers) {
		super.updateStatus(wsVmsBDrivers);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsBDrivers
	 */
	@Override
	@Transactional
	public void delete(WsVmsBDrivers wsVmsBDrivers) {
		super.delete(wsVmsBDrivers);
	}
	
}