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
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.ws.entity.WsVmsMUse;
import com.jeesite.modules.ws.service.WsVmsMUseService;

/**
 * 用车表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMUse")
public class WsVmsMUseController extends BaseController {

	@Autowired
	private WsVmsMUseService wsVmsMUseService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMUse get(String id, boolean isNewRecord) {
		return wsVmsMUseService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMUse:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMUse wsVmsMUse, Model model) {
		model.addAttribute("wsVmsMUse", wsVmsMUse);
		return "modules/ws/wsVmsMUseList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMUse:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMUse> listData(WsVmsMUse wsVmsMUse, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMUse.setPage(new Page<>(request, response));
		Page<WsVmsMUse> page = wsVmsMUseService.findPage(wsVmsMUse);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMUse:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMUse wsVmsMUse, Model model) {
		model.addAttribute("wsVmsMUse", wsVmsMUse);
		return "modules/ws/wsVmsMUseForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMUse:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMUse wsVmsMUse) {
		wsVmsMUse.setuseCode(CodeUtils.getCode());
		wsVmsMUseService.save(wsVmsMUse);
		return renderResult(Global.TRUE, text("保存用车表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMUse:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMUse wsVmsMUse) {
		wsVmsMUseService.delete(wsVmsMUse);
		return renderResult(Global.TRUE, text("删除用车表成功！"));
	}
	
}