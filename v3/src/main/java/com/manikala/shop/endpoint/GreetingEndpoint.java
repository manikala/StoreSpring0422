package com.manikala.shop.endpoint;


import com.manikala.shop.config.WebServiceConfig;
import com.manikala.shop.service.GreetingService;
import com.manikala.shop.ws.greeting.GetGreetingRequest;
import com.manikala.shop.ws.greeting.GetGreetingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GreetingEndpoint {


    private GreetingService greetingService;

    @Autowired
    public GreetingEndpoint(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_GREETING, localPart = "getGreetingRequest")
    @ResponsePayload
    public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest request)
            throws DatatypeConfigurationException {
        GetGreetingResponse response = new GetGreetingResponse();

        response.setGreeting(greetingService.generateGreeting(request.getName()));

        return response;
    }
}