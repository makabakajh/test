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
 * 年检表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_year_inspect", alias="a", label="年检表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id"),
		@Column(name="inspect_date", attrName="inspectDate", label="年检日期", isQuery=false, isUpdateForce=true),
		@Column(name="amount", attrName="amount", label="年检费用", isQuery=false, isUpdateForce=true),
		@Column(name="vehicle_office", attrName="vehicleOffice", label="车管所", isQuery=false),
		@Column(name="user_id", attrName="userId", label="经手人"),
		@Column(name="expire_date", attrName="expireDate", label="到期日期", isQuery=false, isUpdateForce=true),
		@Column(name="next_inspect_date", attrName="nextInspectDate", label="下次年检日期", isQuery=false, isUpdateForce=true),
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
public class WsVmsMYearInspect extends DataEntity<WsVmsMYearInspect> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// 车辆id
	private Date inspectDate;		// 年检日期
	private Double amount;		// 年检费用
	private String vehicleOffice;		// 车管所
	private String userId;		// 经手人
	private Date expireDate;		// 到期日期
	private Date nextInspectDate;		// 下次年检日期
	private User user;
	private WsVmsBVehicle vehicle;
	public WsVmsMYearInspect() {
		this(null);
	}
	
	public WsVmsMYearInspect(String id){
		super(id);
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectDate() {
		return inspectDate;
	}

	public void setInspectDate(Date inspectDate) {
		this.inspectDate = inspectDate;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Size(min=0, max=255, message="车管所长度不能超过 255 个字符")
	public String getVehicleOffice() {
		return vehicleOffice;
	}

	public void setVehicleOffice(String vehicleOffice) {
		this.vehicleOffice = vehicleOffice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getNextInspectDate() {
		return nextInspectDate;
	}

	public void setNextInspectDate(Date nextInspectDate) {
		this.nextInspectDate = nextInspectDate;
	}
	
}