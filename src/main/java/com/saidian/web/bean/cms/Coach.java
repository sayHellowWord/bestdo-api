package com.saidian.web.bean.cms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * todo 待删除
 */
public class Coach implements Serializable{

	private static final long serialVersionUID = 8525978046829379003L;

	private Long id;
	
	private String name;
	
	private byte gender;
	
	private Date birthday;
	
	private String educationalBg;
	
	private String experience;
	
	private String icon;
	
	private String project;
	
	private String rank;
	
	private String siteName;
	
	private String photoIcon;
	
	private String introduction;
	
	private String description;
	
	private byte state;
	
	private Date createDate;
	
	private Long createUser;
	
	private Date updateDate;
	
	private Long updateUser;
	
	private List<String> trainNames;
	
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

	public List<String> getTrainNames() {
		return trainNames;
	}

	public void setTrainNames(List<String> trainNames) {
		this.trainNames = trainNames;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getEducationalBg() {
		return educationalBg;
	}

	public void setEducationalBg(String educationalBg) {
		this.educationalBg = educationalBg;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getPhotoIcon() {
		return photoIcon;
	}

	public void setPhotoIcon(String photoIcon) {
		this.photoIcon = photoIcon;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
