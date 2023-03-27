package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMRegulations;
import com.jeesite.modules.ws.dao.WsVmsMRegulationsDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 违章表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMRegulationsService extends CrudService<WsVmsMRegulationsDao, WsVmsMRegulations> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMRegulations
	 * @return
	 */
	@Override
	public WsVmsMRegulations get(WsVmsMRegulations wsVmsMRegulations) {
		return super.get(wsVmsMRegulations);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMRegulations 查询条件
	 * @param wsVmsMRegulations.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMRegulations> findPage(WsVmsMRegulations wsVmsMRegulations) {
		return super.findPage(wsVmsMRegulations);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMRegulations
	 * @return
	 */
	@Override
	public List<WsVmsMRegulations> findList(WsVmsMRegulations wsVmsMRegulations) {
		return super.findList(wsVmsMRegulations);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMRegulations
	 */
	@Override
	@Transactional
	public void save(WsVmsMRegulations wsVmsMRegulations) {
		super.save(wsVmsMRegulations);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMRegulations, wsVmsMRegulations.getId(), "wsVmsMRegulations_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsMRegulations, wsVmsMRegulations.getId(), "wsVmsMRegulations_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMRegulations
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMRegulations wsVmsMRegulations) {
		super.updateStatus(wsVmsMRegulations);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMRegulations
	 */
	@Override
	@Transactional
	public void delete(WsVmsMRegulations wsVmsMRegulations) {
		super.delete(wsVmsMRegulations);
	}
	
}