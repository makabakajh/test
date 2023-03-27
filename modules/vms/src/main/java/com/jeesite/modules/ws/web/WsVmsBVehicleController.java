package com.jeesite.modules.ws.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.http.HttpClientUtils;
import com.jeesite.modules.ws.entity.WsVmsMInsurance;
import com.jeesite.modules.ws.entity.WsVmsMYearInspect;
import com.jeesite.modules.ws.service.WsVmsMInsuranceService;
import com.jeesite.modules.ws.service.WsVmsMOilService;
import com.jeesite.modules.ws.service.WsVmsMYearInspectService;
import com.jeesite.modules.ws.util.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.alibaba.fastjson.JSONValidator;
import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.ws.entity.WsVmsBVehicle;
import com.jeesite.modules.ws.service.WsVmsBVehicleService;

/**
 * 车辆Controller
 * @author oqm
 * @version 2023-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ws/wsVmsBVehicle")
public class WsVmsBVehicleController extends BaseController {
	private  String ak="ZPKvPKzrn9bE3kvGGkGEQiNGVSwtMARX";
	private  String service_id="235065";
	@Autowired
	private WsVmsBVehicleService wsVmsBVehicleService;
	@Resource
	private WsVmsMOilService  wsVmsMOilService;
	@Resource
	private WsVmsMInsuranceService wsVmsMInsuranceService;
	@Resource
	private WsVmsMYearInspectService wsVmsMYearInspectService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WsVmsBVehicle get(String id, boolean isNewRecord) {
		return wsVmsBVehicleService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("ws:wsVmsBVehicle:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsVmsBVehicle wsVmsBVehicle, Model model) {
		model.addAttribute("wsVmsBVehicle", wsVmsBVehicle);
		return "modules/ws/wsVmsBVehicleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("ws:wsVmsBVehicle:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WsVmsBVehicle> listData(WsVmsBVehicle wsVmsBVehicle, HttpServletRequest request, HttpServletResponse response) {
		wsVmsBVehicle.setPage(new Page<>(request, response));
		Page<WsVmsBVehicle> page = wsVmsBVehicleService.findPage(wsVmsBVehicle);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("ws:wsVmsBVehicle:view")
	@RequestMapping(value = "form")
	public String form(WsVmsBVehicle wsVmsBVehicle, Model model) {
		model.addAttribute("wsVmsBVehicle", wsVmsBVehicle);
		return "modules/ws/wsVmsBVehicleForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("ws:wsVmsBVehicle:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WsVmsBVehicle wsVmsBVehicle) {
		wsVmsBVehicleService.save(wsVmsBVehicle);
		return renderResult(Global.TRUE, text("保存车辆成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("ws:wsVmsBVehicle:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WsVmsBVehicle wsVmsBVehicle) {
		wsVmsBVehicleService.delete(wsVmsBVehicle);
		return renderResult(Global.TRUE, text("删除车辆成功！"));
	}
	
	/**
	 * 列表选择对话框
	 */
	@RequiresPermissions("ws:wsVmsBVehicle:view")
	@RequestMapping(value = "wsVmsBVehicleSelect")
	public String wsVmsBVehicleSelect(WsVmsBVehicle wsVmsBVehicle, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("wsVmsBVehicle", wsVmsBVehicle);
		return "modules/ws/wsVmsBVehicleSelect";
	}

	/*
	 * @Author oqm
	 * @Date 2023/2/7
	 * @Param
	 * @return
	 * @Description 根据设备号查询车牌号
	 **/
	@RequestMapping(value = "getLicensePlate")
	@ResponseBody
	public List<Map<String,Object>> getLicensePlate(@RequestBody List<String> deviceCodes) {
		List<Map<String,Object>> licenseList = wsVmsBVehicleService.getLicenseByDeviceCode(deviceCodes);
		return licenseList;
	}





	/*
	 * @Author oqm
	 * @Date 2023/2/24
	 * @Param dateType (0=每月、1=每季度)，startDate开始时间 endDate结束时间 ，countType （0=费用，1=车辆）
	 * @return 
	 * @Description 车辆费用统计
	 **/
    @RequestMapping(value="vehicleCostStatistics")
	@ResponseBody
    public ArrayList<JSONObject> vehicleCostStatistics(String dateType,String countType,String startDate,String endDate){
		List<Map<String, Object>> list =new ArrayList<>();
		ArrayList<JSONObject> dataList =new ArrayList<>();
		//根据其实日期和结束日期查出中间的所有日期（传入的日期格式只能是yyyy-MM-dd） //前端传入的格式为2023-01 ，所以需要拼接为2023-01-01
		ArrayList<String> dateList = DateUtils.getAllDateByParamDate(startDate+"-01", endDate+"-01", "month");
		if("0".equals(dateType)&&"0".equals(countType)){//每个月的所有费用
			list = wsVmsBVehicleService.getTotalCostByMonth(startDate, endDate);
			for (int i = 0; i <list.size() ; i++) {
				Map<String, Object> map = list.get(i);
				String costName = (String)map.get("costName");
				String cost = (String)map.get("cost");
			}
		}
		if("1".equals(dateType)&&"0".equals(countType)){//每季度的所有费用
			list = wsVmsBVehicleService.getTotalCostByQuarter(startDate,endDate);
		}
		if("0".equals(dateType)&&"1".equals(countType)){//每个月每辆车的费用
			list = wsVmsBVehicleService.getVehicleCostByMonth(startDate,endDate);
		}
		if("1".equals(dateType)&&"1".equals(countType)){//每季度每辆车的费用
			list = wsVmsBVehicleService.getVehicleCostByQuarter(startDate,endDate);
		}
		/*//遍历每个月
		if("0".equals(dateType)){
			for (int i = 0; i <dateList.size() ; i++) {
				String dateTime = dateList.get(i);
				JSONObject jsonObject = countTotalCost(dateTime, list);
				dataList.add(jsonObject);
			}
		}
		//遍历每个季度
		if("1".equals(dateType)){
			for (int i = 1; i < 5; i++) {
				String dateTime = i+"";
				JSONObject jsonObject = countTotalCost(dateTime, list);
				dataList.add(jsonObject);
			}
		}*/
		return dataList;
	}

	/*
	 * @Author oqm
	 * @Date 2023/3/15
	 * @Param
	 * @return
	 * @Description 组装花费数据
	 **/
	private JSONObject countTotalCost(String time,List<Map<String, Object>> list){
		JSONObject jsonObject = new JSONObject();
		ArrayList<Object> arrayList = new ArrayList<>();
		jsonObject.put("time",time);
		BigDecimal totalCost=new BigDecimal("0");
		Map<String, Object> map1 = new HashMap<>();
		arrayList.add(map1);
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = list.get(j);
			String date = (String)map.get("date");
			BigDecimal cost =(BigDecimal) map.get("cost");
			if(date.equals(time)){
				arrayList.add(map);
				if(cost!=null){
					totalCost=totalCost.add(cost);
				}
			}
		}
		map1.put("cost",totalCost);
		map1.put("costName","总成本");
		jsonObject.put("data",arrayList);
		return jsonObject;
	}
	/*
	 * @Author oqm
	 * @Date 2023/2/20
	 * @Param
	 * @return
	 * @Description 前端获取车辆列表
	 **/
	@RequestMapping(value="vehicleList")
	@ResponseBody
	public JSONObject vehicleList(){
		JSONObject jsonObject = new JSONObject();
		List<Map<String, Object>> vehicleList = getVehicleList();
		jsonObject.put("vehicleList",vehicleList);
		ArrayList<JSONObject> toDayVehicle = getToDayVehicle();
		jsonObject.put("toDayVehicle",toDayVehicle);
		return jsonObject;
	}

	/*
	 * @Author oqm
	 * @Date 2023/3/14
	 * @Param
	 * @return
	 * @Description 获取车辆详情
	 **/
	@RequestMapping(value="getVehicleInfo")
	@ResponseBody
	public Map<String,Object> getVehicleInfo(String vehicleId){
		Map<String, Object> vehicle = wsVmsBVehicleService.getVehicleInfoByVehicleId(vehicleId);
		WsVmsMInsurance insurance = wsVmsMInsuranceService.getLastInsuranceByVehicleId(vehicleId);
		if(insurance!=null){
			vehicle.put("insuranceDate",insurance.getInsuranceDate());
			vehicle.put("insuranceCompany",insurance.getCompany());
			vehicle.put("insExpirationDate",insurance.getExpirationDate());
		}
		WsVmsMYearInspect inspect= wsVmsMYearInspectService.getLastInspectByVehicleId(vehicleId);
		if(inspect!=null){
			vehicle.put("inspectExpireDate",inspect.getExpireDate());
		}
		return vehicle;
	}

	/*
	 * @Author oqm
	 * @Date 2023/2/22
	 * @Param
	 * @return
	 * @Description 查看单个车辆费用
	 **/
	@RequestMapping(value="vehicleCost")
	@ResponseBody
	public List<Map<String,Object>> vehicleCost(String vehicleId,String date){
		List<Map<String, Object>> vehicleCost = wsVmsBVehicleService.getVehicleCost(vehicleId, date);
		return vehicleCost;
	}
	/*
	 * @Author oqm
	 * @Date 2023/3/14
	 * @Param
	 * @return
	 * @Description 获取车辆列表
	 **/
	private List<Map<String,Object>> getVehicleList(){
		List<Map<String,Object>> vehicleList = wsVmsBVehicleService.getVehicleList();
		for (int i = 0; i < vehicleList.size(); i++) {
			Map<String, Object> map = vehicleList.get(i);
			String vehicleId = (String)map.get("id");
			BigDecimal initMileage = (BigDecimal)map.get("initMileage");
			BigDecimal currentMileage = (BigDecimal)map.get("currentMileage");
			if(initMileage!=null&&currentMileage!=null){
				//跑的里程
				BigDecimal mileage = currentMileage.subtract(initMileage);
				if(mileage.compareTo(BigDecimal.ZERO)!=0){
					//查询总的加油油费
					Map<String, Object> amountMap = wsVmsMOilService.getAmountByVehicleId(vehicleId);
					BigDecimal total =(BigDecimal) amountMap.get("total");
					BigDecimal last =(BigDecimal) amountMap.get("last");
					BigDecimal amount=total.subtract(last);
					//每公里油耗
					BigDecimal oilCons = new BigDecimal(amount.toString()).divide(mileage, 2, BigDecimal.ROUND_HALF_UP);
					map.put("oilCons",oilCons);
				}
			}
			Date date = (Date)map.get("date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(currentMileage!=null){
				String reportDate ="";
				if(date!=null){
					reportDate = sdf.format(date);
				}
				//查询最后一次加油后，鹰眼系统车辆跑的总公里数
				Map<String, Object> mileageMap = wsVmsBVehicleService.getYingYanMileageByVehicleId(vehicleId, reportDate);
				if(mileageMap!=null){
					BigDecimal yyMileage =(BigDecimal)mileageMap.get("totalMileage");
					//总里程=当前实表里程+鹰眼里程
					BigDecimal totalMileage = currentMileage.add(yyMileage);
					map.put("totalMileage",totalMileage);
				}
			}
		}
		return vehicleList;
	}

	/*
	 * @Author oqm
	 * @Date 2023/3/14
	 * @Param
	 * @return
	 * @Description 今日出车列表
	 **/
	public ArrayList<JSONObject> getToDayVehicle(){
		String baseUrl="https://yingyan.baidu.com/api/v3/track/getdistance";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		String startDate=today+" 00:00:00";
		String startTime = DateUtils.dateToStamp(startDate);
		String endDate=today+" 23:59:59";
		String endTime=DateUtils.dateToStamp(endDate);
		List<Map<String, Object>> deviceList = wsVmsBVehicleService.getDeviceList();
		ArrayList<JSONObject> list = new ArrayList<>();
		for (int i = 0; i <deviceList.size() ; i++) {
			Map<String, Object> map = deviceList.get(i);
			String deviceCode = (String)map.get("deviceCode");
			String vehicleId = (String)map.get("vehicleId");
			String params="?ak="+ak+"&service_id="+service_id+"&entity_name="+deviceCode+"&start_time="+startTime+"&end_time="+endTime;
			String url=baseUrl+params;
			String result = HttpClientUtils.get(url);
			JSONObject jsonObject = JSONObject.parseObject(result);
			String status = jsonObject.getString("status");
			if("0".equals(status)){
				Double distance = jsonObject.getDouble("distance")/1000;
				BigDecimal dis = new BigDecimal(distance);
				Double dist = dis.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				if(dist!=0){
					String pointUrl="https://yingyan.baidu.com/api/v3/track/getlatestpoint?ak="+ak+"&service_id="+service_id+"&entity_name="+deviceCode;
					String s = HttpClientUtils.get(pointUrl);
					JSONObject pointResult = JSONObject.parseObject(s);
					if("0".equals(status)){
						JSONObject latestPoint = pointResult.getJSONObject("latest_point");
						latestPoint.put("distance",dist);
						String speed = latestPoint.getString("speed");
						if("0".equals(speed)){
							latestPoint.put("status",0);
						}else{
							latestPoint.put("status",1);
						}
						Map<String, String> vehicle = wsVmsBVehicleService.getVehicleDriverByVehicleId(vehicleId);
						latestPoint.put("license_plate",vehicle.get("license_plate"));
						latestPoint.put("driver_name",vehicle.get("driver_name"));
						list.add(latestPoint);
					}
				}
			}
		}
		return list;
	}


}