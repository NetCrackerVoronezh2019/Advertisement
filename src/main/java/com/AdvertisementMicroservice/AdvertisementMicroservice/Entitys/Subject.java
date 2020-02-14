package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBJECTS")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	@Column(name="NAME")
	private String name;
	@Column(name="TRANSLATENAME")
	private String translateName;
	@Column(name="URL")
	private String url;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTranslateName() {
		return translateName;
	}
	public void setTranslateName(String translateName) {
		this.translateName = translateName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
