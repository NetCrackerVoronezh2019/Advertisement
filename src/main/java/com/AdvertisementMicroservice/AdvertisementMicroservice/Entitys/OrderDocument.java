package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ORDERDOCUMENTS")
public class OrderDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DOCUMENTNAME")
	private String documentName;
	
	@Column(name="DOCUMENTKEY")
	private String documentKey;
	
	
	@JsonIgnore
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getDocumentKey() {
		return documentKey;
	}


	public void setDocumentKey(String documentKey) {
		this.documentKey = documentKey;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	


}
