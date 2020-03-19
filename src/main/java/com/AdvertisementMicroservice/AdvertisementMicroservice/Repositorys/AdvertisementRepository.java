package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long>{
	
	public List<Advertisement> findByAuthorId(Long id);
	
	
	public List<Advertisement> findByTeacherId(Long id);
	
	
	
	
}
