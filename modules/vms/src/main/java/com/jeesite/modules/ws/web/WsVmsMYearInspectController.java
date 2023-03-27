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
import com.jeesite.modules.ws.entity.WsVmsMYearInspect;
import com.jeesite.modules.ws.service.WsVmsMYearInspectService;

/**
 * 年检表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMYearInspect")
public class WsVmsMYearInspectController extends BaseController {

	@Autowired
	private WsVmsMYearInspectService wsVmsMYearInspectService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMYearInspect get(String id, boolean isNewRecord) {
		return wsVmsMYearInspectService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMYearInspect:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMYearInspect wsVmsMYearInspect, Model model) {
		model.addAttribute("wsVmsMYearInspect", wsVmsMYearInspect);
		return "modules/ws/wsVmsMYearInspectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMYearInspect:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMYearInspect> listData(WsVmsMYearInspect wsVmsMYearInspect, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMYearInspect.setPage(new Page<>(request, response));
		Page<WsVmsMYearInspect> page = wsVmsMYearInspectService.findPage(wsVmsMYearInspect);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMYearInspect:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMYearInspect wsVmsMYearInspect, Model model) {
		model.addAttribute("wsVmsMYearInspect", wsVmsMYearInspect);
		return "modules/ws/wsVmsMYearInspectForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMYearInspect:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMYearInspect wsVmsMYearInspect) {
		wsVmsMYearInspectService.save(wsVmsMYearInspect);
		return renderResult(Global.TRUE, text("保存年检表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMYearInspect:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMYearInspect wsVmsMYearInspect) {
		wsVmsMYearInspectService.delete(wsVmsMYearInspect);
		return renderResult(Global.TRUE, text("删除年检表成功！"));
	}
	
}