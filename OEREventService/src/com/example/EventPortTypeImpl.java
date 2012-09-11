package com.example;

import com.example.eventhandlers.EventHandler;
import com.example.types.Event;
import com.example.types.ObjectFactory;
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
    public static final String EVENT_PROPERTIES = "EventHandlerConfig.properties";
    private Properties properties;
    private ConnectionPool pool;
    private Log logger = LogFactory.getLog(EventPortTypeImpl.class);   // not static since contained in servlet
    
    @PostConstruct
    public void init() {
        
       
        try {
            
               ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
                properties = new Properties() ;
               // Place property file in WEB-INF/classes
                properties.load(classLoader.getResourceAsStream("/" + EVENT_PROPERTIES));
           
                String repoURL  = properties.getProperty("oer.uri");
                String username = properties.getProperty("oer.username"); 
                String password = properties.getProperty("oer.password");
                String numConnections = properties.getProperty("oer.numberOfConnections");
                
                pool = new ConnectionPool(Integer.parseInt(numConnections));
                pool.initialize(repoURL, username, password);
               
         }
     
      
        catch(java.io.IOException ex) {
            logger.error("Failed to read " + EVENT_PROPERTIES, ex);
         
        }
        
        catch(NumberFormatException nex) {
            logger.error("Invalid value for property oer.numberOfConnections, value should be an integer.");
           
        }
        
        catch (Exception lEx) {
            logger.error("ConnectionPool failed to initial connections.", lEx);
           
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
                 ConnectionPool.OERConnection conn = null;
                 try {
                            
                            conn = pool.getInstance();
                            logger.info("Retrieved connection from OER connection pool: " + 
                                        conn.getConnection().hashCode() + " : " +
                            conn.getAuthToken().getToken());
                            handler.process(event, properties, conn);
                            pool.release(conn);  
                 }
                 catch (Exception any)
                 {
                            logger.error("Handler returned an Exception, returning pooled connection ", any);
                            if(conn != null)
                                pool.release(conn);  
                 }
                
            } 
            catch (Exception e) { 
                logger.error("Could not load class configured Event Handler class named " + 
                                   String.valueOf(eventHandlerClass) + " set in property " + eventNameProperty);
                }
        }
        else 
                logger.warn("Ignoring Event " + event.getEventData().getName() + 
                                 " no definition found for event in the AutomationConfig.properties file." +
                            event.getEventData().toString());
    
        
        return "Success";
    }

    
}
