package com.jeesite.modules.ws.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.ws.entity.WsVmsMEtcRecord;
import com.jeesite.modules.ws.dao.WsVmsMEtcRecordDao;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.config.Global;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.common.utils.excel.ExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * etc记录Service
 * @author oqm
 * @version 2023-02-14
 */
@Service
public class WsVmsMEtcRecordService extends CrudService<WsVmsMEtcRecordDao, WsVmsMEtcRecord> {
	@Resource
	private WsVmsMEtcRecordDao wsVmsMEtcRecordDao;
	/**
	 * 获取单条数据
	 * @param wsVmsMEtcRecord
	 * @return
	 */
	@Override
	public WsVmsMEtcRecord get(WsVmsMEtcRecord wsVmsMEtcRecord) {
		return super.get(wsVmsMEtcRecord);
	}
	
	/**
	 * 查询分页数据
	 * @param wsVmsMEtcRecord 查询条件
	 * @param wsVmsMEtcRecord.page 分页对象
	 * @return
	 */
	@Override
	public Page<WsVmsMEtcRecord> findPage(WsVmsMEtcRecord wsVmsMEtcRecord) {
		return super.findPage(wsVmsMEtcRecord);
	}
	
	/**
	 * 查询列表数据
	 * @param wsVmsMEtcRecord
	 * @return
	 */
	@Override
	public List<WsVmsMEtcRecord> findList(WsVmsMEtcRecord wsVmsMEtcRecord) {
		return super.findList(wsVmsMEtcRecord);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wsVmsMEtcRecord
	 */
	@Override
	@Transactional
	public void save(WsVmsMEtcRecord wsVmsMEtcRecord) {
		super.save(wsVmsMEtcRecord);
	}

	/**
	 * 导入数据
	 * @param file 导入的数据文件
	 */
	@Transactional
	public String importData(MultipartFile file) {
		if (file == null){
			throw new ServiceException(text("请选择导入的数据文件！"));
		}
		int successNum = 0; int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		try(ExcelImport ei = new ExcelImport(file, 2, 0)){
			List<WsVmsMEtcRecord> list = ei.getDataList(WsVmsMEtcRecord.class);
			for (WsVmsMEtcRecord wsVmsMEtcRecord : list) {
				try{
					ValidatorUtils.validateWithException(wsVmsMEtcRecord);
					this.save(wsVmsMEtcRecord);
					successNum++;
					successMsg.append("<br/>" + successNum + "、编号 " + wsVmsMEtcRecord.getId() + " 导入成功");
				} catch (Exception e) {
					failureNum++;
					String msg = "<br/>" + failureNum + "、编号 " + wsVmsMEtcRecord.getId() + " 导入失败：";
					if (e instanceof ConstraintViolationException){
						ConstraintViolationException cve = (ConstraintViolationException)e;
						for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
							msg += Global.getText(violation.getMessage()) + " ("+violation.getPropertyPath()+")";
						}
					}else{
						msg += e.getMessage();
					}
					failureMsg.append(msg);
					logger.error(msg, e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			failureMsg.append(e.getMessage());
			return failureMsg.toString();
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new ServiceException(failureMsg.toString());
		}else{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}
	
	/**
	 * 更新状态
	 * @param wsVmsMEtcRecord
	 */
	@Override
	@Transactional
	public void updateStatus(WsVmsMEtcRecord wsVmsMEtcRecord) {
		super.updateStatus(wsVmsMEtcRecord);
	}
	
	/**
	 * 删除数据
	 * @param wsVmsMEtcRecord
	 */
	@Override
	@Transactional
	public void delete(WsVmsMEtcRecord wsVmsMEtcRecord) {
		super.delete(wsVmsMEtcRecord);
	}

	@Transactional
	public void insertBatch(List<WsVmsMEtcRecord> list){
		wsVmsMEtcRecordDao.insertBatch(list);
	}

	public WsVmsMEtcRecord getByPassId(String passId){
		return wsVmsMEtcRecordDao.getByPassId(passId);
	}
}