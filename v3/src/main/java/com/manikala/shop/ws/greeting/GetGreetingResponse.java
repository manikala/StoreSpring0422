package com.manikala.shop.ws.greeting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "greeting"
})
@XmlRootElement(name = "getGreetingResponse")
public class GetGreetingResponse {

    @XmlElement(required = true)
    protected Greeting greeting;

    /**
     * Gets the value of the greeting property.
     *
     * @return
     *     possible object is
     *     {@link Greeting }
     *
     */
    public Greeting getGreeting() {
        return greeting;
    }

    /**
     * Sets the value of the greeting property.
     *
     * @param value
     *     allowed object is
     *     {@link Greeting }
     *
     */
    public void setGreeting(Greeting value) {
        this.greeting = value;
    }

}
