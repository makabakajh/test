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
 * 事故表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_accident", alias="a", label="事故表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="accident_no", attrName="accidentNo", label="事故编号",queryType = QueryType.LIKE),
		@Column(name="accident_date", attrName="accidentDate", label="肇事日期"),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆ID"),
		@Column(name="user_id", attrName="userId", label="肇事人"),
		@Column(name="accident_address", attrName="accidentAddress", label="事故地点"),
		@Column(name="accident_type", attrName="accidentType", label="事故种类"),
		@Column(name="discript", attrName="discript", label="事故说明"),
		@Column(name="treatment", attrName="treatment", label="处理情况"),
		@Column(name="result", attrName="result", label="处理结果"),
		@Column(name="amount", attrName="amount", label="保险赔偿金额"),
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
public class WsVmsMAccident extends DataEntity<WsVmsMAccident> {
	
	private static final long serialVersionUID = 1L;
	private String accidentNo;		// 事故编号
	private Date accidentDate;		// 肇事日期
	private String vehicleId;		// 车辆ID
	private String userId;		// 肇事人
	private String accidentAddress;		// 事故地点
	private String accidentType;		// 事故种类
	private String discript;		// 事故说明
	private String treatment;		// 处理情况
	private String result;		// 处理结果
	private Double amount;		// 保险赔偿金额
	private User user;
	private WsVmsBVehicle vehicle;
	public WsVmsMAccident() {
		this(null);
	}
	
	public WsVmsMAccident(String id){
		super(id);
	}

	public String getAccidentNo() {
		return accidentNo;
	}

	public void setAccidentNo(String accidentNo) {
		this.accidentNo = accidentNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}
	
	@Size(min=0, max=22, message="车辆长度不能超过 22 个字符")
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
	
	@Size(min=0, max=255, message="事故地点长度不能超过 255 个字符")
	public String getAccidentAddress() {
		return accidentAddress;
	}

	public void setAccidentAddress(String accidentAddress) {
		this.accidentAddress = accidentAddress;
	}

	public String getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	
	@Size(min=0, max=255, message="事故说明长度不能超过 255 个字符")
	public String getDiscript() {
		return discript;
	}

	public void setDiscript(String discript) {
		this.discript = discript;
	}
	
	@Size(min=0, max=255, message="处理情况长度不能超过 255 个字符")
	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	@Size(min=0, max=255, message="处理结果长度不能超过 255 个字符")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}