package com.jeesite.modules.ws.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.jeesite.common.entity.Extend;

import java.math.BigDecimal;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Data;

/**
 * 车辆Entity
 * @author oqm
 * @version 2023-02-03
 */
@Table(name="ws_vms_b_vehicle", alias="a", label="车辆信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="license_plate", attrName="licensePlate", label="车牌号"),
		@Column(name="license_color", attrName="licenseColor", label="车牌颜色", isQuery=false),
		@Column(name="vehicle_type", attrName="vehicleType", label="车辆类型", comment="车辆类型（参考字典vehicle_type）"),
		@Column(name="trailer", attrName="trailer", label="挂车车牌号"),
		@Column(name="init_mileage", attrName="initMileage", label="初始里程"),
		@Column(name="fleet_id", attrName="fleetId", label="车队id"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity = Extend.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsMFleet.class,alias = "b",
		on="a.fleet_id=b.id",attrName = "fleet",
		columns = {@Column(name="fleet_name", attrName="fleetName", label="车队名称", queryType=QueryType.LIKE),})
},
		orderBy="a.update_date DESC"
)
@Data
public class WsVmsBVehicle extends DataEntity<WsVmsBVehicle> {
	
	private static final long serialVersionUID = 1L;
	private String licensePlate;		// 车牌号
	private String licenseColor;		// 车牌颜色
	private String vehicleType;		// 车辆类型（参考字典vehicle_type）
	private String trailer;		// 挂车车牌号
	private String fleetId;     //车队id
	private Extend extend;		// 扩展 String
	private Double initMileage;
	private WsVmsMFleet fleet;
	public WsVmsBVehicle() {
		this(null);
	}
	
	public WsVmsBVehicle(String id){
		super(id);
	}
	
	@NotBlank(message="车牌号不能为空")
	@Size(min=0, max=100, message="车牌号长度不能超过 100 个字符")
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	

	public String getLicenseColor() {
		return licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Size(min=0, max=255, message="挂车车牌号长度不能超过 255 个字符")
	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getFleetId() {
		return fleetId;
	}

	public void setFleetId(String fleetId) {
		this.fleetId = fleetId;
	}

	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}