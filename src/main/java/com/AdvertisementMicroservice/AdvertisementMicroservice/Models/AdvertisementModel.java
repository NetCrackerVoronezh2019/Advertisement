package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
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
	private AmazonModel coverImage;
	public String coverImageKey;
	private Tag[] tags;
	public List<String> attachments;
	private List<AmazonModel> allFiles;
	
	public static AdvertisementModel advertisementToModel(Advertisement adv)
	{
		AdvertisementModel model=new AdvertisementModel();
		model.setAdvertisementId(adv.getAdvertisementId());
		model.setAuthorId(adv.getAuthorId());
		model.setAdvertisementName(adv.getAdvertisementName());
		model.setDeadline(adv.getDeadline());
		model.setDescription(adv.getDescription());
		model.setDateOfPublication(adv.getDateOfPublication());
		model.setBudget(adv.getBudget());
		model.setSection(adv.getSection());
		model.setCoverImageKey(adv.getCoverImageKey());
		model.setTags(getTagsArray(adv.getTags()));
		model.setAttachments(adv.sendAttachmentsKeys());
		
		return model;
			
	}
	
	
	public static List<AdvertisementModel> advListToModelList(List<Advertisement> advs)
	{
		List<AdvertisementModel> models=new ArrayList<AdvertisementModel>();
		
		for(Advertisement adv:advs)
		{
			AdvertisementModel model=advertisementToModel(adv);
			models.add(model);
		}
		
		return models;
	}
	
	
	
	public List<String> getAttachments() {
		return attachments;
	}



	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}



	public static Tag[] getTagsArray(String _tags)
	{
	   if(_tags==null)
		   return new Tag[0];
	   String[] t=_tags.split(",");
	   Tag[] tags=new Tag[t.length];
	   for(int i=0; i<t.length;i++)
	   {
		   tags[i]=new Tag();
		   tags[i].name=t[i];
	   }
	   return tags;   
	}
	

	public String getCoverImageKey() {
		return coverImageKey;
	}




	public void setCoverImageKey(String coverImageKey) {
		this.coverImageKey = coverImageKey;
	}




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
	
	public AmazonModel getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(AmazonModel coverImage) {
		this.coverImage = coverImage;
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
