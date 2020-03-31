package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.IsUserAdvertisementModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.UserAdvertisements;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.AdvertisementService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserAdvertisementController {

	@Autowired
	private AdvertisementService advService;
	
	@PostMapping("isUserAdvertisement")
	public ResponseEntity<Boolean> isUserAdv(@RequestBody IsUserAdvertisementModel model)
	{
		if(this.advService.is(model.userId, model.advertisementId))
		{
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		return new ResponseEntity<>(false,HttpStatus.OK);
	}
	
	@GetMapping("myAdvertisements/{userId}")
	public List<Advertisement> getStudnetAdvertisements(@PathVariable Long userId)
	{
		return advService.getAdvertisementsByAuthorId(userId);
	}
		
	@PostMapping("getStudentAdvertisements")
	public List<Advertisement> getAdvByIdAndStatus(@RequestBody UserAdvertisements userAdvs)
	{
		return advService.getAdvByAuthorIdAndStatus(userAdvs.getUserId(), userAdvs.getStatus());
	}
	
}
