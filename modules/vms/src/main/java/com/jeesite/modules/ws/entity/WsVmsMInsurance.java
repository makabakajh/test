package com.jeesite.modules.ws.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.entity.User;
import lombok.Data;

/**
 * 保险表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_insurance", alias="a", label="保险表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="insurance_date", attrName="insuranceDate", label="投保日期", isQuery=false, isUpdateForce=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id"),
		@Column(name="insurance_no", attrName="insuranceNo", label="保单号"),
		@Column(name="company", attrName="company", label="保险公司", isQuery=false),
		@Column(name="amount", attrName="amount", label="投保金额", isQuery=false, isUpdateForce=true),
		@Column(name="expiration_date", attrName="expirationDate", label="到期日期", isQuery=false, isUpdateForce=true),
		@Column(name="user_id", attrName="userId", label="经手人"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = User.class,alias = "b",
				on="a.user_id=b.user_code",attrName = "user",
				columns ={@Column(name="user_name", attrName="userName", queryType=QueryType.LIKE)}),
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "c",
				on="a.vehicle_id=c.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)})
} ,orderBy="a.update_date DESC"
)
@Data
public class WsVmsMInsurance extends DataEntity<WsVmsMInsurance> {
	
	private static final long serialVersionUID = 1L;
	private Date insuranceDate;		// 投保日期
	private String vehicleId;		// 车辆id
	private String insuranceNo;		// 保单号
	private String company;		// 保险公司
	private Double amount;		// 投保金额
	private Date expirationDate;		// 到期日期
	private String userId;		// 经手人
	private User user;
	private WsVmsBVehicle vehicle;
	public WsVmsMInsurance() {
		this(null);
	}
	
	public WsVmsMInsurance(String id){
		super(id);
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	

	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	
	@Size(min=0, max=255, message="保险公司长度不能超过 255 个字符")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Size(min=0, max=255, message="经手人长度不能超过 255 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}