package com.jeesite.modules.ws.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONValidator;
import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.modules.ws.entity.WsVmsBVehicle;
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
import com.jeesite.modules.ws.entity.WsVmsMAccident;
import com.jeesite.modules.ws.service.WsVmsMAccidentService;

/**
 * 事故表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMAccident")
public class WsVmsMAccidentController extends BaseController {

	@Autowired
	private WsVmsMAccidentService wsVmsMAccidentService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMAccident get(String id, boolean isNewRecord) {
		return wsVmsMAccidentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsMAccident:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMAccident wsVmsMAccident, Model model) {
		model.addAttribute("wsVmsMAccident", wsVmsMAccident);
		return "modules/ws/wsVmsMAccidentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsMAccident:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMAccident> listData(WsVmsMAccident wsVmsMAccident, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMAccident.setPage(new Page<>(request, response));
		Page<WsVmsMAccident> page = wsVmsMAccidentService.findPage(wsVmsMAccident);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsMAccident:view")
	@RequestMapping(value = "form")
	public String form(WsVmsMAccident wsVmsMAccident, Model model) {
		model.addAttribute("wsVmsMAccident", wsVmsMAccident);
		return "modules/ws/wsVmsMAccidentForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsMAccident:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMAccident wsVmsMAccident) {
		wsVmsMAccidentService.save(wsVmsMAccident);
		return renderResult(Global.TRUE, text("保存事故表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMAccident:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMAccident wsVmsMAccident) {
		wsVmsMAccidentService.delete(wsVmsMAccident);
		return renderResult(Global.TRUE, text("删除事故表成功！"));
	}

	@RequiresPermissions("ws:wsVmsMAccident:view")
	@RequestMapping(value = "wsVmsMAccidentSelect")
	public String wsVmsMAccidentSelect(WsVmsMAccident wsVmsMAccident,String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("wsVmsMAccident", wsVmsMAccident);
		return "modules/ws/wsVmsMAccidentSelect";
	}
}