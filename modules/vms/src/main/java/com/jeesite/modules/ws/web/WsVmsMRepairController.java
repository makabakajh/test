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
import com.jeesite.modules.ws.entity.WsVmsMRepair;
import com.jeesite.modules.ws.service.WsVmsMRepairService;

/**
 * 维修保养表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMRepair")
public class WsVmsMRepairController extends BaseController {

	@Autowired
	private WsVmsMRepairService wsVmsMRepairService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMRepair get(String id, boolean isNewRecord) {
		return wsVmsMRepairService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMRepair:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMRepair wsVmsMRepair, Model model) {
		model.addAttribute("wsVmsMRepair", wsVmsMRepair);
		return "modules/ws/wsVmsMRepairList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMRepair:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMRepair> listData(WsVmsMRepair wsVmsMRepair, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMRepair.setPage(new Page<>(request, response));
		Page<WsVmsMRepair> page = wsVmsMRepairService.findPage(wsVmsMRepair);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMRepair:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMRepair wsVmsMRepair, Model model) {
		model.addAttribute("wsVmsMRepair", wsVmsMRepair);
		return "modules/ws/wsVmsMRepairForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMRepair:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMRepair wsVmsMRepair) {
		wsVmsMRepairService.save(wsVmsMRepair);
		return renderResult(Global.TRUE, text("保存维修保养表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMRepair:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMRepair wsVmsMRepair) {
		wsVmsMRepairService.delete(wsVmsMRepair);
		return renderResult(Global.TRUE, text("删除维修保养表成功！"));
	}
	
}