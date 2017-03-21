package com.saidian.web.bean.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String address;

	private Integer auditId;

	private Byte auditStatus;

	private Timestamp auditTime;

	private String chargeMode;

	private String cityName;
	
	private String organizer;

	private String coorganizer;

	private Timestamp createTime;

	private Integer creater;

	private String startDate;
	
	private String endDate;

	private Byte entryCode;
	
	private Byte entryStatus;

	private String headImage;

	private String hotline;

	private String itemCode;

	private String itemName;

	private Integer limitMax;

	private Integer limitMin;

	private String matchContext;

	private String matchDate;

	private Timestamp modifeTime;

	private Integer modifer;

	private String name;

	private String regionName;

	private byte shelveCode;

	private String thumbnail;

	public Match() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public Byte getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Byte auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Timestamp getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getCoorganizer() {
		return coorganizer;
	}

	public void setCoorganizer(String coorganizer) {
		this.coorganizer = coorganizer;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Byte getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(Byte entryCode) {
		this.entryCode = entryCode;
	}

	public Byte getEntryStatus() {
		return entryStatus;
	}

	public void setEntryStatus(Byte entryStatus) {
		this.entryStatus = entryStatus;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getLimitMax() {
		return limitMax;
	}

	public void setLimitMax(Integer limitMax) {
		this.limitMax = limitMax;
	}

	public Integer getLimitMin() {
		return limitMin;
	}

	public void setLimitMin(Integer limitMin) {
		this.limitMin = limitMin;
	}

	public String getMatchContext() {
		return matchContext;
	}

	public void setMatchContext(String matchContext) {
		this.matchContext = matchContext;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public Timestamp getModifeTime() {
		return modifeTime;
	}

	public void setModifeTime(Timestamp modifeTime) {
		this.modifeTime = modifeTime;
	}

	public Integer getModifer() {
		return modifer;
	}

	public void setModifer(Integer modifer) {
		this.modifer = modifer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public byte getShelveCode() {
		return shelveCode;
	}

	public void setShelveCode(byte shelveCode) {
		this.shelveCode = shelveCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


}