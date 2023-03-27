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
import com.jeesite.modules.ws.entity.WsVmsMRegulations;
import com.jeesite.modules.ws.service.WsVmsMRegulationsService;

/**
 * 违章表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMRegulations")
public class WsVmsMRegulationsController extends BaseController {

	@Autowired
	private WsVmsMRegulationsService wsVmsMRegulationsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMRegulations get(String id, boolean isNewRecord) {
		return wsVmsMRegulationsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMRegulations:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMRegulations wsVmsMRegulations, Model model) {
		model.addAttribute("wsVmsMRegulations", wsVmsMRegulations);
		return "modules/ws/wsVmsMRegulationsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMRegulations:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMRegulations> listData(WsVmsMRegulations wsVmsMRegulations, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMRegulations.setPage(new Page<>(request, response));
		Page<WsVmsMRegulations> page = wsVmsMRegulationsService.findPage(wsVmsMRegulations);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMRegulations:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMRegulations wsVmsMRegulations, Model model) {
		model.addAttribute("wsVmsMRegulations", wsVmsMRegulations);
		return "modules/ws/wsVmsMRegulationsForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMRegulations:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMRegulations wsVmsMRegulations) {
		wsVmsMRegulationsService.save(wsVmsMRegulations);
		return renderResult(Global.TRUE, text("保存违章表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMRegulations:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMRegulations wsVmsMRegulations) {
		wsVmsMRegulationsService.delete(wsVmsMRegulations);
		return renderResult(Global.TRUE, text("删除违章表成功！"));
	}
	
}