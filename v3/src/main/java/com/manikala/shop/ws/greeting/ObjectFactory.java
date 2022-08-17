package com.manikala.shop.ws.greeting;

import javax.xml.bind.annotation.XmlRegistry;



@XmlRegistry
public class ObjectFactory {



    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetGreetingResponse }
     *
     */
    public GetGreetingResponse createGetGreetingResponse() {
        return new GetGreetingResponse();
    }

    /**
     * Create an instance of {@link Greeting }
     *
     */
    public Greeting createGreeting() {
        return new Greeting();
    }

    /**
     * Create an instance of {@link GetGreetingRequest }
     *
     */
    public GetGreetingRequest createGetGreetingRequest() {
        return new GetGreetingRequest();
    }

}
