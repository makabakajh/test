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
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.ws.entity.WsVmsMVehicleLicense;
import com.jeesite.modules.ws.service.WsVmsMVehicleLicenseService;

/**
 * 行驶证Controller
 * @author oqm
 * @version 2023-02-14
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMVehicleLicense")
public class WsVmsMVehicleLicenseController extends BaseController {

	@Autowired
	private WsVmsMVehicleLicenseService wsVmsMVehicleLicenseService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMVehicleLicense get(String id, boolean isNewRecord) {
		return wsVmsMVehicleLicenseService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMVehicleLicense:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMVehicleLicense wsVmsMVehicleLicense, Model model) {
		model.addAttribute("wsVmsMVehicleLicense", wsVmsMVehicleLicense);
		return "modules/ws/wsVmsMVehicleLicenseList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMVehicleLicense:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMVehicleLicense> listData(WsVmsMVehicleLicense wsVmsMVehicleLicense, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMVehicleLicense.setPage(new Page<>(request, response));
		Page<WsVmsMVehicleLicense> page = wsVmsMVehicleLicenseService.findPage(wsVmsMVehicleLicense);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMVehicleLicense:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMVehicleLicense wsVmsMVehicleLicense, Model model) {
		model.addAttribute("wsVmsMVehicleLicense", wsVmsMVehicleLicense);
		return "modules/ws/wsVmsMVehicleLicenseForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMVehicleLicense:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		wsVmsMVehicleLicenseService.save(wsVmsMVehicleLicense);
		return renderResult(Global.TRUE, text("保存行驶证成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMVehicleLicense:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMVehicleLicense wsVmsMVehicleLicense) {
		wsVmsMVehicleLicenseService.delete(wsVmsMVehicleLicense);
		return renderResult(Global.TRUE, text("删除行驶证成功！"));
	}
	
}