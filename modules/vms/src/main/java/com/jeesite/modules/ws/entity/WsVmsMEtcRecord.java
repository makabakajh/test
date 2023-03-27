package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;
import com.jeesite.common.utils.excel.annotation.ExcelFields;

/**
 * etc记录Entity
 * @author oqm
 * @version 2023-02-14
 */
@Table(name="ws_vms_m_etc_record", alias="a", label="etc记录信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="pass_id", attrName="passId", label="passId"),
		@Column(name="license_plate", attrName="licensePlate", label="车牌号",queryType = QueryType.LIKE),
		@Column(name="start_station", attrName="startStation", label="出发站"),
		@Column(name="end_station", attrName="endStation", label="到达站"),
		@Column(name="start_time", attrName="startTime", label="出发时间"),
		@Column(name="end_time", attrName="endTime", label="到达时间"),
		@Column(name="total_amount", attrName="totalAmount", label="花费金额"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class WsVmsMEtcRecord extends DataEntity<WsVmsMEtcRecord> {
	
	private static final long serialVersionUID = 1L;
	private String passId;
	private String licensePlate;		// 车牌号
	private String startStation;		// 出发站
	private String endStation;		// 到达站
	private Date startTime;		// 出发时间
	private Date endTime;		// 到达时间
	private BigDecimal totalAmount;		// 花费金额

	@ExcelFields({
		@ExcelField(title="车牌号", attrName="licensePlate", align=Align.CENTER, sort=20),
		@ExcelField(title="出发站", attrName="startStation", align=Align.CENTER, sort=30),
		@ExcelField(title="到达站", attrName="endStation", align=Align.CENTER, sort=40),
		@ExcelField(title="出发时间", attrName="startTime", align=Align.CENTER, sort=50, dataFormat="yyyy-MM-dd hh:mm"),
		@ExcelField(title="到达时间", attrName="endTime", align=Align.CENTER, sort=60, dataFormat="yyyy-MM-dd hh:mm"),
		@ExcelField(title="花费金额", attrName="totalAmount", align=Align.CENTER, sort=70),
	})
	public WsVmsMEtcRecord() {
		this(null);
	}
	
	public WsVmsMEtcRecord(String id){
		super(id);
	}

	public String getPassId() {
		return passId;
	}

	public void setPassId(String passId) {
		this.passId = passId;
	}

	@Size(min=0, max=255, message="车牌号长度不能超过 255 个字符")
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	@Size(min=0, max=255, message="出发站长度不能超过 255 个字符")
	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	
	@Size(min=0, max=255, message="到达站长度不能超过 255 个字符")
	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}