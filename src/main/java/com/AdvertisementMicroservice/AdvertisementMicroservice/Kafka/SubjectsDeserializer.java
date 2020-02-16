package com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.apache.kafka.common.serialization.Deserializer;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SubjectsDeserializer implements Deserializer<SubjectServiceX> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public SubjectServiceX deserialize(String topic, byte[] data) {
		
		ObjectMapper mapper = new ObjectMapper();
		SubjectServiceX subjects = null;
	   try {
	     subjects = mapper.readValue(data,SubjectServiceX.class);
	   } catch (Exception e) {
	     e.printStackTrace();
	   }
	  
	   return subjects;
	 }

	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
