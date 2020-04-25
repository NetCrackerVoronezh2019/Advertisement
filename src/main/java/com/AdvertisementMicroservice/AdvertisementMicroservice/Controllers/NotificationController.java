package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationResponseStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationType;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.FullNotificationModel;
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
	
	@Autowired 
	private AdvertisementService advService;
	
	
	
	@GetMapping("getCommonNots/{senderId}/{addresseeId}")
	public ResponseEntity<List<Notification>> getCommonNots(@PathVariable Long senderId,@PathVariable Long addresseeId)
	{
		List<Notification> notifications=this.notifService.findСommonNotifications(senderId,addresseeId);
		return new ResponseEntity<>(notifications,HttpStatus.OK);
	}
	
	@GetMapping("setNotificatiosAsReaded/{userId}")
	public ResponseEntity<?> setNotificationsAsReaded(@PathVariable Long userId)
	{
		notifService.setAllNotificationsAsReaded(userId);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@PostMapping("canSendRequest")
	public ResponseEntity<Boolean> canSendRequest(@RequestBody SendAdvertisementNotificationModel model)
	{
		Notification not=notifService.findBySenderIdAndAdvertisementId(model.getSenderId(),model.getAdvertisementId());
		if(not!=null)
			return new ResponseEntity<>(false,HttpStatus.OK);
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PostMapping("newNotification")
	public ResponseEntity<?> newNotification(@RequestBody Notification notif)
	{
		notif.setStatus(NotificationStatus.UNREADED);
		notif.setResponseStatus(NotificationResponseStatus.UNREADED);
		System.out.println("name "+notif.getAdvertisementName());
		notifService.save(notif);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@GetMapping("getMyAllNotifications/{userId}")
	public ResponseEntity<List<FullNotificationModel>> getMyAllNotifications(@PathVariable Long userId)
	{   
		List<Notification> notifs=notifService.getMyAllNotifications(userId).stream()
				.filter(n->n.getResponseStatus()==NotificationResponseStatus.UNREADED)
				.collect(Collectors.toList());
		List<Advertisement> advs=advService.findAll();
		List<FullNotificationModel> full=new ArrayList<>();
		
		for(Notification n:notifs)
		{
			Advertisement adv=advs.stream().filter(a->a.getAdvertisementId().equals(n.getAdvertisementId()))
											.findFirst().get();
			FullNotificationModel fm=new FullNotificationModel();
			fm.setAdvertisementName(adv.getAdvertisementName());
			fm.setNotification(n);
			full.add(fm);
			
		}
		return new ResponseEntity<>(full,HttpStatus.OK);
		
		
	}
	
	@GetMapping("getMyAllNotificationsSize/{userId}")
	public ResponseEntity<Integer> getMyAllNotificationsSize(@PathVariable Long userId)
	{   
		return new ResponseEntity<>(notifService.getMyAllNotifications(userId).stream()
				.filter(n->n.getStatus()==NotificationStatus.UNREADED)
				.collect(Collectors.toList()).size()
				,HttpStatus.OK);
		
	}
	
	@PostMapping("notificationResponse")
	public ResponseEntity<?> notificationResponse(@RequestBody Notification notif) 
	{
		Notification newNotif=new Notification();
		notif.setStatus(NotificationStatus.READED);
		if(notif.getResponseStatus()==NotificationResponseStatus.ACCEPTED)
		{
			newNotif=notifService.generateResponseNotification(notif);
			Order order=orderService.generateOrder(notif);
			notifService.save(newNotif);
			Advertisement adv=advService.findById(notif.getAdvertisementId());
			order.setAdvertisement(adv);
			orderService.save(order);
	
			try {
				UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088//advertisement/createDialog").
						queryParam("creatorId",notif.getSenderId())
						.queryParam("userId",notif.getAddresseeId())
						.queryParam("advertisementName",adv.getAdvertisementName());
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<Integer> res=restTemplate.exchange(uriBuilder.build().encode().toUri(),HttpMethod.POST,null,new ParameterizedTypeReference<Integer>(){});
			}
			catch(Exception ex)
			{
				
			}
		    
		
			
		}
		if(notif.getResponseStatus()==NotificationResponseStatus.REJECTED)
		{
			newNotif=notifService.generateResponseNotification(notif);
			notifService.save(newNotif);
		}
		notifService.save(notif);		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	
}
