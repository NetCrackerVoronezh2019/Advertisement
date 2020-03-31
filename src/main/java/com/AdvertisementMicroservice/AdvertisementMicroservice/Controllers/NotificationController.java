package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationResponseStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationType;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.SendAdvertisementNotificationModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.NotificationRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.OrderRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.AdvertisementService;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.NotificationService;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.OrderService;

@RestController
@CrossOrigin("http://localhost:4200")
public class NotificationController {
	
	@Autowired 
	private NotificationService notifService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("canSendRequest")
	public ResponseEntity<?> canSendRequest(@RequestBody SendAdvertisementNotificationModel model)
	{
		Notification not=notifService.findBySenderIdAndAdvertisementId(model.getSenderId(),model.getAdvertisementId());
		if(not!=null)
			return new ResponseEntity<>(false,HttpStatus.OK);
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PostMapping("newNotification")
	public ResponseEntity<?> newNotification(@RequestBody Notification notif)
	{
		notif.setStatus(NotificationResponseStatus.UNREADED);
		System.out.println("name "+notif.getAdvertisementName());
		notifService.save(notif);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@GetMapping("getMyAllNotifications/{userId}")
	public ResponseEntity<List<Notification>> getMyAllNotifications(@PathVariable Long userId)
	{   
		return new ResponseEntity<>(notifService.getMyAllNotifications(userId).stream()
				.filter(n->n.getStatus()==NotificationResponseStatus.UNREADED)
				.collect(Collectors.toList())
				,HttpStatus.OK);
		
	}
	
	@PostMapping("notificationResponse")
	public ResponseEntity<?> notificationResponse(@RequestBody Notification notif) 
	{
		Notification newNotif=new Notification();
		if(notif.getType()==NotificationType.TAKE_ADVERTISEMENT)
		{
			if(notif.getStatus()==NotificationResponseStatus.ACCEPTED)
			{
				newNotif.setType(NotificationType.ACCEPTED_TAKE_ADVERTISEMENT);
				newNotif.setAddresseeId(notif.getSenderId());
				newNotif.setSenderId(notif.getAddresseeId());
				newNotif.setAdvertisementId(notif.getAdvertisementId());
				newNotif.setStatus(NotificationResponseStatus.UNREADED);
				newNotif.setAdvertisementName(notif.getAdvertisementName());
				Order order=new Order();
				order.setCustomerId(notif.getAddresseeId());
				order.setFreelancerId(notif.getSenderId());
				order.setStatus(OrderStatus.INPROGRESS);
				order.setAdvertisementId(notif.getAdvertisementId());
				order.setAdvertisementName(notif.getAdvertisementName());
				notifService.save(newNotif);
				orderService.save(order);
			}
			if(notif.getStatus()==NotificationResponseStatus.REJECTED)
			{
				newNotif.setType(NotificationType.REJECTED_TAKE_ADVERTISEMENT);
				newNotif.setAddresseeId(notif.getSenderId());
				newNotif.setSenderId(notif.getAddresseeId());
				newNotif.setAdvertisementId(notif.getAdvertisementId());
				newNotif.setStatus(NotificationResponseStatus.UNREADED);
				newNotif.setAdvertisementName(notif.getAdvertisementName());
				notifService.save(newNotif);
			}
		}
		notifService.save(notif);		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	
}
