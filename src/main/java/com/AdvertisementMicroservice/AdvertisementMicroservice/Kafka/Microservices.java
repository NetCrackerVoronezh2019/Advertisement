package com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="microservices")
public class Microservices {
	
	private String mainPort;
	private String advertismentPort;
	private String amazonPort;
	private String conversationPort;
	private String userAndgroupsPort;
	private String host;
	private String main_token;
	private String advertisement_token;
	private String conversation_token;
	private String userAndgroups_token;
	private String amazon_token;
	
	
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMain_token() {
		return main_token;
	}
	public void setMain_token(String main_token) {
		this.main_token = main_token;
	}
	public String getAdvertisement_token() {
		return advertisement_token;
	}
	public void setAdvertisement_token(String advertisement_token) {
		this.advertisement_token = advertisement_token;
	}
	public String getConversation_token() {
		return conversation_token;
	}
	public void setConversation_token(String conversation_token) {
		this.conversation_token = conversation_token;
	}
	public String getUserAndgroups_token() {
		return userAndgroups_token;
	}
	public void setUserAndgroups_token(String userAndgroups_token) {
		this.userAndgroups_token = userAndgroups_token;
	}
	public String getAmazon_token() {
		return amazon_token;
	}
	public void setAmazon_token(String amazon_token) {
		this.amazon_token = amazon_token;
	}
	public String getMainPort() {
		return mainPort;
	}
	public void setMainPort(String mainPort) {
		this.mainPort = mainPort;
	}
	public String getAdvertismentPort() {
		return advertismentPort;
	}
	public void setAdvertismentPort(String advertismentPort) {
		this.advertismentPort = advertismentPort;
	}
	public String getAmazonPort() {
		return amazonPort;
	}
	public void setAmazonPort(String amazonPort) {
		this.amazonPort = amazonPort;
	}
	public String getConversationPort() {
		return conversationPort;
	}
	public void setConversationPort(String conversationPort) {
		this.conversationPort = conversationPort;
	}
	
	

	
	public String getUserAndgroupsPort() {
		return userAndgroupsPort;
	}
	public void setUserAndgroupsPort(String userAndgroupsPort) {
		this.userAndgroupsPort = userAndgroupsPort;
	}
	public void setMicroservicesInfo(List<MicroserviceInfo> models)
	{
		for(MicroserviceInfo model:models)
			this.setMicroserviceInfo(model);
	}
	
	
	public void setMicroserviceInfo(MicroserviceInfo infoModel)
	{
		
		if(infoModel!=null)
		{
			if(infoModel.getMicroserviceName()!=null && infoModel.getPort()!=null)
			{
				if(infoModel.getMicroserviceName()==MicroservicesEnum.MAIN)
				{
					this.setMainPort(infoModel.getPort().toString());
					this.setMain_token(infoModel.getToken());
			
				}
				else
				{
					if(infoModel.getMicroserviceName()==MicroservicesEnum.ADVERTISEMENT)
					{
						this.setAdvertismentPort(infoModel.getPort().toString());
						this.setAdvertisement_token(infoModel.getToken());
					}
					else
					{
						if(infoModel.getMicroserviceName()==MicroservicesEnum.CONVERSATION)
						{
							this.setConversationPort(infoModel.getPort().toString());
							this.setConversation_token(infoModel.getToken());
						}
						else
						{	
							if(infoModel.getMicroserviceName()==MicroservicesEnum.USERANDGROUPS)
							{
								this.setUserAndgroupsPort(infoModel.getPort());
								this.setUserAndgroups_token(infoModel.getToken());
							}
							else
							{
								if(infoModel.getMicroserviceName()==MicroservicesEnum.AMAZON)
								{
									this.setAmazonPort(infoModel.getPort().toString());
									this.setAmazon_token(infoModel.getToken());
								}
							}
						}
					}
				}
			}
		}
	}
	
	

}
