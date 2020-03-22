package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class AdvertisementModel {
	
	private Long advertisementId;
	private Long authorId;
	private String advertisementName;
	private LocalDateTime deadline;
	private String description;
	private LocalDateTime dateOfPublication;
	private String budget;
	private String section;
	private String content;
	
	
	@Override
	public String toString() {
		return "AdvertisementModel [advertisementId=" + advertisementId + ", authorId=" + authorId
				+ ", advertisementName=" + advertisementName + ", deadline=" + deadline + ", description=" + description
				+ ", dateOfPublication=" + dateOfPublication + ", budget=" + budget + ", section=" + section
				+ ", content=" + content + "]";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime getDeadline() {
		return deadline;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
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
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
}
