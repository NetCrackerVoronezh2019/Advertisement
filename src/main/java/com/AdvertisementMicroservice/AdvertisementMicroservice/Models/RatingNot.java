package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;


import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;

public class RatingNot {
	
	private Order order;
	private double rating;
	private String comment;
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
		
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}
