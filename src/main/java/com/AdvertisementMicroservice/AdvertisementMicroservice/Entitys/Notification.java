package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.*;

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
	
	@Column(name="ADVERTISEMENTNAME")
	private String advertisementName;
	
	@Column(name="ADDRESSEEID")
	private Long addresseeId;
	
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

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getAdvertisementId() {
		return advertisementId;
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
