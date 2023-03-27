package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Data;

/**
 * 里程表Entity
 * @author oqm
 * @version 2023-02-13
 */
@Table(name="ws_vms_b_mileage", alias="a", label="里程表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆"),
		@Column(name="mileage", attrName="mileage", label="当前里程", isQuery=false, isUpdateForce=true),
		@Column(name="mileage_type", attrName="mileageType", label="里程类型"),
		@Column(name="date", attrName="date", label="填报日期", isQuery=false, isUpdateForce=true),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "b",
				on="a.vehicle_id=b.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)})
}, orderBy="a.update_date DESC"
)
@Data
public class WsVmsBMileage extends DataEntity<WsVmsBMileage> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// vehicle_id
	private Double mileage;		// 当前里程
	private String mileageType;		// 里程类型
	private Date date;		// 填报日期
	private WsVmsBVehicle vehicle;
	public WsVmsBMileage() {
		this(null);
	}
	
	public WsVmsBMileage(String id){
		super(id);
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	

	public String getMileageType() {
		return mileageType;
	}

	public void setMileageType(String mileageType) {
		this.mileageType = mileageType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}