package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

	public List<Notification> findByAddresseeId(Long id);
	
	public Notification findBySenderIdAndAdvertisementId(Long id1, Long id2);
	
	
	@Query(value = "Select * from notifications\r\n" + 
			"where	addresseeid=?2 and  \r\n" + 
			"		senderid=?1 and\r\n" + 
			"		type in ('TAKE_ADVERTISEMENT','RECEIVE_SERVICE') and\r\n" + 
			"		responsestatus !='ACCEPTED'\r\n" + 
			"			", 
			  nativeQuery = true)
	public List<Notification> findСommonNotifications(Long senderId, Long addresseeid);
	
	

}

