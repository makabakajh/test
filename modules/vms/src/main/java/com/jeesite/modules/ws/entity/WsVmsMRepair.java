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
import com.jeesite.modules.sys.entity.User;
import lombok.Data;

/**
 * 维修保养表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_repair", alias="a", label="维修保养表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id"),
		@Column(name="accident_id", attrName="accidentId", label="事故id"),
		@Column(name="repair_type", attrName="repairType", label="维保类型"),
		@Column(name="repair_shop", attrName="repairShop", label="修理厂", isQuery=false),
		@Column(name="repair_date", attrName="repairDate", label="送修日期", isQuery=false, isUpdateForce=true),
		@Column(name="user_id", attrName="userId", label="送修人"),
		@Column(name="repair_reason", attrName="repairReason", label="送修原因", isQuery=false),
		@Column(name="amount", attrName="amount", label="维修金额", isQuery=false, isUpdateForce=true),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = User.class,alias = "b",
				on="a.user_id=b.user_code",attrName = "user",
				columns ={@Column(name="user_name", attrName="userName", queryType=QueryType.LIKE)}),
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "c",
				on="a.vehicle_id=c.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)}),
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsMAccident.class,alias = "d",
				on="a.accident_id=d.id",attrName = "accident",
				columns ={@Column(name="accident_no", attrName="accidentNo", label="事故编号")})
} ,orderBy="a.update_date DESC"
)
@Data
public class WsVmsMRepair extends DataEntity<WsVmsMRepair> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// 车辆id
	private String repairType;		// 维保类型
	private String repairShop;		// 修理厂
	private Date repairDate;		// 送修日期
	private String userId;		// 送修人
	private String repairReason;		// 送修原因
	private Double amount;		// 维修金额
	private User user;
	private WsVmsBVehicle vehicle;
	private WsVmsMAccident accident;
	public WsVmsMRepair() {
		this(null);
	}
	
	public WsVmsMRepair(String id){
		super(id);
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	
	@Size(min=0, max=255, message="修理厂长度不能超过 255 个字符")
	public String getRepairShop() {
		return repairShop;
	}

	public void setRepairShop(String repairShop) {
		this.repairShop = repairShop;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(String repairReason) {
		this.repairReason = repairReason;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}