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
import com.jeesite.modules.ws.entity.WsVmsMOilRecharge;
import com.jeesite.modules.ws.service.WsVmsMOilRechargeService;

/**
 * 油卡充值记录Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMOilRecharge")
public class WsVmsMOilRechargeController extends BaseController {

	@Autowired
	private WsVmsMOilRechargeService wsVmsMOilRechargeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMOilRecharge get(String id, boolean isNewRecord) {
		return wsVmsMOilRechargeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMOilRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMOilRecharge wsVmsMOilRecharge, Model model) {
		model.addAttribute("wsVmsMOilRecharge", wsVmsMOilRecharge);
		return "modules/ws/wsVmsMOilRechargeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMOilRecharge:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMOilRecharge> listData(WsVmsMOilRecharge wsVmsMOilRecharge, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMOilRecharge.setPage(new Page<>(request, response));
		Page<WsVmsMOilRecharge> page = wsVmsMOilRechargeService.findPage(wsVmsMOilRecharge);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMOilRecharge:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMOilRecharge wsVmsMOilRecharge, Model model) {
		model.addAttribute("wsVmsMOilRecharge", wsVmsMOilRecharge);
		return "modules/ws/wsVmsMOilRechargeForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMOilRecharge:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMOilRecharge wsVmsMOilRecharge) {
		wsVmsMOilRechargeService.save(wsVmsMOilRecharge);
		return renderResult(Global.TRUE, text("保存油卡充值记录成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMOilRecharge:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMOilRecharge wsVmsMOilRecharge) {
		wsVmsMOilRechargeService.delete(wsVmsMOilRecharge);
		return renderResult(Global.TRUE, text("删除油卡充值记录成功！"));
	}
	
}