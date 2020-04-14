package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;

public class RatingNot {
	
	private Notification notif;
	private double rating;
	
	public Notification getNotif() {
		return notif;
	}
	public void setNotif(Notification notif) {
		this.notif = notif;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}
