package com.saidian.web.bean.cms;

import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable{

	private static final long serialVersionUID = -2538623820822039958L;

	private Long id;
	
	private String name;
	
	private String district;
	
	private String adress;
	
	private String phone;
	
	private String type;
	
	private String icon;
	
	private String description;
	
	private String shortIcon;
	
	private byte state;
	
	private Date createDate;
	
	private Long createUser;
	
	private Date updateDate;
	
	private Long updateUser;
	
	private boolean flag;
	
	private Long auditUser;
	
	private Date auditDate;
	
	private String auditMark;

	public Long getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Long auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditMark() {
		return auditMark;
	}

	public void setAuditMark(String auditMark) {
		this.auditMark = auditMark;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortIcon() {
		return shortIcon;
	}

	public void setShortIcon(String shortIcon) {
		this.shortIcon = shortIcon;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	
}
