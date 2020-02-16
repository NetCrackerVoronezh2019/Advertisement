package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.time.LocalDateTime;


public class AdvertisementModel {
	
	private Long advertisementId;
	private Long authorId;
	private String advertisementName;
	private LocalDateTime deadline;
	private String description;
	private LocalDateTime dateOfPublication;
	private String budget;
	private SubjectModel section;
	public Long getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDateOfPublication() {
		return dateOfPublication;
	}
	public void setDateOfPublication(LocalDateTime dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public SubjectModel getSection() {
		return section;
	}
	public void setSection(SubjectModel section) {
		this.section = section;
	}
	
	
}
