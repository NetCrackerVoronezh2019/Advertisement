package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AmazonModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.Tag;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	private AdvertisementStatus status;
	
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private AdvertisementType type;
	
	@Column(name="SECTION")
	private String section;
	
	@Column(name="NAME")
	private String advertisementName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="BUDGET")
	private Integer budget;
	
	@Column(name="DATEOFPUBLICATION")
	@JsonProperty(access=Access.READ_ONLY)
	private LocalDateTime dateOfPublication;
	
	@Column(name="DEADLINE")
	private LocalDateTime deadline;
	
	@Column(name="AUTHORID")
	private Long authorId;
	
	@Column(name="TAGS")
	private String tags;
	
	@Column(name="СOVERIMAGEKEY")
	private String coverImageKey;
	
	 @OneToMany(mappedBy = "advertisement", fetch = FetchType.EAGER)
	    private Collection<Attachment> attachments;
	 
	 
	 @JsonGetter("attachments")
	 public List<String> sendAttachmentsKeys(){
		 Collection<Attachment> att=this.getAttachment();
		 if(att==null)
			 return new ArrayList<String>();
		 List<String> list=att.stream()
		 	.map(s->s.getKey())
		 	.collect(Collectors.toList());
		
		 	return list;
		 
	 }
	public Collection<Attachment> getAttachment() {
		return attachments;
	}

	public void setAttachments(Collection<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setTags(Tag[] tags)
	{
		String allTags=new String();	
		for(Tag tag:tags)
		{
			allTags+=tag.name+",";
		}
		
		this.setTags(allTags);
	}
	
	@JsonGetter("tags")
	public Tag[] getTagsArray()
	{
	   if(this.getTags()==null)
		   return new Tag[0];
	   String[] t=this.getTags().split(",");
	   Tag[] tags=new Tag[t.length];
	   for(int i=0; i<t.length;i++)
	   {
		   tags[i]=new Tag();
		   tags[i].name=t[i];
	   }
	   return tags;   
	}
	
	
	
	public String[] getAttachmentKeys(List<AmazonModel> keys)
	{
		String str="";
		String[] arr=new String[keys.size()];
		String newKey="";
		for(int i=0;i<keys.size();i++)
		{
		  	newKey="adv"+this.getAdvertisementId()+"_"+keys.get(i).getName();
		  	arr[i]=newKey;
		  	str+=newKey+",";
		}
		
		
		return arr;
	}
	
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public AdvertisementStatus getStatus() {
		return status;
	}

	public void setStatus(AdvertisementStatus status) {
		this.status = status;
	}

	public String getSection() {
		return section;
	}

	public String getCoverImageKey() {
		return coverImageKey;
	}
	public void setCoverImageKey(String coverImageKey) {
		this.coverImageKey = coverImageKey;
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

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
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

	public AdvertisementType getType() {
		return type;
	}

	public void setType(AdvertisementType type) {
		this.type = type;
	}
}
