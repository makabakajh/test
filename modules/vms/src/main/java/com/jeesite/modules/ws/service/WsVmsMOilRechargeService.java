package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMOilRecharge;
import com.jeesite.modules.ws.dao.WsVmsMOilRechargeDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 油卡充值记录Service
 * @author oqm
 * @version 2023-02-06
 */
@Service
public class WsVmsMOilRechargeService extends CrudService<WsVmsMOilRechargeDao, WsVmsMOilRecharge> {
	
	/**
	 * 获取单条数据
	 * @param wsVmsMOilRecharge
	 * @return
	 */
	@Override
	public WsVmsMOilRecharge get(WsVmsMOilRecharge wsVmsMOilRecharge) {
		return super.get(wsVmsMOilRecharge);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMOilRecharge 查询条件
	 * @param wsVmsMOilRecharge.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMOilRecharge> findPage(WsVmsMOilRecharge wsVmsMOilRecharge) {
		return super.findPage(wsVmsMOilRecharge);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMOilRecharge
	 * @return
	 */
	@Override
	public List<WsVmsMOilRecharge> findList(WsVmsMOilRecharge wsVmsMOilRecharge) {
		return super.findList(wsVmsMOilRecharge);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMOilRecharge
	 */
	@Override
	@Transactional
	public void save(WsVmsMOilRecharge wsVmsMOilRecharge) {
		super.save(wsVmsMOilRecharge);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsMOilRecharge, wsVmsMOilRecharge.getId(), "wsVmsMOilRecharge_image");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMOilRecharge
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMOilRecharge wsVmsMOilRecharge) {
		super.updateStatus(wsVmsMOilRecharge);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMOilRecharge
	 */
	@Override
	@Transactional
	public void delete(WsVmsMOilRecharge wsVmsMOilRecharge) {
		super.delete(wsVmsMOilRecharge);
	}
	
}