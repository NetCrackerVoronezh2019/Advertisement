package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.util.List;

public class CompleteOrderModel {
	
	private Long orderId;
	private Long userId;
	private String roleName;
	public List<AmazonModel> allFiles;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<AmazonModel> getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(List<AmazonModel> allFiles) {
		this.allFiles = allFiles;
	}
	
	
	
}
