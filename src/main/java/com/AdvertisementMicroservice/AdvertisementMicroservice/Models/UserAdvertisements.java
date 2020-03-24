package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementStatus;

public class UserAdvertisements {

	private Long userId;
	private AdvertisementStatus status;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public AdvertisementStatus getStatus() {
		return status;
	}
	public void setStatus(AdvertisementStatus status) {
		this.status = status;
	}
	
	
}
