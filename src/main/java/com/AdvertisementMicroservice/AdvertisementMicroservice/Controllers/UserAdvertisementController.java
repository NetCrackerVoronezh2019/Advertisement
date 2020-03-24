package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.UserAdvertisements;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.AdvertisementService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserAdvertisementController {

	@Autowired
	private AdvertisementService advService;
	
	@GetMapping("myAdvertisements/{userId}")
	public List<Advertisement> getStudnetAdvertisements(@PathVariable Long userId)
	{
		return advService.getAdvertisementsByAuthorId(userId);
	}
	
	
	@GetMapping("myAdvertisements/{teacherId}")
	public List<Advertisement> getTeacherAdvertisements(@PathVariable Long teacherId)
	{
		return advService.getAdvertisementsByTeacherId(teacherId);
	}
	
	
	@PostMapping("getStudentAdvertisements")
	public List<Advertisement> getAdvByIdAndStatus(@RequestBody UserAdvertisements userAdvs)
	{
		return advService.getAdvByAuthorIdAndStatus(userAdvs.getUserId(), userAdvs.getStatus());
	}
	
}
