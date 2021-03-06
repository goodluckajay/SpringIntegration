package com.syne;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class PrintService {
	
	public Message<String> print(Message<String> message)
	{
		System.out.println(message.getPayload());		
		return MessageBuilder.withPayload("I Acknowledge." + message.getHeaders().get("MessageNumber")).build();
	}

}
