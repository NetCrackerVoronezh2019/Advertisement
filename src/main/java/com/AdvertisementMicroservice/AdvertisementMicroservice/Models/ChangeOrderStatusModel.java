package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;

public class ChangeOrderStatusModel {

	private Long orderId;
	private OrderStatus orderStatus;
	private Long userId;
	private String roleName;
	
	
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
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
