package com.jeesite.modules.ws.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.common.entity.Page;
import com.alibaba.fastjson.JSONValidator;
import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.ws.entity.WsVmsBDevice;
import com.jeesite.modules.ws.service.WsVmsBDeviceService;

/**
 * 设备Controller
 * @author oqm
 * @version 2023-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsBDevice")
public class WsVmsBDeviceController extends BaseController {

	@Autowired
	private WsVmsBDeviceService wsVmsBDeviceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsBDevice get(String id, boolean isNewRecord) {
		return wsVmsBDeviceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsBDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsBDevice wsVmsBDevice, Model model) {
		model.addAttribute("wsVmsBDevice", wsVmsBDevice);
		return "modules/ws/wsVmsBDeviceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsBDevice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsBDevice> listData(WsVmsBDevice wsVmsBDevice, HttpServletRequest request, HttpServletResponse response) {
		wsVmsBDevice.setPage(new Page<>(request, response));
		Page<WsVmsBDevice> page = wsVmsBDeviceService.findPage(wsVmsBDevice);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsBDevice:view")
	@RequestMapping(value = "form")
	public String form(WsVmsBDevice wsVmsBDevice, Model model) {
		model.addAttribute("wsVmsBDevice", wsVmsBDevice);
		return "modules/ws/wsVmsBDeviceForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsBDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsBDevice wsVmsBDevice) {
		wsVmsBDeviceService.save(wsVmsBDevice);
		return renderResult(Global.TRUE, text("保存设备成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsBDevice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsBDevice wsVmsBDevice) {
		wsVmsBDeviceService.delete(wsVmsBDevice);
		return renderResult(Global.TRUE, text("删除设备成功！"));
	}
	
	/**
	 * 列表选择对话框
	 */
	@RequiresPermissions("ws:wsVmsBDevice:view")
	@RequestMapping(value = "wsVmsBDeviceSelect")
	public String wsVmsBDeviceSelect(WsVmsBDevice wsVmsBDevice, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("wsVmsBDevice", wsVmsBDevice);
		return "modules/ws/wsVmsBDeviceSelect";
	}
	
}