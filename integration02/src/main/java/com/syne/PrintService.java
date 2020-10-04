package com.syne;

import org.springframework.messaging.Message;

public class PrintService {
	
	public void print(Message<String> message)
	{
		System.out.println(message.getPayload());
		System.out.println("Headers : " + message.getHeaders());
	}

}
