package com.jeesite.modules.ws.web;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.file.entity.FileUpload;
import com.jeesite.modules.file.service.FileUploadService;
import com.jeesite.modules.ws.entity.WsVmsBMileage;
import com.jeesite.modules.ws.entity.WsVmsBVehicle;
import com.jeesite.modules.ws.service.WsVmsBMileageService;
import com.jeesite.modules.ws.service.WsVmsBVehicleService;
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
import com.jeesite.modules.ws.entity.WsVmsMOil;
import com.jeesite.modules.ws.service.WsVmsMOilService;

/**
 * 加油记录Controller
 * @author oqm
 * @version 2023-02-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsMOil")
public class WsVmsMOilController extends BaseController {

	@Autowired
	private WsVmsMOilService wsVmsMOilService;
	@Resource
	private FileUploadService fileUploadService;
	@Resource
	private WsVmsBMileageService wsVmsBMileageService;
	@Resource
	private WsVmsBVehicleService wsVmsBVehicleService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsMOil get(String id, boolean isNewRecord) {
		return wsVmsMOilService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */

	@RequestMapping(value = {"list", ""})
	public String list(WsVmsMOil wsVmsMOil, Model model) {
		model.addAttribute("wsVmsMOil", wsVmsMOil);
		return "modules/ws/wsVmsMOilList";
	}
	
	/**
	 * 查询列表数据
	 */

	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsMOil> listData(WsVmsMOil wsVmsMOil, HttpServletRequest request, HttpServletResponse response) {
		wsVmsMOil.setPage(new Page<>(request, response));
		Page<WsVmsMOil> page = wsVmsMOilService.findPage(wsVmsMOil);
		List<WsVmsMOil> list = page.getList();
		for (int i = 0; i <list.size() ; i++) {
			WsVmsMOil vmsMOil = list.get(i);
			FileUpload fileUpload = new FileUpload();
			fileUpload.setBizType("wsVmsMOil_image");
			fileUpload.setBizKey(vmsMOil.getId());
			List<FileUpload> fileUploads = fileUploadService.findList(fileUpload);
			List<String> fileUrls = vmsMOil.getFileUrls();
			for (int j = 0; j < fileUploads.size(); j++) {
				FileUpload file= fileUploads.get(j);
				String fileUrl = file.getFileUrl();
				fileUrls.add(fileUrl);
			}
		}
		return page;
	}

	/**wsVmsMOil
	 * 查看编辑表单
	 */

	@RequestMapping(value = "form")
	public String form(WsVmsMOil wsVmsMOil, Model model) {
		model.addAttribute("wsVmsMOil", wsVmsMOil);
		return "modules/ws/wsVmsMOilForm";

	}

	/**
	 * 保存数据
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsMOil wsVmsMOil, String fileId, WsVmsBMileage wsVmsBMileage) {
		//先查询下车辆之前是否有加油记录
		String vehicleId = wsVmsMOil.getVehicleId();
		List<WsVmsMOil> oilList = wsVmsMOilService.findByVehicleId(vehicleId);
		if(oilList.size()==0){//第一次加油登记，登记初始里程
			Double mileage = wsVmsBMileage.getMileage();
			WsVmsBVehicle vehicle = wsVmsBVehicleService.get(vehicleId);
			vehicle.setInitMileage(mileage);
			wsVmsBVehicleService.update(vehicle);
		}
		wsVmsMOilService.save(wsVmsMOil);
		if(StringUtils.isNotBlank(fileId)){//图片
			FileUpload fileUpload = fileUploadService.get(fileId);
			fileUpload.setBizKey(wsVmsMOil.getId());
			fileUpload.setBizType("wsVmsMOil_image");
			fileUploadService.save(fileUpload);
		}
//		if(wsVmsBMileage.getIsNewRecord()){
//			wsVmsBMileage.setMileageType("0");
//			wsVmsBMileage.setDate(wsVmsMOil.getOilDate());
//			wsVmsBMileageService.save(wsVmsBMileage);
//		}
		return renderResult(Global.TRUE, text("保存加油记录成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsMOil:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsMOil wsVmsMOil) {
		wsVmsMOilService.delete(wsVmsMOil);
		return renderResult(Global.TRUE, text("删除加油记录成功！"));
	}
	
}