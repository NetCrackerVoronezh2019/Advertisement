package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Tag;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag,Long>{

	@Query(value = "Select * from tags where advertisementid=?1", 
			  nativeQuery = true)
	public List<Tag> findAllByAdvId(Long id);
	
	@Modifying
	@Query(value = "delete  from tags where advertisementid=?1", 
			  nativeQuery = true)
	public void deletebyAdvId(Long id);
	
}
