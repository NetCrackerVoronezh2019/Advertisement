package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {

	public List<Order> findByCustomerId(Long id);
	
	public List<Order> findByFreelancerId(Long id);
	
	@Query(value = "Select avg(starsforwork) from orders "
			+ "where starsforwork>0 and freelancerid=?1", 
			  nativeQuery = true)
	public Optional<Double> findAllRaitings(Long id);
	
	
	@Query(value = "Select * from orders where advertisementid=?1", 
			  nativeQuery = true)
	public Optional<Order> findAllByAdvId(Long id);
	
	
	
	
	@Query(value = "Select * from orders"
			+ " where starsforwork>0 and freelancerid=?1 and status='СOMPLETED'", 
			  nativeQuery = true)
	public Optional<List<Order>> findAllFeedBackByFreelancerId(Long id);
	


}
