package com.universe.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("tbl_operation_log")
public class OperationLogDo {

	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer opType;
	private String opContent;
	private Integer userId;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public Integer getOpType() {
		return opType;
	}

	public String getOpContent() {
		return opContent;
	}

	public Integer getUserId() {
		return userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "OperationLogDo [id=" + id + ", opType=" + opType + ", opContent=" + opContent + ", userId=" + userId + ", createTime="
			+ createTime + "]";
	}

}
