package com.saidian.web.bean.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News implements Serializable{

	private static final long serialVersionUID = -8985683097766007402L;

	private Long id;
	
	private String title;
	
	private String subTitle;
	
	private String author;
	
	private String label;
	
	private String icon;
	
	private String shortIcon;
	
	private byte autoHome;
	
	private String description;
	
	private byte state;
	
	private Date createDate;
	
	private Long createUser;
	
	private Date updateDate;
	
	private Long updateUser;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getShortIcon() {
		return shortIcon;
	}

	public void setShortIcon(String shortIcon) {
		this.shortIcon = shortIcon;
	}

	public byte getAutoHome() {
		return autoHome;
	}

	public void setAutoHome(byte autoHome) {
		this.autoHome = autoHome;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
