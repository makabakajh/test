package com.jeesite.modules.ws.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 司机表Entity
 * @author oqm
 * @version 2023-02-03
 */
@Table(name="ws_vms_b_drivers", alias="a", label="司机表信息", columns={
		@Column(name="id", attrName="id", label="主键id", isPK=true),
		@Column(name="driver_name", attrName="driverName", label="司机姓名", queryType=QueryType.LIKE),
		@Column(name="driving_license", attrName="drivingLicense", label="身份证号", isQuery=false),
		@Column(name="vehicle_class", attrName="vehicleClass", label="准驾车型", isQuery=false),
		@Column(name="organizations", attrName="organizations", label="驾驶证发证机关", isQuery=false),
		@Column(name="valid_period_from", attrName="validPeriodFrom", label="驾驶证有效期自", isQuery=false, isUpdateForce=true),
		@Column(name="valid_period_to", attrName="validPeriodTo", label="驾驶证有效期至", isQuery=false, isUpdateForce=true),
		@Column(name="qualification_certificate", attrName="qualificationCertificate", label="从业资格证号", isQuery=false),
		@Column(name="road_trans_license", attrName="roadTransLicense", label="道路运输经营许可证", isQuery=false),
		@Column(name="vehicle_driving_license", attrName="vehicleDrivingLicense", label="机动车驾驶证号", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class WsVmsBDrivers extends DataEntity<WsVmsBDrivers> {
	
	private static final long serialVersionUID = 1L;
	private String driverName;		// 司机姓名
	private String drivingLicense;		// 身份证号
	private String vehicleClass;		// 准驾车型
	private String organizations;		// 驾驶证发证机关
	private Date validPeriodFrom;		// 驾驶证有效期自
	private Date validPeriodTo;		// 驾驶证有效期至
	private String qualificationCertificate;		// 从业资格证号
	private String roadTransLicense;		// 道路运输经营许可证
	private String vehicleDrivingLicense;		// 机动车驾驶证号

	public WsVmsBDrivers() {
		this(null);
	}
	
	public WsVmsBDrivers(String id){
		super(id);
	}
	

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}
	

	public String getOrganizations() {
		return organizations;
	}

	public void setOrganizations(String organizations) {
		this.organizations = organizations;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getValidPeriodFrom() {
		return validPeriodFrom;
	}

	public void setValidPeriodFrom(Date validPeriodFrom) {
		this.validPeriodFrom = validPeriodFrom;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getValidPeriodTo() {
		return validPeriodTo;
	}

	public void setValidPeriodTo(Date validPeriodTo) {
		this.validPeriodTo = validPeriodTo;
	}
	
	@Size(min=0, max=1000, message="从业资格证号长度不能超过 1000 个字符")
	public String getQualificationCertificate() {
		return qualificationCertificate;
	}

	public void setQualificationCertificate(String qualificationCertificate) {
		this.qualificationCertificate = qualificationCertificate;
	}
	
	@Size(min=0, max=255, message="道路运输经营许可证长度不能超过 255 个字符")
	public String getRoadTransLicense() {
		return roadTransLicense;
	}

	public void setRoadTransLicense(String roadTransLicense) {
		this.roadTransLicense = roadTransLicense;
	}
	
	@Size(min=0, max=255, message="机动车驾驶证号长度不能超过 255 个字符")
	public String getVehicleDrivingLicense() {
		return vehicleDrivingLicense;
	}

	public void setVehicleDrivingLicense(String vehicleDrivingLicense) {
		this.vehicleDrivingLicense = vehicleDrivingLicense;
	}
	
}