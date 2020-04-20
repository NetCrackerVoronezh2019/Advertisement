package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Attachment;



@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
	
	
}
