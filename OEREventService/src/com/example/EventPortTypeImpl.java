package com.example;

import com.example.types.Event;
import com.example.types.ObjectFactory;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;
import java.net.URL;
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

    private static AuthToken authenticationToken;
    private static FlashlineRegistry alerInstance = null;
    
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
        // "v2_1.G+NTr3az8thaGGJBn0vwPg=="
        test("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", 567);
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
    
    /**
            * Reads an existing asset from the OER repository.
            * @param repoURL a string representing the URL of the repository.  For example  http://localhost:7101/aler/services/FlashlineRegistry
            * @param username a string representing the repository administrator user name.  For example admin
            * @param password a string representing the repository administrator password.   For example admin
            * @param assetID a long representing the AssetID of the asset to be read.<br>
            * AssetID for a given asset is displayed in the OER asset editor at the bottom on the page. 
            */
           @WebMethod(exclude = true)
           public  String test(String repoURL, String username, String password, long assetID) 
               {
                       
                      OERFunctions funcs = new OERFunctions();
                      
                      // Get for NewAsset Event, but for now
                      
                      String recipients[] =  { "architect@soabpm-server" };
                      
                      funcs.notify(repoURL, username, password, recipients);
                       
                    
                       return "";
               }

       
       @WebMethod(exclude = true)
       private void connect(String repoURL, String userName, String password)  
                                                                                               
               {
                       
                       try{
                               if (alerInstance == null)
                               {
                                       URL alerRepURL  = new URL(repoURL);
                       
                                       alerInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                       
                                       authenticationToken = alerInstance.authTokenCreate(userName, password);
                                       System.out.println("Connected to Repository : " + repoURL);
                               }
                               else
                                       System.out.println("Connected to Repository : " + repoURL +       " using existing connection.");
                       }
                       catch(Exception ox)
                       {               
                            System.out.println("Exception connecting to Repository " + ox.getMessage());
 
                        }   
                       
                       return;
               }
}
