package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationType;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;
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
	
	public Order generateOrder(Notification notif)
	{
		Order order=new Order();
		order.setStatus(OrderStatus.INPROGRESS);
		order.setAdvertisementId(notif.getAdvertisementId());
		order.setAdvertisementName(notif.getAdvertisementName());
		
		if(notif.getType()==NotificationType.TAKE_ADVERTISEMENT)
		{
			order.setCustomerId(notif.getAddresseeId());
			order.setFreelancerId(notif.getSenderId());
		}
		else
		{
			if(notif.getType()==NotificationType.RECEIVE_SERVICE)
			{
				order.setCustomerId(notif.getSenderId());
				order.setFreelancerId(notif.getAddresseeId());
			}
		}
		
		return order;
	}
}
