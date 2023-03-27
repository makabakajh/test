package com.jeesite.modules.ws.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 车队表Entity
 * @author oqm
 * @version 2023-02-03
 */
@Table(name="ws_vms_m_fleet", alias="a", label="车队表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="fleet_code", attrName="fleetCode", label="车队编号", isUpdate=false, queryType=QueryType.LIKE),
		@Column(name="fleet_name", attrName="fleetName", label="车队名称", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class WsVmsMFleet extends DataEntity<WsVmsMFleet> {
	
	private static final long serialVersionUID = 1L;
	private String fleetCode;		// 车队编号
	private String fleetName;		// 车队名称

	public WsVmsMFleet() {
		this(null);
	}
	
	public WsVmsMFleet(String id){
		super(id);
	}
	

	public String getFleetCode() {
		return fleetCode;
	}

	public void setFleetCode(String fleetCode) {
		this.fleetCode = fleetCode;
	}
	
	@Size(min=0, max=255, message="车队名称长度不能超过 255 个字符")
	public String getFleetName() {
		return fleetName;
	}

	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}
	
}