package com.awstechguide.aws.springcloudlambdadynamodb.component;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.awstechguide.aws.springcloudlambdadynamodb.service.TransactionService;
@Component
public class TransactionComponent implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

	@Autowired
	TransactionService transactionServ;
	
	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent t) {
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setStatusCode(200);
        responseEvent.setBody("");
		return responseEvent;
	}
	

}

