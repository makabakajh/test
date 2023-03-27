package com.jeesite.modules.ws.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

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
import com.jeesite.modules.ws.entity.WsVmsRVehicleDevice;
import com.jeesite.modules.ws.service.WsVmsRVehicleDeviceService;

/**
 * 车辆设备关联表Controller
 * @author oqm
 * @version 2023-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsRVehicleDevice")
public class WsVmsRVehicleDeviceController extends BaseController {

	@Autowired
	private WsVmsRVehicleDeviceService wsVmsRVehicleDeviceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsRVehicleDevice get(String id, boolean isNewRecord) {
		return wsVmsRVehicleDeviceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsRVehicleDevice wsVmsRVehicleDevice, Model model) {
		model.addAttribute("wsVmsRVehicleDevice", wsVmsRVehicleDevice);
		return "modules/ws/wsVmsRVehicleDeviceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDevice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsRVehicleDevice> listData(WsVmsRVehicleDevice wsVmsRVehicleDevice, HttpServletRequest request, HttpServletResponse response) {
		wsVmsRVehicleDevice.setPage(new Page<>(request, response));
		Page<WsVmsRVehicleDevice> page = wsVmsRVehicleDeviceService.findPage(wsVmsRVehicleDevice);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDevice:view")
	@RequestMapping(value = "form")
	public String form(WsVmsRVehicleDevice wsVmsRVehicleDevice, Model model) {
		model.addAttribute("wsVmsRVehicleDevice", wsVmsRVehicleDevice);
		return "modules/ws/wsVmsRVehicleDeviceForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		List<WsVmsRVehicleDevice> list = wsVmsRVehicleDeviceService.findList(wsVmsRVehicleDevice);
		if(list.size()>0){
			return renderResult(Global.FALSE, text("该车已绑定了此设备！"));
		}
		WsVmsRVehicleDevice vehicleDevice = new WsVmsRVehicleDevice();
		vehicleDevice.setDeviceId(wsVmsRVehicleDevice.getDeviceId());
		List<WsVmsRVehicleDevice> vehicleDevices = wsVmsRVehicleDeviceService.findList(vehicleDevice);
		if(vehicleDevices.size()>0){
			return renderResult(Global.FALSE, text("该设备已绑定了其他车辆，请重新选择！！"));
		}
		wsVmsRVehicleDeviceService.save(wsVmsRVehicleDevice);
		return renderResult(Global.TRUE, text("绑定成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDevice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsRVehicleDevice wsVmsRVehicleDevice) {
		wsVmsRVehicleDeviceService.delete(wsVmsRVehicleDevice);
		return renderResult(Global.TRUE, text("删除车辆设备关联表成功！"));
	}
	
}