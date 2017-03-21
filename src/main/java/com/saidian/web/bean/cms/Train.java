package com.saidian.web.bean.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Train implements Serializable{

	private static final long serialVersionUID = 7994760805580533468L;

	private Long id;
	
	private String name;
	
	private String district;
	
	private String adress;
	
	private String phone;
	
	private String project;
	
	private String type;
	
	private Date signStartTime;
	
	private Date signEndTime;
	
	private Integer maxNum;
	
	private Integer minNum;
	
	private String trainTime;
	
	private String payType;
	
	private String typeDescription;
	
	private byte autoSign;
	
	private byte signState;
	
	private byte shelves;
	
	private byte homePage;
	
	private String icon;
	
	private String description;
	
	private String shortIcon;
	
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
	
	private boolean flag;

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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSignStartTime() {
		return signStartTime;
	}

	public void setSignStartTime(Date signStartTime) {
		this.signStartTime = signStartTime;
	}

	public Date getSignEndTime() {
		return signEndTime;
	}

	public void setSignEndTime(Date signEndTime) {
		this.signEndTime = signEndTime;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public byte getAutoSign() {
		return autoSign;
	}

	public void setAutoSign(byte autoSign) {
		this.autoSign = autoSign;
	}

	public byte getSignState() {
		return signState;
	}

	public void setSignState(byte signState) {
		this.signState = signState;
	}

	public byte getShelves() {
		return shelves;
	}

	public void setShelves(byte shelves) {
		this.shelves = shelves;
	}

	public byte getHomePage() {
		return homePage;
	}

	public void setHomePage(byte homePage) {
		this.homePage = homePage;
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
