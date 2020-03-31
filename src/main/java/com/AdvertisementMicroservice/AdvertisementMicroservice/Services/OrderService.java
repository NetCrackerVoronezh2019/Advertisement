package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRep;
	
	public List<Order> findByCustomerId(Long id)
	{
		return orderRep.findByCustomerId(id);
	}
	
	public List<Order> findByFreelancerId(Long id)
	{
		return orderRep.findByFreelancerId(id);
	}
	
	public void save(Order order)
	{
		orderRep.save(order);
	}
}
