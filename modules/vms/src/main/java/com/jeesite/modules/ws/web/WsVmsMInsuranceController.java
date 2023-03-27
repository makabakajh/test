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
import com.jeesite.modules.ws.entity.WsVmsMInsurance;
import com.jeesite.modules.ws.service.WsVmsMInsuranceService;

/**
 * 保险表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMInsurance")
public class WsVmsMInsuranceController extends BaseController {

	@Autowired
	private WsVmsMInsuranceService wsVmsMInsuranceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMInsurance get(String id, boolean isNewRecord) {
		return wsVmsMInsuranceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMInsurance:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMInsurance wsVmsMInsurance, Model model) {
		model.addAttribute("wsVmsMInsurance", wsVmsMInsurance);
		return "modules/ws/wsVmsMInsuranceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMInsurance:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMInsurance> listData(WsVmsMInsurance wsVmsMInsurance, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMInsurance.setPage(new Page<>(request, response));
		Page<WsVmsMInsurance> page = wsVmsMInsuranceService.findPage(wsVmsMInsurance);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMInsurance:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMInsurance wsVmsMInsurance, Model model) {
		model.addAttribute("wsVmsMInsurance", wsVmsMInsurance);
		return "modules/ws/wsVmsMInsuranceForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMInsurance:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMInsurance wsVmsMInsurance) {
		wsVmsMInsuranceService.save(wsVmsMInsurance);
		return renderResult(Global.TRUE, text("保存保险表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMInsurance:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMInsurance wsVmsMInsurance) {
		wsVmsMInsuranceService.delete(wsVmsMInsurance);
		return renderResult(Global.TRUE, text("删除保险表成功！"));
	}
	
}