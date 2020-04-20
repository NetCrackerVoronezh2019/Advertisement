package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ATTACHMENTS")
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ATTACHMENTID")
	private Long attachmentId;
	
	@Column(name="KEY")
	private String key;
	
	@JsonIgnore
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADVERTISEMENTID")
    private Advertisement advertisement;

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	
	
}
