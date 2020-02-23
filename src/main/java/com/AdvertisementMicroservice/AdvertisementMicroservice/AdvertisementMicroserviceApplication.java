package com.AdvertisementMicroservice.AdvertisementMicroservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka.*;


@SpringBootApplication
public class AdvertisementMicroserviceApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext app=SpringApplication.run(AdvertisementMicroserviceApplication.class, args);
		
		ConsumerThreadService a = (ConsumerThreadService) app.getBean("consumerThreadService");		
		Utility utility=(Utility) app.getBean("utility");	
	 //   utility.sendPortModelToConfig("http://localhost:7082/setPortModel");
	 //   Thread consumerThread1 =new Thread(a.getRunnable());
	 //   Thread consumerThread2 =new Thread(a.getRunnable2());
	  //  consumerThread1.start();
	   // consumerThread2.start();	    
		}

}
