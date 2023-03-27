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
 * 违章表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_regulations", alias="a", label="违章表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="regulations_date", attrName="regulationsDate", label="违章日期", isQuery=false, isUpdateForce=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id"),
		@Column(name="user_id", attrName="userId", label="违章人"),
		@Column(name="regulations_type", attrName="regulationsType", label="违章类型"),
		@Column(name="address", attrName="address", label="违章地点", isQuery=false),
		@Column(name="amount", attrName="amount", label="罚款金额", isQuery=false, isUpdateForce=true),
		@Column(name="deduct_points", attrName="deductPoints", label="扣分", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = User.class,alias = "b",
				on="a.user_id=b.user_code",attrName = "user",
				columns ={@Column(name="user_name", attrName="userName", queryType=QueryType.LIKE)}),
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "c",
				on="a.vehicle_id=c.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)})
	} , orderBy="a.update_date DESC"
)
@Data
public class WsVmsMRegulations extends DataEntity<WsVmsMRegulations> {
	
	private static final long serialVersionUID = 1L;
	private Date regulationsDate;		// 违章日期
	private String vehicleId;		// 车辆id
	private String userId;		// 违章人
	private String regulationsType;		// 违章类型
	private String address;		// 违章地点
	private Double amount;		// 罚款金额
	private String deductPoints;		// 扣分
	private User user;
	private WsVmsBVehicle vehicle;
	public WsVmsMRegulations() {
		this(null);
	}
	
	public WsVmsMRegulations(String id){
		super(id);
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegulationsDate() {
		return regulationsDate;
	}

	public void setRegulationsDate(Date regulationsDate) {
		this.regulationsDate = regulationsDate;
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getRegulationsType() {
		return regulationsType;
	}

	public void setRegulationsType(String regulationsType) {
		this.regulationsType = regulationsType;
	}
	
	@Size(min=0, max=255, message="违章地点长度不能超过 255 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

	public String getDeductPoints() {
		return deductPoints;
	}

	public void setDeductPoints(String deductPoints) {
		this.deductPoints = deductPoints;
	}
	
}