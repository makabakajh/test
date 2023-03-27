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
import com.alibaba.fastjson.JSONValidator;
import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.ws.entity.WsVmsBOilCard;
import com.jeesite.modules.ws.service.WsVmsBOilCardService;

/**
 * 油卡表Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsBOilCard")
public class WsVmsBOilCardController extends BaseController {

	@Autowired
	private WsVmsBOilCardService wsVmsBOilCardService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsBOilCard get(String id, boolean isNewRecord) {
		return wsVmsBOilCardService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsBOilCard:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsBOilCard wsVmsBOilCard, Model model) {
		model.addAttribute("wsVmsBOilCard", wsVmsBOilCard);
		return "modules/ws/wsVmsBOilCardList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsBOilCard:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsBOilCard> listData(WsVmsBOilCard wsVmsBOilCard, HttpServletRequest request, HttpServletResponse response) {
		wsVmsBOilCard.setPage(new Page<>(request, response));
		Page<WsVmsBOilCard> page = wsVmsBOilCardService.findPage(wsVmsBOilCard);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsBOilCard:view")
	@RequestMapping(value = "form")
	public String form(WsVmsBOilCard wsVmsBOilCard, Model model) {
		model.addAttribute("wsVmsBOilCard", wsVmsBOilCard);
		return "modules/ws/wsVmsBOilCardForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsBOilCard:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsBOilCard wsVmsBOilCard) {
		wsVmsBOilCardService.save(wsVmsBOilCard);
		return renderResult(Global.TRUE, text("保存油卡表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsBOilCard:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsBOilCard wsVmsBOilCard) {
		wsVmsBOilCardService.delete(wsVmsBOilCard);
		return renderResult(Global.TRUE, text("删除油卡表成功！"));
	}
	
	/**
	 * 列表选择对话框
	 */
	@RequiresPermissions("ws:wsVmsBOilCard:view")
	@RequestMapping(value = "wsVmsBOilCardSelect")
	public String wsVmsBOilCardSelect(WsVmsBOilCard wsVmsBOilCard, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("wsVmsBOilCard", wsVmsBOilCard);
		return "modules/ws/wsVmsBOilCardSelect";
	}
	
}