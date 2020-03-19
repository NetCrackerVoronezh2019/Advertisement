package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.SubjectService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name="ADVERTISEMENTS")
public class Advertisement {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADVERTISEMENTID")
	private Long advertisementId;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING) 
	@JsonIgnore
	private AdvertisementStatus status;
	
	@Column(name="SECTION")
	private String section;
	
	@Column(name="NAME")
	private String advertisementName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="BUDGET")
	private String budget;
	
	@Column(name="DATEOFPUBLICATION")
	@JsonProperty(access=Access.READ_ONLY)
	private LocalDateTime dateOfPublication;
	
	@Column(name="DEADLINE")
	private LocalDateTime deadline;
	
	@Column(name="IMAGEKEY")
	private String imageKey;
	
	@Column(name="AUTHORID")
	private Long authorId;
	
	@Column(name="TEACHER_ID")
	private Long teacherId;
	
	
	
	public AdvertisementStatus getStatus() {
		return status;
	}

	public void setStatus(AdvertisementStatus status) {
		this.status = status;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getSection() {
		return section;
	}
	
	public String getImageKey() {
		return imageKey;
	}

	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}

	public void setSection(String section) {
		this.section = section;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public LocalDateTime getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(LocalDateTime dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	
	
}
