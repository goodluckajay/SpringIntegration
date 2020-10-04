package com.syne;

import org.springframework.messaging.Message;

public class StringPrintService {
	
	public void print(Message<?> message)
	{
		System.out.println("StringService : " + message.getPayload());
	}

}
