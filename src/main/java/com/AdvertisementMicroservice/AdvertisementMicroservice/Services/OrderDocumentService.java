package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderDocument;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.OrderDocumentRepository;

@Service
public class OrderDocumentService {

	@Autowired
	private OrderDocumentRepository orderDocumentRepository;
	
	public OrderDocument save(OrderDocument orderDocument)
	{
		return this.orderDocumentRepository.save(orderDocument);
	}
	
	public void deleteDocumentByKey(String key)
	{
		this.orderDocumentRepository.deleteOrderDocument(key);
	}
} 
