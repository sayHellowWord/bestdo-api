package com.saidian.web.bean.cms;

import java.io.Serializable;
import java.util.Date;

public class Dynamic implements Serializable{

	private static final long serialVersionUID = 7353819556611600658L;

	private Long id;
	
	private String title;
	
	private String orgnName;
	
	private Long orgnId;
	
	private String photoIcon;
	
	private String shortIcon;
	
	private String description;
	
	private byte isShow;
	
	private byte state;
	
	private byte autohome;
	
	private Date createDate;
	
	private Long createUser;
	
	private Date updateDate;
	
	private Long updateUser;
	
	private Long auditUser;
	
	private Date auditDate;
	
	private String auditMark;

	private String timeStr;


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

	public String getOrgnName() {
		return orgnName;
	}

	public void setOrgnName(String orgnName) {
		this.orgnName = orgnName;
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

	public Long getOrgnId() {
		return orgnId;
	}

	public void setOrgnId(Long orgnId) {
		this.orgnId = orgnId;
	}

	public String getPhotoIcon() {
		return photoIcon;
	}

	public void setPhotoIcon(String photoIcon) {
		this.photoIcon = photoIcon;
	}

	public String getShortIcon() {
		return shortIcon;
	}

	public void setShortIcon(String shortIcon) {
		this.shortIcon = shortIcon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsShow() {
		return isShow;
	}

	public void setIsShow(byte isShow) {
		this.isShow = isShow;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public byte getAutohome() {
		return autohome;
	}

	public void setAutohome(byte autohome) {
		this.autohome = autohome;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
}
