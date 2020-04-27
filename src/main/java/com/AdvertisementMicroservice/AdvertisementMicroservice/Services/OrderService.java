package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationType;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRep;
	
	public List<Order> findByCustomerId(Long id)
	{
		return orderRep.findByCustomerId(id);
	}
	
	public Optional<List<Order>> findByFreelancerIdAndOrderStatus(Long id,OrderStatus status)
	{
		return this.orderRep.findAllByFreelancerIdAndOrderStatus(id, status.name());
	}
	
	public Optional<List<Order>> findByCustomerIdAndOrderStatus(Long id,OrderStatus status)
	{
		return this.orderRep.findAllByCustomerIdAndOrderStatus(id, status.name());
	}
	
	public Optional<Order> findByOrder(Long id)
	{
		return orderRep.findById(id);
	}
	
	
	public Optional<Order> findByAdvertisementId(Long id)
	{
		return orderRep.findAllByAdvId(id);
	}
	
	public List<Order> findByFreelancerId(Long id)
	{
		return orderRep.findByFreelancerId(id);
	}
	
	public Order save(Order order)
	{
		return orderRep.save(order);
	}
	
	public Order generateOrder(Notification notif)
	{
		Order order=new Order();
		order.setStatus(OrderStatus.ACCEPTED);

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
	
	public List<Order> findAllFeedBackByFreelancerId(Long id)
	{
		return this.orderRep.findAllFeedBackByFreelancerId(id).get();
	}
	
	
	
}
