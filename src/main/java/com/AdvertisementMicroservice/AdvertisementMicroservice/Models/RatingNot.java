package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;


import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;

public class RatingNot {
	
	private Long orderId;
	private double rating;
	private String comment;
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
		
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	
	
	
}
