package com.saidian.web.bean.cms;

import java.io.Serializable;
import java.util.Date;


public class PhyContext implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String scop;

	private String address;

	/**
	 * 经度
	 */
	private Double bdLongitude;

	/**
	 * 纬度
	 */
	private Double bdLatitude;

	private String telphone;
	
	/*
	 * 工作日 时间
	 */
	private Integer workdayTime;
	/*
	 * 休息日时间
	 */
	private Integer restdayTime;
	/**
	 * 预约方式
	 */
	private String type;

	/**
	 * 头图地址
	 */
	private String headUrl;

	/**
	 * 缩略图地址
	 */
	private String bodyUrl;

	/**
	 * 内容
	 */
	private String content;
	
	private Long creatUser;
	
	private Date creatDate;
	
	private Long updateUser;
	
	private Date updateDate;

	private String phyDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getScop() {
		return scop;
	}

	public void setScop(String scop) {
		this.scop = scop;
	}

	
	

	public Double getBdLongitude() {
		return bdLongitude;
	}

	public void setBdLongitude(Double bdLongitude) {
		this.bdLongitude = bdLongitude;
	}

	public Double getBdLatitude() {
		return bdLatitude;
	}

	public void setBdLatitude(Double bdLatitude) {
		this.bdLatitude = bdLatitude;
	}


	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWorkDayStartHour(){
		return workdayTime>>24;
	}
	public int getWorkDayStartMin(){
		return (workdayTime>>16)&0xff;
	}
	public int getWorkDayEndHour(){
		return (workdayTime>>8)&0xff;
	}
	public int getWorkDayEndMin(){
		return workdayTime&0xff;
	}
	
	public void setWorkDayTime(int startHour,int startMin,int endHour,int endMin){
		workdayTime=(startHour<<24)|(startMin<<16)|(endHour<<8)|endMin;
	}
	
	public int getRestDayStartHour(){
		return restdayTime>>24;
	}
	public int getRestDayStartMin(){
		return (restdayTime>>16)&0xff;
	}
	public int getRestDayEndHour(){
		return (restdayTime>>8)&0xff;
	}
	public int getRestDayEndMin(){
		return restdayTime&0xff;
	}
	public void setRestDayTime(int startHour,int startMin,int endHour,int endMin){
		restdayTime=(startHour<<24)|(startMin<<16)|(endHour<<8)|endMin;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getBodyUrl() {
		return bodyUrl;
	}

	public void setBodyUrl(String bodyUrl) {
		this.bodyUrl = bodyUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(Long creatUser) {
		this.creatUser = creatUser;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getPhyDate() {
		return phyDate;
	}

	public void setPhyDate(String phyDate) {
		this.phyDate = phyDate;
	}
}