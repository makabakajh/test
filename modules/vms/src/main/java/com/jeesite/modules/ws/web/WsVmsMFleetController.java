package com.jeesite.modules.ws.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.ws.util.CodeUtils;
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
import com.jeesite.modules.ws.entity.WsVmsMFleet;
import com.jeesite.modules.ws.service.WsVmsMFleetService;

/**
 * 车队表Controller
 * @author oqm
 * @version 2023-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMFleet")
public class WsVmsMFleetController extends BaseController {

	@Autowired
	private WsVmsMFleetService wsVmsMFleetService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMFleet get(String id, boolean isNewRecord) {
		return wsVmsMFleetService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMFleet:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMFleet wsVmsMFleet, Model model) {
		model.addAttribute("wsVmsMFleet", wsVmsMFleet);
		return "modules/ws/wsVmsMFleetList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMFleet:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMFleet> listData(WsVmsMFleet wsVmsMFleet, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMFleet.setPage(new Page<>(request, response));
		Page<WsVmsMFleet> page = wsVmsMFleetService.findPage(wsVmsMFleet);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMFleet:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMFleet wsVmsMFleet, Model model) {
		model.addAttribute("wsVmsMFleet", wsVmsMFleet);
		return "modules/ws/wsVmsMFleetForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMFleet:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMFleet wsVmsMFleet) {
		String code = CodeUtils.getCode();
		wsVmsMFleet.setFleetCode(code);
		wsVmsMFleetService.save(wsVmsMFleet);
		return renderResult(Global.TRUE, text("保存车队表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMFleet:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMFleet wsVmsMFleet) {
		wsVmsMFleetService.delete(wsVmsMFleet);
		return renderResult(Global.TRUE, text("删除车队表成功！"));
	}
	
	/**
	 * 列表选择对话框
	 */
	@RequiresPermissions("ws:wsVmsMFleet:view")
	@RequestMapping(value = "wsVmsMFleetSelect")
	public String wsVmsMFleetSelect(WsVmsMFleet wsVmsMFleet, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("wsVmsMFleet", wsVmsMFleet);
		return "modules/ws/wsVmsMFleetSelect";
	}
	
}