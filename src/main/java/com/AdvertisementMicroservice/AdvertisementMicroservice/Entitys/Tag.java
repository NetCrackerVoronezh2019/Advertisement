package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TAGS")
public class Tag {

	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TAGID")
	@Id
	private Long id;
	
	@Column(name="TAGNAME")	
	private String name;
	
	@JsonIgnore
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADVERTISEMENTID")
    private Advertisement advertisement;


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

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	
	
	
	
}
