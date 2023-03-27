package com.jeesite.modules.ws.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.ws.entity.WsVmsBDrivers;
import com.jeesite.modules.ws.service.WsVmsBDriversService;
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
import com.jeesite.modules.ws.entity.WsVmsRVehicleDrives;
import com.jeesite.modules.ws.service.WsVmsRVehicleDrivesService;

/**
 * 车辆司机关联表Controller
 * @author oqm
 * @version 2023-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsRVehicleDrives")
public class WsVmsRVehicleDrivesController extends BaseController {

	@Autowired
	private WsVmsRVehicleDrivesService wsVmsRVehicleDrivesService;
	@Resource
	private WsVmsBDriversService wsVmsBDriversService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsRVehicleDrives get(String id, boolean isNewRecord) {
		return wsVmsRVehicleDrivesService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDrives:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsRVehicleDrives wsVmsRVehicleDrives, Model model) {
		model.addAttribute("wsVmsRVehicleDrives", wsVmsRVehicleDrives);
		return "modules/ws/wsVmsRVehicleDrivesList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDrives:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsRVehicleDrives> listData(WsVmsRVehicleDrives wsVmsRVehicleDrives, HttpServletRequest request, HttpServletResponse response) {
		wsVmsRVehicleDrives.setPage(new Page<>(request, response));
		Page<WsVmsRVehicleDrives> page = wsVmsRVehicleDrivesService.findPage(wsVmsRVehicleDrives);
		/*List<WsVmsRVehicleDrives> list = page.getList();
		for (int i = 0; i <list.size() ; i++) {
			WsVmsRVehicleDrives vehicleDrives = list.get(i);
			String driverType = vehicleDrives.getDriverType();
			if("0".equals(driverType)){
				String driverId = vehicleDrives.getDriverId();
				vehicleDrives.setMainDriverId(driverId);
				WsVmsBDrivers drivers = wsVmsBDriversService.get(driverId);
				vehicleDrives.setMainDriverName(drivers.getDriverName());
			}
		}*/
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDrives:view")
	@RequestMapping(value = "form")
	public String form(WsVmsRVehicleDrives wsVmsRVehicleDrives, Model model) {
		model.addAttribute("wsVmsRVehicleDrives", wsVmsRVehicleDrives);
		return "modules/ws/wsVmsRVehicleDrivesForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDrives:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		String assistDriverId = wsVmsRVehicleDrives.getAssistDriverId();
		String mainDriverId = wsVmsRVehicleDrives.getMainDriverId();
		//司机未选择
		if(StringUtils.isBlank(assistDriverId)&&StringUtils.isBlank(mainDriverId)){
			return renderResult(Global.FALSE, text("请先选择需要绑定的司机！"));
		}
		ArrayList<WsVmsRVehicleDrives> list = new ArrayList<>();
		String mainVehicleId = wsVmsRVehicleDrives.getMainDriverId();
		String vehicleId = wsVmsRVehicleDrives.getVehicleId();
		//车辆已绑定了其他司机为主驾
		List<Map<String, Object>> mainDrivers = wsVmsRVehicleDrivesService.getMainDriverByVehicleId(vehicleId);
		if(mainDrivers.size()>0){
			String driverName =(String) mainDrivers.get(0).get("driver_name");
			return renderResult(Global.FALSE, text("此车辆已绑定"+driverName+"为主驾，请先解绑！"));
		}
		//所选的司机已经绑定过了该车辆
		String assistVehicleIds = wsVmsRVehicleDrives.getAssistDriverId();
		List<String> assistVehicleIdList=new ArrayList<>();
		ArrayList<String> driverList = new ArrayList<>();
		driverList.add(mainVehicleId);
		if(StringUtils.isNotBlank(assistVehicleIds)){
			assistVehicleIdList = Arrays.asList(assistVehicleIds.split(","));
			driverList.addAll(assistVehicleIdList);
		}
		List<String> drivers = wsVmsRVehicleDrivesService.getDriverByDrivers(vehicleId, driverList);
		String driverName="";
		if(drivers.size()>0){
			for (int i = 0; i <drivers.size() ; i++) {
				String s = drivers.get(i);
				if(i<drivers.size()-1){
					driverName=driverName+s+",";
				}else{
					driverName=driverName+s;
				}
			}
			return renderResult(Global.FALSE, text("该车辆已绑定了"+driverName+",请重新选择！"));
		}
		//该司机已经绑定为了其他车辆的主驾
		String mainDriverName = wsVmsRVehicleDrivesService.getDriverByMainDriverId(mainDriverId);
		if(StringUtils.isNotBlank(mainDriverName)){
			return renderResult(Global.FALSE, text("改司机已绑定为其他车辆的主驾，请重新选择！"));
		}
		if(StringUtils.isNotBlank(mainVehicleId)){
			wsVmsRVehicleDrives.setDriverId(mainVehicleId);
			wsVmsRVehicleDrives.setVehicleId(vehicleId);
			wsVmsRVehicleDrives.setDriverType("0");
			list.add(wsVmsRVehicleDrives);
		}
		for (int i = 0; i <assistVehicleIdList.size() ; i++) {
			WsVmsRVehicleDrives vehicleDrive = new WsVmsRVehicleDrives();
			vehicleDrive.setVehicleId(vehicleId);
			vehicleDrive.setDriverId(assistVehicleIdList.get(i));
			vehicleDrive.setDriverType("1");
			vehicleDrive.setRemarks(wsVmsRVehicleDrives.getRemarks());
			list.add(vehicleDrive);
		}
		wsVmsRVehicleDrivesService.insertBatch(list);
		return renderResult(Global.TRUE, text("绑定司机成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsRVehicleDrives:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsRVehicleDrives wsVmsRVehicleDrives) {
		wsVmsRVehicleDrivesService.delete(wsVmsRVehicleDrives);
		return renderResult(Global.TRUE, text("解绑司机成功！"));
	}
	
}