package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
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
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.OrderModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.RatingNot;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.isMyOrderModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.OrderRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.NotificationService;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.OrderService;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired OrderRepository o;
	
	@Autowired 
	private NotificationService notService;
	

	
	@GetMapping("rating/{freelancerId}")
	public  ResponseEntity<Double> getRaiting(@PathVariable Long freelancerId)
	{
		Optional<Double> rating=this.o.findAllRaitings(freelancerId);
		if(rating.isPresent())
			return new ResponseEntity<>(rating.get(),HttpStatus.OK);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@PostMapping("haveIOrder")
	public ResponseEntity<OrderModel> haveIOrder(@RequestBody @Valid isMyOrderModel model)
	{
		Long advId=model.getAdvertisementId();
		Optional<Order> optionalOrder =orderService.findByAdvertisementId(advId);
		if(optionalOrder.isEmpty())
			return new ResponseEntity<>(null,HttpStatus.OK);
		Order order=optionalOrder.get();
		OrderModel orderModel=OrderModel.orderToOrderModel(order);
		if(orderModel.getFreelancerId().equals(model.getUserId()))
			return new ResponseEntity<>(orderModel,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.OK);
		
	}
	
	@PostMapping("getMyOrders")
	public ResponseEntity<List<OrderModel>> getMyOrders(@RequestBody MyOrdersModel model)
	{
		List<Order> orders=new ArrayList<Order>();
		if(model.getRoleName().equals("ROLE_STUDENT"))
			orders=orderService.findByCustomerId(model.getId());
		if(model.getRoleName().equals("ROLE_TEACHER"))
			orders=orderService.findByFreelancerId(model.getId());
		List<OrderModel> orderModels=OrderModel.orderListToModelList(orders);
		return new ResponseEntity<>(orderModels,HttpStatus.OK);
	}
	
	@GetMapping("getFreelancerOrders/{userId}")
	public ResponseEntity<List<OrderModel>> getallOrders(@PathVariable Long userId)
	{
		List<Order> orders=new ArrayList<Order>();
		orders=orderService.findByFreelancerId(userId);
		List<OrderModel> orderModels=OrderModel.orderListToModelList(orders);
		return new ResponseEntity<>(orderModels,HttpStatus.OK);
	}
	
	@PostMapping("changeRating")
	public ResponseEntity<?> changeRating(@RequestBody RatingNot model)
	{
		Notification notif=model.getNotif();
		Order order=orderService.findByOrder(notif.getOrderId()).get();
		order.setStarsForWork(model.getRating());
		notif.setResponseStatus(NotificationResponseStatus.ESTIMATED);
		orderService.save(order);
		this.notService.save(notif);
		
		Notification not=new Notification();
		not.setSenderId(notif.getAddresseeId());
		not.setAddresseeId(notif.getSenderId());
		not.setStatus(NotificationStatus.UNREADED);
		not.setResponseStatus(NotificationResponseStatus.UNREADED);
		not.setType(NotificationType.CHANGE_REITING);
		not.setOrderId(notif.getOrderId());
		not.setAdvertisementId(notif.getAdvertisementId());
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
	public ResponseEntity<Order> changeOrderStatus(@RequestBody ChangeOrderStatusModel model )
	{
		Optional<Order> order=orderService.findByOrder(model.getOrderId());
		Order o=order.get();
		if(model.getRoleName().equals("ROLE_TEACHER") )
		{
			if(o.getFreelancerId().equals(model.getUserId()))
			{
				o.setStatus(OrderModel.nextOrderStatus(o.getStatus()));
				o=orderService.save(o);
				Notification not=notService.generateOrderNotification(o.getStatus(),o.getOrderId());
				notService.save(not);
				return new ResponseEntity<>(o,HttpStatus.OK);
				
			}
			else
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		else
			if(model.getRoleName().equals("ROLE_STUDENT"))
			{
				if(o.getCustomerId().equals(model.getOrderId()))
				{
					o.setStatus(OrderModel.nextOrderStatus(o.getStatus()));;
					orderService.save(o);
					return new ResponseEntity<>(o,HttpStatus.OK);
				}
			}
		
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
}
