package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

	public List<Notification> findByAddresseeId(Long id);
	
	public Notification findBySenderIdAndAdvertisementId(Long id1, Long id2);
}

