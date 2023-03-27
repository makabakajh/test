package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMRepair;
import com.jeesite.modules.ws.dao.WsVmsMRepairDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 维修保养表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMRepairService extends CrudService<WsVmsMRepairDao, WsVmsMRepair> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMRepair
	 * @return
	 */
	@Override
	public WsVmsMRepair get(WsVmsMRepair wsVmsMRepair) {
		return super.get(wsVmsMRepair);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMRepair 查询条件
	 * @param wsVmsMRepair.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMRepair> findPage(WsVmsMRepair wsVmsMRepair) {
		return super.findPage(wsVmsMRepair);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMRepair
	 * @return
	 */
	@Override
	public List<WsVmsMRepair> findList(WsVmsMRepair wsVmsMRepair) {
		return super.findList(wsVmsMRepair);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMRepair
	 */
	@Override
	@Transactional
	public void save(WsVmsMRepair wsVmsMRepair) {
		super.save(wsVmsMRepair);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMRepair, wsVmsMRepair.getId(), "wsVmsMRepair_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsMRepair, wsVmsMRepair.getId(), "wsVmsMRepair_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMRepair
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMRepair wsVmsMRepair) {
		super.updateStatus(wsVmsMRepair);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMRepair
	 */
	@Override
	@Transactional
	public void delete(WsVmsMRepair wsVmsMRepair) {
		super.delete(wsVmsMRepair);
	}
	
}