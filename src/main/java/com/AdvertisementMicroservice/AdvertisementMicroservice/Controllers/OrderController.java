package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationResponseStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationType;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.ChangeOrderStatusModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.ChangeRatingModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.MyOrderModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.MyOrdersModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.NotificationService;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.OrderService;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private NotificationService notService;
	
	@PostMapping("getMyOrders")
	public ResponseEntity<List<Order>> getMyOrders(@RequestBody MyOrdersModel model)
	{
		List<Order> orders=new ArrayList<Order>();
		if(model.getRoleName().equals("ROLE_STUDENT"))
			orders=orderService.findByCustomerId(model.getId());
		if(model.getRoleName().equals("ROLE_TEACHER"))
			orders=orderService.findByFreelancerId(model.getId());
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	
	@PostMapping("changeRating")
	public ResponseEntity<?> changeRating(@RequestBody ChangeRatingModel model)
	{
		Order order=orderService.findByOrder(model.getOrderId()).get();
		order.setStarsForWork(model.getReiting());
		orderService.save(order);
		Notification not=new Notification();
		not.setSenderId(model.getCustomerId());
		not.setAddresseeId(model.getFreelancerId());
		not.setStatus(NotificationStatus.UNREADED);
		not.setResponseStatus(NotificationResponseStatus.UNREADED);
		not.setType(NotificationType.CHANGE_REITING);
		not.setOrderId(model.getOrderId());
		not.setAdvertisementId(model.getAdvertisementId());
		notService.save(not);
		return new ResponseEntity<>(null,HttpStatus.OK);
		
	}
	
	@PostMapping("getMyOrder")
	public ResponseEntity<Order> getMyOrder(@RequestBody MyOrderModel model)
	{
		Order o=orderService.findByOrder(model.getOrderId()).get();
		return new ResponseEntity<>(o,HttpStatus.OK);
	}
	
	
	@GetMapping("getOrderStatuses")
	public ResponseEntity<OrderStatus[]> getOrderStatuses()
	{
		return new ResponseEntity<>(OrderStatus.values(),HttpStatus.OK);
	}
	
	
	@PostMapping("changeOrderStatus")
	public ResponseEntity<?> changeOrderStatus(@RequestBody ChangeOrderStatusModel model )
	{
		Optional<Order> order=orderService.findByOrder(model.getOrderId());
		Order o=order.get();
		if(model.getRoleName().equals("ROLE_TEACHER") )
		{
			if(o.getFreelancerId()==model.getUserId())
			{
				o.setStatus(model.getOrderStatus());
				orderService.save(o);
				Notification not=notService.generateOrderNotification(model);
				notService.save(not);
				return new ResponseEntity<>(null,HttpStatus.OK);
				
			}
			else
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		else
			if(model.getRoleName().equals("ROLE_STUDENT"))
			{
				if(o.getCustomerId()==model.getOrderId())
				{
					o.setStatus(model.getOrderStatus());
					orderService.save(o);
					return new ResponseEntity<>(null,HttpStatus.OK);
				}
			}
		
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
}
