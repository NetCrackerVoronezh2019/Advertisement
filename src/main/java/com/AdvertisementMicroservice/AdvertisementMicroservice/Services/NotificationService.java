package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationResponseStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.NotificationType;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.ChangeOrderStatusModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired 
	private NotificationRepository notRep;
	
	@Autowired OrderService orderService;
	
	public List<Notification> getMyAllNotifications(Long userId)
	{
		return notRep.findByAddresseeId(userId);
	}
	
	public List<Notification> findСommonNotifications(Long senderId, Long addresseeid){
		return this.notRep.findСommonNotifications(senderId, addresseeid);
	}
	
	public void setAllNotificationsAsReaded(Long userId)
	{
		List<Notification> nots=notRep.findByAddresseeId(userId);
		if(nots!=null)
		{
			List<Notification> n=nots.stream()
				.filter(not->not.getStatus()==NotificationStatus.UNREADED)
				.collect(Collectors.toList());
			
			for(Notification updatedNot:n)
			{
				updatedNot.setStatus(NotificationStatus.READED);
				this.save(updatedNot);
			}
		}
		
		
			
	}
	public void save(Notification not)
	{
		notRep.save(not);
	}
	
	public Notification findBySenderIdAndAdvertisementId(Long id1,Long id2)
	{
		return notRep.findBySenderIdAndAdvertisementId(id1, id2);
	}
	
	public Notification generateResponseNotification(Notification notif)
	{
		Notification newNotif=new Notification();
		newNotif.setAddresseeId(notif.getSenderId());
		newNotif.setSenderId(notif.getAddresseeId());
		newNotif.setAdvertisementId(notif.getAdvertisementId());
		newNotif.setStatus(NotificationStatus.UNREADED);
		newNotif.setResponseStatus(NotificationResponseStatus.UNREADED);
		newNotif.setAdvertisementName(notif.getAdvertisementName());
		if(notif.getType()==NotificationType.TAKE_ADVERTISEMENT)
		{
			if(notif.getResponseStatus()==NotificationResponseStatus.ACCEPTED)
				newNotif.setType(NotificationType.ACCEPTED_TAKE_ADVERTISEMENT);
			if(notif.getResponseStatus()==NotificationResponseStatus.REJECTED)
				newNotif.setType(NotificationType.REJECTED_TAKE_ADVERTISEMENT);
		}
		else
		{
			if(notif.getType()==NotificationType.RECEIVE_SERVICE)
			{
				if(notif.getResponseStatus()==NotificationResponseStatus.ACCEPTED)
					newNotif.setType(NotificationType.ACCEPTED_RECEIVE_SERVICE);
				if(notif.getResponseStatus()==NotificationResponseStatus.REJECTED)
					newNotif.setType(NotificationType.REJECTED_RECEIVE_SERVICE);
			}
		}
		return newNotif;
	}
	
	
	public Notification generateOrderNotification(OrderStatus status,Long orderId)
	{	
		Notification n=new Notification();
		if(status==OrderStatus.INPROGRESS)
		{
			n.setType(NotificationType.CHANGE_ORDER_STATUS_TO_INPROGRESS);
			n.setStatus(NotificationStatus.UNREADED);
			n.setResponseStatus(NotificationResponseStatus.UNREADED);
			Order order=orderService.findByOrder(orderId).get();
			n.setAddresseeId(order.getCustomerId());
			n.setSenderId(order.getFreelancerId());
			n.setAdvertisementId(order.getAdvertisementId());
			n.setOrderId(orderId);
			
			return n;
		}
		if(status==OrderStatus.СOMPLETED)
		{
			n.setType(NotificationType.CHANGE_ORDER_STATUS_TO_COMPLETED);
			n.setStatus(NotificationStatus.UNREADED);
			n.setResponseStatus(NotificationResponseStatus.UNREADED);
			Order order=orderService.findByOrder(orderId).get();
			n.setAddresseeId(order.getCustomerId());
			n.setSenderId(order.getFreelancerId());
			n.setAdvertisementId(order.getAdvertisementId());
			n.setOrderId(orderId);
			return n;
		}
							
		return null;
	}
	
}
