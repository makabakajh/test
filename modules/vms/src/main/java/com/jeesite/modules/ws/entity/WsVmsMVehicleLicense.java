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
 * 行驶证Entity
 * @author oqm
 * @version 2023-02-14
 */
@Table(name="ws_vms_m_vehicle_license", alias="a", label="行驶证信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆编号", queryType=QueryType.LIKE),
		@Column(name="license_type", attrName="licenseType", label="车辆类型", isQuery=false),
		@Column(name="owner", attrName="owner", label="所有人", isQuery=false),
		@Column(name="address", attrName="address", label="住址", isQuery=false),
		@Column(name="use_character", attrName="useCharacter", label="车辆使用性质", isQuery=false),
		@Column(name="model", attrName="model", label="品牌型号", isQuery=false),
		@Column(name="vin", attrName="vin", label="车辆识别号", isQuery=false),
		@Column(name="engine_no", attrName="engineNo", label="发动机号码", isQuery=false),
		@Column(name="register_date", attrName="registerDate", label="注册日期", isQuery=false, isUpdateForce=true),
		@Column(name="issue_date", attrName="issueDate", label="发证日期", isQuery=false, isUpdateForce=true),
		@Column(name="issuing_organizations", attrName="issuingOrganizations", label="发证机关", isQuery=false),
		@Column(name="busload", attrName="busload", label="核定载人数", isQuery=false, isUpdateForce=true),
		@Column(name="gross_mass", attrName="grossMass", label="总质量", isQuery=false, isUpdateForce=true),
		@Column(name="curb_mass", attrName="curbMass", label="整备质量", isQuery=false, isUpdateForce=true),
		@Column(name="tonnage", attrName="tonnage", label="核定载质量", isQuery=false, isUpdateForce=true),
		@Column(name="out_dimension", attrName="outDimension", label="外廓尺寸", isQuery=false),
		@Column(name="traction_mass", attrName="tractionMass", label="准牵引总质量", isQuery=false, isUpdateForce=true),
		@Column(name="vehicle_energy_type", attrName="vehicleEnergyType", label="车辆能源类型", isQuery=false),
		@Column(name="vehicle_license", attrName="vehicleLicense", label="车辆行驶证号", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "b",
				on="a.vehicle_id=b.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)})
} , orderBy="a.update_date DESC"
)
@Data
public class WsVmsMVehicleLicense extends DataEntity<WsVmsMVehicleLicense> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// 车辆编号
	private String licenseType;		// 车辆类型
	private String owner;		// 所有人
	private String address;		// 住址
	private String useCharacter;		// 车辆使用性质
	private String model;		// 品牌型号
	private String vin;		// 车辆识别号
	private String engineNo;		// 发动机号码
	private Date registerDate;		// 注册日期
	private Date issueDate;		// 发证日期
	private String issuingOrganizations;		// 发证机关
	private Long busload;		// 核定载人数
	private Double grossMass;		// 总质量
	private Double curbMass;		// 整备质量
	private Double tonnage;		// 核定载质量
	private String outDimension;		// 外廓尺寸
	private Double tractionMass;		// 准牵引总质量
	private String vehicleEnergyType;		// 车辆能源类型
	private String vehicleLicense;		// 车辆行驶证号
	private WsVmsBVehicle vehicle;
	public WsVmsMVehicleLicense() {
		this(null);
	}
	
	public WsVmsMVehicleLicense(String id){
		super(id);
	}
	
	@Size(min=0, max=64, message="车辆编号长度不能超过 64 个字符")
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@Size(min=0, max=255, message="车辆类型长度不能超过 255 个字符")
	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	
	@Size(min=0, max=255, message="所有人长度不能超过 255 个字符")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Size(min=0, max=255, message="住址长度不能超过 255 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Size(min=0, max=255, message="车辆使用性质长度不能超过 255 个字符")
	public String getUseCharacter() {
		return useCharacter;
	}

	public void setUseCharacter(String useCharacter) {
		this.useCharacter = useCharacter;
	}
	
	@Size(min=0, max=100, message="品牌型号长度不能超过 100 个字符")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Size(min=0, max=255, message="车辆识别号长度不能超过 255 个字符")
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	@Size(min=0, max=255, message="发动机号码长度不能超过 255 个字符")
	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@Size(min=0, max=255, message="发证机关长度不能超过 255 个字符")
	public String getIssuingOrganizations() {
		return issuingOrganizations;
	}

	public void setIssuingOrganizations(String issuingOrganizations) {
		this.issuingOrganizations = issuingOrganizations;
	}
	
	public Long getBusload() {
		return busload;
	}

	public void setBusload(Long busload) {
		this.busload = busload;
	}
	
	public Double getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(Double grossMass) {
		this.grossMass = grossMass;
	}
	
	public Double getCurbMass() {
		return curbMass;
	}

	public void setCurbMass(Double curbMass) {
		this.curbMass = curbMass;
	}
	
	public Double getTonnage() {
		return tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}
	
	@Size(min=0, max=255, message="外廓尺寸长度不能超过 255 个字符")
	public String getOutDimension() {
		return outDimension;
	}

	public void setOutDimension(String outDimension) {
		this.outDimension = outDimension;
	}
	
	public Double getTractionMass() {
		return tractionMass;
	}

	public void setTractionMass(Double tractionMass) {
		this.tractionMass = tractionMass;
	}
	
	@Size(min=0, max=255, message="车辆能源类型长度不能超过 255 个字符")
	public String getVehicleEnergyType() {
		return vehicleEnergyType;
	}

	public void setVehicleEnergyType(String vehicleEnergyType) {
		this.vehicleEnergyType = vehicleEnergyType;
	}
	
	@Size(min=0, max=255, message="车辆行驶证号长度不能超过 255 个字符")
	public String getVehicleLicense() {
		return vehicleLicense;
	}

	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	
}