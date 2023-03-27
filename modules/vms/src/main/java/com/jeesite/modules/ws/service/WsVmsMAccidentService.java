package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMAccident;
import com.jeesite.modules.ws.dao.WsVmsMAccidentDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 事故表Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMAccidentService extends CrudService<WsVmsMAccidentDao, WsVmsMAccident> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMAccident
	 * @return
	 */
	@Override
	public WsVmsMAccident get(WsVmsMAccident wsVmsMAccident) {
		return super.get(wsVmsMAccident);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMAccident 查询条件
	 * @param wsVmsMAccident.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMAccident> findPage(WsVmsMAccident wsVmsMAccident) {
		return super.findPage(wsVmsMAccident);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMAccident
	 * @return
	 */
	@Override
	public List<WsVmsMAccident> findList(WsVmsMAccident wsVmsMAccident) {
		return super.findList(wsVmsMAccident);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMAccident
	 */
	@Override
	@Transactional
	public void save(WsVmsMAccident wsVmsMAccident) {
		super.save(wsVmsMAccident);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMAccident, wsVmsMAccident.getId(), "wsVmsMAccident_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(wsVmsMAccident, wsVmsMAccident.getId(), "wsVmsMAccident_file");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMAccident
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMAccident wsVmsMAccident) {
		super.updateStatus(wsVmsMAccident);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMAccident
	 */
	@Override
	@Transactional
	public void delete(WsVmsMAccident wsVmsMAccident) {
		super.delete(wsVmsMAccident);
	}
	
}