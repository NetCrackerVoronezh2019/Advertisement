package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import java.time.LocalDateTime;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="ADVERTISEMENTS")
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADVERTISEMENTID")
	private Long advertisementId;
	
	@Column(name="section")
	@Enumerated(EnumType.STRING)
	private AdvertisementSections section;
	
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
	
	@Column(name="AUTHORID")
	private Long authorId;

	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public AdvertisementSections getSection() {
		return section;
	}

	public void setSection(AdvertisementSections section) {
		this.section = section;
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
