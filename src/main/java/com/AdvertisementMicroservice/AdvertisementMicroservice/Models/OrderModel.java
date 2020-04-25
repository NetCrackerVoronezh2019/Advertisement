package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;

public class OrderModel {
	
	private Long orderId;	
	private Long customerId;
	private Long freelancerId;
	private Long advertisementId;
	private String advertisementName;
	private OrderStatus status;
	private double starsForWork;
	private OrderStatus nextStatus;
	private String freelancerFIO;
	private String customerFIO;
	private String comment;
	private Advertisement advertisement; 
	
	
	
	public String getFreelancerFIO() {
		return freelancerFIO;
	}
	public void setFreelancerFIO(String freelancerFIO) {
		this.freelancerFIO = freelancerFIO;
	}
	public String getCustomerFIO() {
		return customerFIO;
	}
	public void setCustomerFIO(String customerFIO) {
		this.customerFIO = customerFIO;
	}
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	public static OrderModel orderToOrderModel(Order order)
	{
		OrderModel model=new OrderModel();
		model.setOrderId(order.getOrderId());
		model.setCustomerId(order.getCustomerId());
		model.setFreelancerId(order.getFreelancerId());
		model.setAdvertisement(order.getAdvertisement());
		model.setStatus(order.getStatus());
		model.setStarsForWork(order.getStarsForWork());
		model.setNextStatus(nextOrderStatus(order.getStatus()));
		model.setComment(order.getComment());
		return model;
	}
	
	
	public static List<OrderModel> orderListToModelList(List<Order> orders)
	{
		List<OrderModel> orderModels=new ArrayList<>();
		for(Order o:orders)
			orderModels.add(orderToOrderModel(o));
		
		return orderModels;
	}
	
	public static OrderStatus nextOrderStatus(OrderStatus status)
	{
		List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());
		orderStatusList=orderStatusList.stream().
						filter(s->s.getStatusWeight()>status.getStatusWeight())
						.collect(Collectors.toList());
		if(orderStatusList.size()==0)
			return null;
		return orderStatusList.get(0);
		
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(Long freelancerId) {
		this.freelancerId = freelancerId;
	}

	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public double getStarsForWork() {
		return starsForWork;
	}

	public void setStarsForWork(double starsForWork) {
		this.starsForWork = starsForWork;
	}

	public OrderStatus getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(OrderStatus nextStatus) {
		this.nextStatus = nextStatus;
	}
	
	
}
