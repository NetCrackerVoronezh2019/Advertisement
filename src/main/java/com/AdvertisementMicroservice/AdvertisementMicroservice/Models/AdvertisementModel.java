package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.time.LocalDateTime;

public class AdvertisementModel {
	
	private Long userId;
	private String advertisementName;
	private String advertisementSection;
	private LocalDateTime deadlineDate;
	private String description;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public String getAdvertisementSection() {
		return advertisementSection;
	}
	public void setAdvertisementSection(String advertisementSection) {
		this.advertisementSection = advertisementSection;
	}
	public LocalDateTime getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(LocalDateTime deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
