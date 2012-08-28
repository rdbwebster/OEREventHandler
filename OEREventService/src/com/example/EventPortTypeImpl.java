package com.example;

import com.example.types.Event;

import com.example.types.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

@WebService(name = "eventPortType", targetNamespace = "http://www.bea.com/aler/events/eventsListenerWsdl/", serviceName = "eventService", portName = "EventServiceBindingPort", wsdlLocation = "/WEB-INF/wsdl/eventService.wsdl")
@XmlSeeAlso( { ObjectFactory.class })
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EventPortTypeImpl {
    public EventPortTypeImpl() {
    }

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponse", output = "http://www.bea.com/aler/events/eventsListenerWsdl/eventPortType/newEventRequestResponseResponse")
    @WebMethod(action = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponse")
    @WebResult(name = "StringVal", targetNamespace = "http://www.bea.com/infra/events", partName = "status")
    public String newEventRequestResponse(@WebParam(name = "newEventRequestResponse", partName = "event", targetNamespace = "http://www.bea.com/infra/events")
        Event event) {
        if(event == null)
            System.out.println("OER_CUSTOM_EVENT_HANDLER ERROR event is null");
        else {
            System.out.println("OER_CUSTOM_EVENT_HANDLER received new event " + event.getEventData().getName());
        }

        return "Success";
    }

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponseString", output = "http://www.bea.com/aler/events/eventsListenerWsdl/eventPortType/newEventRequestResponseStringResponse")
    @WebMethod(action = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponseString")
    @WebResult(name = "StringVal", targetNamespace = "http://www.bea.com/infra/events", partName = "status")
    public String newEventRequestResponseString(@WebParam(name = "newEventRequestResponseString", partName = "event", targetNamespace = "http://www.bea.com/infra/events")
        String event) {
        return null;
    }
}
