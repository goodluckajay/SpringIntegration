package com.syne;

import org.springframework.messaging.Message;

public class PrintService {
	
	public void print(Message<String> message)
	{
		throw new RuntimeException("Exception !!");
	}

}
