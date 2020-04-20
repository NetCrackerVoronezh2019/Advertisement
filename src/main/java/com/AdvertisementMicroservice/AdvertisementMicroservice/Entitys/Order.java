package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.*;



@Entity
@Table(name="ORDERS")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORDERID")
	private Long orderId;
	
	@Column(name="CUSTOMERID")
	private Long customerId;
	
	@Column(name="FREELANCERID")
	private Long freelancerId;
	
	@Column(name="ADVERTISEMENTID")
	private Long advertisementId;
	
	@Column(name="ADVERTISEMENTNAME")
	private String advertisementName;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="STARSFORWORK")
	private double starsForWork;

	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getStarsForWork() {
		return starsForWork;
	}

	public void setStarsForWork(double starsForWork) {
		this.starsForWork = starsForWork;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
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
	
	

	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
}
