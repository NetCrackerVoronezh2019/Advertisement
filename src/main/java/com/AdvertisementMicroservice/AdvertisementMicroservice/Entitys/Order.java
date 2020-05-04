package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



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
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	
	
	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="STARSFORWORK")
	private double starsForWork;
	


	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADVERTISEMENTID")
    private Advertisement advertisement;

	
	
	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
}
