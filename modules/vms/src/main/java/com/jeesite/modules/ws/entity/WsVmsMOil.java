package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * 加油记录Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_oil", alias="a", label="加油记录信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vehicle_id", attrName="vehicleId", label="车辆id", queryType=QueryType.LIKE),
		@Column(name="oil_date", attrName="oilDate", label="加油日期", isQuery=false, isUpdateForce=true),
		@Column(name="oil_card_id", attrName="oilCardId", label="油卡id", queryType=QueryType.LIKE),
		@Column(name="oil_type", attrName="oilType", label="油品类型"),
		@Column(name="unit_price", attrName="unitPrice", label="油品单价", isQuery=false, isUpdateForce=true),
		@Column(name="fuel", attrName="fuel", label="加油量", isQuery=false, isUpdateForce=true),
		@Column(name="amount", attrName="amount", label="加油金额", isQuery=false, isUpdateForce=true),
		@Column(name="user_id", attrName="userId", label="经手人"),
		@Column(name="pay_type", attrName="payType", label="付款方式"),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = User.class,alias = "b",
				on="a.user_id=b.user_code",attrName = "user",
				columns ={@Column(name="user_name", attrName="userName", queryType=QueryType.LIKE),
						@Column(name = "user_code",attrName = "userCode",queryType=QueryType.EQ)}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN,entity = WsVmsBVehicle.class,alias = "c",
				on="a.vehicle_id=c.id",attrName = "vehicle",
				columns ={@Column(name="license_plate", attrName="licensePlate", label="车牌号", queryType=QueryType.LIKE)}),
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBOilCard.class,alias = "d",
				on="a.oil_card_id=d.id",attrName = "oilCard",
				columns ={@Column(name="card_no", attrName="cardNo", label="油卡号码", queryType=QueryType.LIKE)})
} , orderBy="a.update_date DESC"
)
@Data
public class WsVmsMOil extends DataEntity<WsVmsMOil> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleId;		// 车辆id
	private Date oilDate;		// 加油日期
	private String oilCardId;		// 油卡id
	private String oilType;		// 油品类型
	private Double unitPrice;		// 油品单价
	private Double fuel;		// 加油量
	private Double amount;		// 加油金额
	private String userId;		// 经手人
	private String payType;		// 付款方式
	private User user;
	private WsVmsBVehicle vehicle;
	private WsVmsBOilCard oilCard;
	private List<String> fileUrls=new ArrayList<>();
	public WsVmsMOil() {
		this(null);
	}
	
	public WsVmsMOil(String id){
		super(id);
	}
	

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOilDate() {
		return oilDate;
	}

	public void setOilDate(Date oilDate) {
		this.oilDate = oilDate;
	}
	

	public String getOilCardId() {
		return oilCardId;
	}

	public void setOilCardId(String oilCardId) {
		this.oilCardId = oilCardId;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Double getFuel() {
		return fuel;
	}

	public void setFuel(Double fuel) {
		this.fuel = fuel;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
}