package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Data;

/**
 * 设备Entity
 * @author oqm
 * @version 2023-02-03
 */
@Table(name="ws_vms_b_device", alias="a", label="设备信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="device_code", attrName="deviceCode", label="设备编号", queryType=QueryType.LIKE),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="device_type", attrName="deviceType", label="设备类型"),
		@Column(name="type", attrName="type", label="型号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
@Data
public class WsVmsBDevice extends DataEntity<WsVmsBDevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceCode;		// 设备编号
	private String deviceName;		// 设备名称
	private String type;		// 型号
	private String deviceType;  //设备类型
	public WsVmsBDevice() {
		this(null);
	}
	
	public WsVmsBDevice(String id){
		super(id);
	}
	

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}