package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Data;

/**
 * 车辆司机关联表Entity
 * @author oqm
 * @version 2023-02-03
 */
@Table(name="ws_vms_r_vehicle_drives", alias="a", label="车辆司机关联表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆Id"),
		@Column(name="driver_id", attrName="driverId", label="司机id"),
		@Column(name="driver_type", attrName="driverType", label="司机类型"),
		@Column(includeEntity=DataEntity.class),
	},
		joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "b",
		on="a.vehicle_id=b.id",attrName = "vehicle",
		columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN,entity = WsVmsBDrivers.class,alias = "c",
		on="a.driver_id=c.id",attrName = "driver",
		columns ={@Column(name="driver_name", attrName="driverName", label="司机姓名", queryType=QueryType.LIKE)})
		}
	,orderBy="a.update_date DESC"
)
@Data
public class WsVmsRVehicleDrives extends DataEntity<WsVmsRVehicleDrives> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// 车辆Id
	private String driverId;		// 司机id
	private String driverType;		// 司机类型
	private String mainDriverId;
	private String mainDriverName; //主驾
	private String assistDriverId;
	private String assistDriverName; //副驾

	private WsVmsBVehicle vehicle;
	private WsVmsBDrivers driver;
	public WsVmsRVehicleDrives() {
		this(null);
	}
	
	public WsVmsRVehicleDrives(String id){
		super(id);
	}
	
	@Size(min=0, max=100, message="车辆Id长度不能超过 100 个字符")
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@Size(min=0, max=100, message="司机id长度不能超过 100 个字符")
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	@Size(min=0, max=100, message="司机类型长度不能超过 100 个字符")
	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
	
}