package com.syne;

import org.springframework.messaging.Message;

public class CustomTransformer {
	
	public String transform(Message<String> message)
	{
		String token[] = message.getPayload().split(" ");
		return token[1] + "," + token[0];
	}
}
