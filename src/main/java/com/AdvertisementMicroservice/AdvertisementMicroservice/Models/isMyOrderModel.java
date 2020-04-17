package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import javax.validation.constraints.NotNull;

public class isMyOrderModel {
	
	@NotNull
	private Long userId;
	@NotNull
	private Long advertisementId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}
	
	
}
