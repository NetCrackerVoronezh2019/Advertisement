package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;

public class FullNotificationModel {
	
	private String advertisementName;
	private Notification notification;
	
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	
	
}
