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
import com.jeesite.modules.ws.entity.WsVmsBDrivers;
import com.jeesite.modules.ws.service.WsVmsBDriversService;

/**
 * 司机表Controller
 * @author oqm
 * @version 2023-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsBDrivers")
public class WsVmsBDriversController extends BaseController {

	@Autowired
	private WsVmsBDriversService wsVmsBDriversService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsBDrivers get(String id, boolean isNewRecord) {
		return wsVmsBDriversService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsBDrivers:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsBDrivers wsVmsBDrivers, Model model) {
		model.addAttribute("wsVmsBDrivers", wsVmsBDrivers);
		return "modules/ws/wsVmsBDriversList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsBDrivers:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsBDrivers> listData(WsVmsBDrivers wsVmsBDrivers, HttpServletRequest request, HttpServletResponse response) {
		wsVmsBDrivers.setPage(new Page<>(request, response));
		Page<WsVmsBDrivers> page = wsVmsBDriversService.findPage(wsVmsBDrivers);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsBDrivers:view")
	@RequestMapping(value = "form")
	public String form(WsVmsBDrivers wsVmsBDrivers, Model model) {
		model.addAttribute("wsVmsBDrivers", wsVmsBDrivers);
		return "modules/ws/wsVmsBDriversForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsBDrivers:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsBDrivers wsVmsBDrivers) {
		wsVmsBDriversService.save(wsVmsBDrivers);
		return renderResult(Global.TRUE, text("保存司机表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsBDrivers:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsBDrivers wsVmsBDrivers) {
		wsVmsBDriversService.delete(wsVmsBDrivers);
		return renderResult(Global.TRUE, text("删除司机表成功！"));
	}
	
	/**
	 * 列表选择对话框
	 */
	@RequiresPermissions("ws:wsVmsBDrivers:view")
	@RequestMapping(value = "wsVmsBDriversSelect")
	public String wsVmsBDriversSelect(WsVmsBDrivers wsVmsBDrivers, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("wsVmsBDrivers", wsVmsBDrivers);
		return "modules/ws/wsVmsBDriversSelect";
	}
	
}