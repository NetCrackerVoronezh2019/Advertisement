package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired 
	private NotificationRepository notRep;
	
	public List<Notification> getMyAllNotifications(Long userId)
	{
		return notRep.findByAddresseeId(userId);
	}
	
	public void save(Notification not)
	{
		notRep.save(not);
	}
	
	public Notification findBySenderIdAndAdvertisementId(Long id1,Long id2)
	{
		return notRep.findBySenderIdAndAdvertisementId(id1, id2);
	}
}
