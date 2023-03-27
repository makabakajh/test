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
import com.jeesite.modules.ws.entity.WsVmsBMileage;
import com.jeesite.modules.ws.service.WsVmsBMileageService;

/**
 * 里程表Controller
 * @author oqm
 * @version 2023-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsBMileage")
public class WsVmsBMileageController extends BaseController {

	@Autowired
	private WsVmsBMileageService wsVmsBMileageService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsBMileage get(String id, boolean isNewRecord) {
		return wsVmsBMileageService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsBMileage:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsBMileage wsVmsBMileage, Model model) {
		model.addAttribute("wsVmsBMileage", wsVmsBMileage);
		return "modules/ws/wsVmsBMileageList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsBMileage:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsBMileage> listData(WsVmsBMileage wsVmsBMileage, HttpServletRequest request, HttpServletResponse response) {
		wsVmsBMileage.setPage(new Page<>(request, response));
		Page<WsVmsBMileage> page = wsVmsBMileageService.findPage(wsVmsBMileage);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsBMileage:view")
	@RequestMapping(value = "form")
	public String form(WsVmsBMileage wsVmsBMileage, Model model) {
		model.addAttribute("wsVmsBMileage", wsVmsBMileage);
		return "modules/ws/wsVmsBMileageForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsBMileage:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsBMileage wsVmsBMileage) {
		wsVmsBMileageService.save(wsVmsBMileage);
		return renderResult(Global.TRUE, text("保存里程成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsBMileage:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsBMileage wsVmsBMileage) {
		wsVmsBMileageService.delete(wsVmsBMileage);
		return renderResult(Global.TRUE, text("删除里程成功！"));
	}
	
}