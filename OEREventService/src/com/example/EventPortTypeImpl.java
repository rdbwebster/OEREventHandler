package com.example;

import com.example.eventhandlers.EventHandler;
import com.example.types.Event;
import com.example.types.ObjectFactory;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

@WebService(name = "eventPortType", targetNamespace = "http://www.bea.com/aler/events/eventsListenerWsdl/", serviceName = "eventService", portName = "EventServiceBindingPort", wsdlLocation = "WEB-INF/wsdl/eventService.wsdl")
@XmlSeeAlso( { ObjectFactory.class })
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EventPortTypeImpl {

    public static final String EVENT_PROPERTY_PREFIX = "event";
    private Properties properties;
    private Log logger = LogFactory.getLog(EventPortTypeImpl.class);   // not static since contained in servlet
    
    @PostConstruct
    public void init() {
        
       
        try {
            
               ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
                properties = new Properties() ;
               // Place property file in WEB-INF/classes
                properties.load(classLoader.getResourceAsStream("/EventHandlerConfig.properties"));
            
         }
        catch(Exception ex)
        {
            logger.error("Failed to read AutomationConfig.properties");
            ex.printStackTrace(System.out);
        }
    }
        
    public EventPortTypeImpl() { 
        
          System.out.println("EventPortTypeImpl Using Logging " + LogFactory.getFactory().getClass().getName());
     
    }
    
    
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponse", output = "http://www.bea.com/aler/events/eventsListenerWsdl/eventPortType/newEventRequestResponseResponse")
    @WebMethod(action = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponse")
    @WebResult(name = "StringVal", targetNamespace = "http://www.bea.com/infra/events", partName = "status")
    public String newEventRequestResponse(@WebParam(name = "newEventRequestResponse", partName = "event", targetNamespace = "http://www.bea.com/infra/events")
        Event event) {
            
        if(event == null)
            logger.error("OER_CUSTOM_EVENT_HANDLER ERROR event is null");
        else {
            System.out.println("OER_CUSTOM_EVENT_HANDLER received new event " + event.getEventData().getName());
            logger.info("Received new event " + event.getEventData().getName());
               
        }
        
        // Get the event type and determine property name
        String eventNameProperty = EVENT_PROPERTY_PREFIX + "." + 
                event.getEventData().getName().substring(event.getEventData().getName().lastIndexOf(':') + 1);
        
        //
        // load the
        // configured handler for this event
        //
        
        String eventHandlerClass  = properties.getProperty(eventNameProperty);
      
        if(eventHandlerClass!= null) {
            
            try {
              Class c = Class.forName(eventHandlerClass);  // Dynamically load the class
              EventHandler  handler = (EventHandler) c.newInstance();            // Dynamically instantiate it
                
              // Invoke the handler to process the event
              handler.process(event, properties);
            } 
            
            catch (Exception e) { 
                logger.error("Could not locate the configured Event Handler class named " + 
                                   String.valueOf(eventHandlerClass) + " set in property " + eventNameProperty);
                }
        }
        else 
                logger.warn("Ignoring Event " + event.getEventData().getName() + 
                                 " no definition found for event in the AutomationConfig.properties file.");
    
        
        return "Success";
    }


    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponseString", output = "http://www.bea.com/aler/events/eventsListenerWsdl/eventPortType/newEventRequestResponseStringResponse")
    @WebMethod(action = "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponseString")
    @WebResult(name = "StringVal", targetNamespace = "http://www.bea.com/infra/events", partName = "status")
    public String newEventRequestResponseString(@WebParam(name = "newEventRequestResponseString", partName = "event", targetNamespace = "http://www.bea.com/infra/events")
        String event) {
        
        // Not implemented
        return null;
    }
    
}
