package com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka;


public class MicroserviceInfo {
	
	private MicroservicesEnum microserviceName;
	private String port;
	private String token;
	
	public MicroservicesEnum getMicroserviceName() {
		return microserviceName;
	}
	public void setMicroserviceName(MicroservicesEnum microserviceName) {
		this.microserviceName = microserviceName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "MicroserviceInfo [microserviceName=" + microserviceName + ", port=" + port + ", token=" + token + "]";
	}
	
	
	
	
}
