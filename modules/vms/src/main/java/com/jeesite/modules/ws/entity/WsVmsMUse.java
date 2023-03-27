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
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
import lombok.Data;

/**
 * 用车表Entity
 * @author oqm
 * @version 2023-02-06
 */
@Table(name="ws_vms_m_use", alias="a", label="用车表信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="use_code", attrName="useCode", label="申请单号", isUpdate=false, queryType=QueryType.LIKE),
		@Column(name="use_date", attrName="useDate", label="申请日期", isQuery=false, isUpdateForce=true),
		@Column(name="use_department", attrName="useDepartment", label="用车部门", isQuery=false),
		@Column(name="applicant", attrName="applicant", label="申请人"),
		@Column(name="vehicle_type", attrName="vehicleType", label="车型"),
		@Column(name="plan_use_date", attrName="planUseDate", label="计划用车时间", isQuery=false, isUpdateForce=true),
		@Column(name="use_reasons", attrName="useReasons", label="用车事由", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type = Type.LEFT_JOIN,entity = Office.class,alias = "b",
		on="a.use_department=b.office_code",attrName = "office",
		columns = {@Column(name="full_name", 	attrName="fullName", 	label="机构全称", queryType=QueryType.LIKE),}),
		@JoinTable(type=Type.LEFT_JOIN,entity = User.class,alias = "c",
		on="a.applicant=c.user_code",attrName = "user",
		columns = {@Column(name = "user_name",attrName = "userName",label = "用户昵称",queryType = QueryType.LIKE) })
}
	, orderBy="a.update_date DESC"
)
@Data
public class WsVmsMUse extends DataEntity<WsVmsMUse> {
	
	private static final long serialVersionUID = 1L;
	private String useCode;		// 申请单号
	private Date useDate;		// 申请日期
	private String useDepartment;		// 用车部门
	private String applicant;		// 申请人
	private String vehicleType;		// 车型
	private Date planUseDate;		// 计划用车时间
	private String useReasons;		// 用车事由
	private Office office;
	private User user;
	public WsVmsMUse() {
		this(null);
	}
	
	public WsVmsMUse(String id){
		super(id);
	}

	public String getuseCode() {
		return useCode;
	}

	public void setuseCode(String useCode) {
		this.useCode = useCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Size(min=0, max=255, message="用车部门长度不能超过 255 个字符")
	public String getUseDepartment() {
		return useDepartment;
	}

	public void setUseDepartment(String useDepartment) {
		this.useDepartment = useDepartment;
	}
	
	@Size(min=0, max=255, message="申请人长度不能超过 255 个字符")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPlanUseDate() {
		return planUseDate;
	}

	public void setPlanUseDate(Date planUseDate) {
		this.planUseDate = planUseDate;
	}
	
	@Size(min=0, max=255, message="用车事由长度不能超过 255 个字符")
	public String getUseReasons() {
		return useReasons;
	}

	public void setUseReasons(String useReasons) {
		this.useReasons = useReasons;
	}
	
}