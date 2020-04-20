package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class AdvertisementModel {
	
	private Long advertisementId;
	private Long authorId;
	private String authorRole;
	private String advertisementName;
	private LocalDateTime deadline;
	private String description;
	private LocalDateTime dateOfPublication;
	private Integer budget;
	private String section;
	private String content;
	private Tag[] tags;
	private List<AmazonModel> allFiles;
	
	
	public List<AmazonModel> getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(List<AmazonModel> allFiles) {
		this.allFiles = allFiles;
	}
	public Tag[] getTags() {
		return tags;
	}
	public void setTags(Tag[] tags) {
		this.tags = tags;
	}
	public String getAuthorRole() {
		return authorRole;
	}
	public void setAuthorRole(String authorRole) {
		this.authorRole = authorRole;
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
	
	
	public LocalDateTime getDeadline() {
		return deadline;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
	public Integer getBudget() {
		return budget;
	}
	public void setBudget(Integer budget) {
		this.budget = budget;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
}
