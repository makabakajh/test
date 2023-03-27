package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Data;

/**
 * 车辆设备关联表Entity
 * @author oqm
 * @version 2023-02-03
 */
@Table(name="ws_vms_r_vehicle_device", alias="a", label="车辆设备关联表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id"),
		@Column(name="device_id", attrName="deviceId", label="设备id"),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "b",
		on="a.vehicle_id=b.id",attrName = "vehicle",
		columns = {@Column(name="license_plate", attrName="licensePlate", label="车牌号",queryType=QueryType.LIKE)}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN,entity = WsVmsBDevice.class,alias = "c",
				on="a.device_id=c.id",attrName = "device",
		columns =@Column(name="device_code", attrName="deviceCode", label="设备编号", queryType=QueryType.LIKE))
},
		orderBy="a.update_date DESC"
)
@Data
public class WsVmsRVehicleDevice extends DataEntity<WsVmsRVehicleDevice> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// 车辆id
	private String deviceId;		// 设备id
	private WsVmsBVehicle vehicle;
	private WsVmsBDevice device;
	public WsVmsRVehicleDevice() {
		this(null);
	}
	
	public WsVmsRVehicleDevice(String id){
		super(id);
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}