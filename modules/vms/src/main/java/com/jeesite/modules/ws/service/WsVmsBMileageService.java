package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsBMileage;
import com.jeesite.modules.ws.dao.WsVmsBMileageDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

import javax.annotation.Resource;

/**
 * 里程表Service
 * @author oqm
 * @version 2023-02-13
 */
@Service
public class WsVmsBMileageService extends CrudService<WsVmsBMileageDao, WsVmsBMileage> {
	@Resource
	private WsVmsBMileageDao wsVmsBMileageDao;
	/**
	 * 获取单条数据
	 * @param wsVmsBMileage
	 * @return
	 */
	@Override
	public WsVmsBMileage get(WsVmsBMileage wsVmsBMileage) {
		return super.get(wsVmsBMileage);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsBMileage 查询条件
	 * @param wsVmsBMileage.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsBMileage> findPage(WsVmsBMileage wsVmsBMileage) {
		return super.findPage(wsVmsBMileage);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsBMileage
	 * @return
	 */
	@Override
	public List<WsVmsBMileage> findList(WsVmsBMileage wsVmsBMileage) {
		return super.findList(wsVmsBMileage);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsBMileage
	 */
	@Override
	@Transactional
	public void save(WsVmsBMileage wsVmsBMileage) {
		super.save(wsVmsBMileage);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(wsVmsBMileage, wsVmsBMileage.getId(), "wsVmsBMileage_image");
	}
	
	/**
	 * 更新状态
	 * @param wsVmsBMileage
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsBMileage wsVmsBMileage) {
		super.updateStatus(wsVmsBMileage);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsBMileage
	 */
	@Override
	@Transactional
	public void delete(WsVmsBMileage wsVmsBMileage) {
		super.delete(wsVmsBMileage);
	}

	public List<WsVmsBMileage> getByVehicleIdAndDate(String vehicleId,String date){
		return wsVmsBMileageDao.getVehicleIdAndDate(vehicleId,date);
	}
}