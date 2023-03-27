package com.jeesite.modules.ws.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Data;

/**
 * 油卡充值记录Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_oil_recharge", alias="a", label="油卡充值记录信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="oil_card_id", attrName="oilCardId", label="油卡号码"),
		@Column(name="recharge_date", attrName="rechargeDate", label="充值日期", isQuery=false, isUpdateForce=true),
		@Column(name="balance", attrName="balance", label="充前余额", isQuery=false, isUpdateForce=true),
		@Column(name="recharge_amount", attrName="rechargeAmount", label="充值金额", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = WsVmsBOilCard.class,alias = "b",
				on="a.oil_card_id=b.id",attrName = "oilCard",
				columns ={@Column(name="card_no", attrName="cardNo", label="油卡号码", queryType=QueryType.LIKE)})
} ,orderBy="a.update_date DESC"
)
@Data
public class WsVmsMOilRecharge extends DataEntity<WsVmsMOilRecharge> {
	
	private static final long serialVersionUID = 1L;
	private String oilCardId;		// 油卡号码
	private Date rechargeDate;		// 充值日期
	private Double balance;		// 充前余额
	private Double rechargeAmount;		// 充值金额
	private WsVmsBOilCard oilCard;
	public WsVmsMOilRecharge() {
		this(null);
	}
	
	public WsVmsMOilRecharge(String id){
		super(id);
	}

	public String getOilCardId() {
		return oilCardId;
	}

	public void setOilCardId(String oilCardId) {
		this.oilCardId = oilCardId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}
	
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@NotNull(message="充值金额不能为空")
	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	
}