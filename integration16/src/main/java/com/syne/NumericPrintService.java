package com.syne;

import org.springframework.messaging.Message;

public class NumericPrintService {
	public void print(Message<?> message)
	{
		System.out.println("NumericService : " + message.getPayload());		
	}
}
