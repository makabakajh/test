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
 * 油卡表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_b_oil_card", alias="a", label="油卡表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="card_no", attrName="cardNo", label="油卡号码", queryType=QueryType.LIKE),
		@Column(name="issue_date", attrName="issueDate", label="发卡日期", isQuery=false, isUpdateForce=true),
		@Column(name="issue_user", attrName="issueUser", label="发卡人", isQuery=false),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id"),
		@Column(name="gas_station", attrName="gasStation", label="加油站"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = User.class,alias = "b",
				on="a.issue_user=b.user_code",attrName = "user",
				columns ={@Column(name="user_name", attrName="userName", queryType=QueryType.LIKE)}),
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "c",
				on="a.vehicle_id=c.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)})

	},orderBy="a.update_date DESC"
)
@Data
public class WsVmsBOilCard extends DataEntity<WsVmsBOilCard> {
	
	private static final long serialVersionUID = 1L;
	private String cardNo;		// 油卡号码
	private Date issueDate;		// 发卡日期
	private String issueUser;		// 发卡人
	private String vehicleId;		// 车辆id
	private String gasStation;		// 加油站
	private User user;
	private WsVmsBVehicle vehicle;
	public WsVmsBOilCard() {
		this(null);
	}
	
	public WsVmsBOilCard(String id){
		super(id);
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	

	public String getIssueUser() {
		return issueUser;
	}

	public void setIssueUser(String issueUser) {
		this.issueUser = issueUser;
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	

	public String getGasStation() {
		return gasStation;
	}

	public void setGasStation(String gasStation) {
		this.gasStation = gasStation;
	}
	
}