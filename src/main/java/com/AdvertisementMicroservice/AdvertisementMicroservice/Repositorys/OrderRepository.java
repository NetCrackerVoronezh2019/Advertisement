package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {

	public List<Order> findByCustomerId(Long id);
	
	public List<Order> findByFreelancerId(Long id);
}
