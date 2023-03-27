package com.jeesite.modules.ws.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.utils.excel.annotation.ExcelField.Type;
import org.springframework.web.multipart.MultipartFile;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.ws.entity.WsVmsMEtcRecord;
import com.jeesite.modules.ws.service.WsVmsMEtcRecordService;

/**
 * etc记录Controller
 * @author oqm
 * @version 2023-02-14
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMEtcRecord")
public class WsVmsMEtcRecordController extends BaseController {

	@Autowired
	private WsVmsMEtcRecordService wsVmsMEtcRecordService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMEtcRecord get(String id, boolean isNewRecord) {
		return wsVmsMEtcRecordService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMEtcRecord wsVmsMEtcRecord, Model model) {
		model.addAttribute("wsVmsMEtcRecord", wsVmsMEtcRecord);
		return "modules/ws/wsVmsMEtcRecordList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMEtcRecord> listData(WsVmsMEtcRecord wsVmsMEtcRecord, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMEtcRecord.setPage(new Page<>(request, response));
		Page<WsVmsMEtcRecord> page = wsVmsMEtcRecordService.findPage(wsVmsMEtcRecord);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMEtcRecord wsVmsMEtcRecord, Model model) {
		model.addAttribute("wsVmsMEtcRecord", wsVmsMEtcRecord);
		return "modules/ws/wsVmsMEtcRecordForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMEtcRecord wsVmsMEtcRecord) {
		wsVmsMEtcRecordService.save(wsVmsMEtcRecord);
		return renderResult(Global.TRUE, text("保存etc记录成功！"));
	}

	/**
	 * 导出数据
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:view")
	@RequestMapping(value = "exportData")
	public void exportData(WsVmsMEtcRecord wsVmsMEtcRecord, HttpServletResponse response) {
		List<WsVmsMEtcRecord> list = wsVmsMEtcRecordService.findList(wsVmsMEtcRecord);
		String fileName = "etc记录" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("etc记录", WsVmsMEtcRecord.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载模板
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		WsVmsMEtcRecord wsVmsMEtcRecord = new WsVmsMEtcRecord();
		List<WsVmsMEtcRecord> list = ListUtils.newArrayList(wsVmsMEtcRecord);
		String fileName = "etc记录模板.xlsx";
		try(ExcelExport ee = new ExcelExport("etc记录", WsVmsMEtcRecord.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入数据
	 */
	@ResponseBody
	@RequiresPermissions("ws:wsVmsMEtcRecord:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file) {
		try {
			String message = wsVmsMEtcRecordService.importData(file);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMEtcRecord:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMEtcRecord wsVmsMEtcRecord) {
		wsVmsMEtcRecordService.delete(wsVmsMEtcRecord);
		return renderResult(Global.TRUE, text("删除etc记录成功！"));
	}

	@RequestMapping(value = "wsVmsMEtcRecordImport")
	public String wsVmsMEtcRecordImport(WsVmsMEtcRecord wsVmsMEtcRecord, Model model) {
		model.addAttribute("wsVmsMEtcRecord", wsVmsMEtcRecord);
		return "modules/ws/wsVmsMEtcRecordImport";
	}

	/*
	 * @Author oqm
	 * @Date 2023/2/15
	 * @Param
	 * @return
	 * @Description 手动导入ETC记录
	 **/
	@PostMapping(value = "etcRecordImportSave")
	@ResponseBody
	public String etcRecordImportSave(String content) {
		if(StringUtils.isNotBlank(content)){
			JSONObject json = JSON.parseObject(content);
			String retcode = json.getString("retcode");
			if("0000".equals(retcode)){
				ArrayList<WsVmsMEtcRecord> list = new ArrayList<>();
				JSONArray jsonArray = json.getJSONObject("beResult").getJSONArray("beArray");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String passId = jsonObject.getString("passId");
					WsVmsMEtcRecord etcRecord = wsVmsMEtcRecordService.getByPassId(passId);
					if(etcRecord!=null){
						continue;
					}
					String plateNum = jsonObject.getString("plateNum");
					String startStation = jsonObject.getString("enTollStationName");
					String endStation = jsonObject.getString("exTollStationName");
					Date startTime = jsonObject.getDate("enTime");
					Date endTime = jsonObject.getDate("exTime");
					BigDecimal totalAmount = jsonObject.getBigDecimal("totalAmount");
					WsVmsMEtcRecord wsVmsMEtcRecord = new WsVmsMEtcRecord();
					wsVmsMEtcRecord.setPassId(passId);
					wsVmsMEtcRecord.setLicensePlate(plateNum);
					wsVmsMEtcRecord.setStartStation(startStation);
					wsVmsMEtcRecord.setEndStation(endStation);
					wsVmsMEtcRecord.setStartTime(startTime);
					wsVmsMEtcRecord.setEndTime(endTime);
					wsVmsMEtcRecord.setTotalAmount(totalAmount);
					list.add(wsVmsMEtcRecord);
				}
				wsVmsMEtcRecordService.insertBatch(list);
				return renderResult(Global.TRUE, text("导入etc记录成功！"));
			}
		}
		return renderResult(Global.FALSE, text("操作失败，请联系管理员！"));
	}
}