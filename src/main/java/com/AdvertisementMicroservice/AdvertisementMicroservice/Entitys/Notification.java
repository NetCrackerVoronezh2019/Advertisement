package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="NOTIFICATIONS")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long notificationId;
	
	@Column(name="SENDERID")
	private Long senderId;
	
	@Column(name="ADVERTISEMENTID")
	private Long advertisementId;
	
	@Column(name="ORDERID")
	private Long orderId;
	
	@Column(name="ADVERTISEMENTNAME")
	private String advertisementName;
	
	@Column(name="ADDRESSEEID")
	private Long addresseeId;
	
	@Column(name="CERTIFICATENAME")
	private String certificateName;
	
	@Column(name="DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;
	
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private NotificationType type;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;
	
	
	@Column(name="RESPONSESTATUS")
	@Enumerated(EnumType.STRING)
	private NotificationResponseStatus responseStatus;
	
	
	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	public NotificationResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(NotificationResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	
	
	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getAdvertisementId() {
		return advertisementId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getAddresseeId() {
		return addresseeId;
	}

	public void setAddresseeId(Long addresseeId) {
		this.addresseeId = addresseeId;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}
		
}
