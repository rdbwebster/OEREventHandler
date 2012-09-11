This Web App is an example of a Custom OER Event Listener Web Service
OER is configured to send events to this web service when certain actions are completed in the OER web interface.
This web service then calls back into the OER using its REX interface to configure Assets etc based on the desired
processing for the received event.

http://docs.oracle.com/cd/E23943_01/admin.1111/e16580/oerwf.htm#CJHJADDE

Contrary to docs I found EventListener.WSDL in
/u02/app/oracle/middleware/user_projects/domains/soa_domain/servers/gov_server1/stage/oer_11/oer_11.1.1.5.0/oer-app/WEB-INF/lib
/modules.eventing-11.1.1.5.0.jar

The WSDL included with OER for custom web services has a number of issues.
** It is abstract,
** Edit the WSDL and add service Tag to WSDL before definitions close tag

            <wsdl:service name="eventService">
	        <wsdl:port name="EventServiceBindingPort" binding="tns:EventServiceBinding">
	            <soap:address location="http://localhost:7001/OEREventHandler-EventHandler-context-root/EventServiceBindingPort"/>
	        </wsdl:port>
	    </wsdl:service>

Other issues
** If a method is Document Literal its message must reference an element not a type.
   Many violations

** If a method is Document Literal there can be only one part
   May violations for some methods.
   

Modify the wsdl so it contains only the 'newEventRequestResponse' operation.
See the eventService.wsdl file








Create New App In JDeveloper
============================

Application->New Generic Application   'OEREvents'
With New Generic Project               'OEREventService'

Right click on EventHandler project and choose 
Business Tier-> Web Services-> Java Web Service From WSDL

In Wizard
Choose Java EE 1.5 with support for JAX-WS RI
Browse and select modified WSDL   file: .../eventService.wsdl
Leave Java selected
Leave Add Service Endpoint Interface UNselected
Leave Copy WSDL Locally selected

Package Name:  		com.example
Root Package for types: com.example.types

Press Next and Finish
(Code Generation may take up to 1 minute)


Edit EventPortTypeImpl.java 
 - add code to implement a strategy pattern.
   The event received from OER is looked up in EventHandlerConfig.properties file
   The configured Java handler class for the event is loaded dynamically and invoked.
 - add logging

Add a com.example.eventhandlers folder
Create a concrete handler class that implements the EventHandler interface for each type 
of event the web service will process.
The classes in this folder will be loaded and invoked by the EventPortTypeImpl class to handle an event.

Add EventHandlerConfig.properties file
Add commons-logging.properties file


            
    
Then Build in JDeveloper, and right click on Project and Deploy to Admin Server.
        
Deploy Successful
        The following URL context root(s) were defined and can be used as a starting point to test your application:
        
        http://10.0.2.15:7001//EventHandler-EventHandler-context-root/eventService
        
        From wls console see
      http://10.0.2.15:7001/OEREvents-OEREventService-context-root/eventService
         and
        http://10.0.2.15:7001/OEREvents-OEREventService-context-root/eventService?WSDL   
 
 Test with WLS Console
 
        Note: at this point can run test with WLS Console, Select EventService Web App, EventService, TestClient
              fill in html form, remove 
                 <!--Optional:-->
                 <ExtendedData />
        Then press NewEventRequestResponse Button.
        
        should see
        <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
           <S:Body>
                   <xs:status xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:ns2="http://www.bea.com/infra/events" xmlns="http://www.bea.com/aler/events">Success</xs:status>
           </S:Body>
         </S:Envelope>
       

Configure OER to the Enable OER Event Manager
========================================
Login to OER as Admin

in OER Admin System Settings Tab

set cmee.eventframework.enabled to True
then press save button.



Configure OER to call this web Service when events are raised in OER
====================================================================

Edit the EndPointEventSubscription.xml file in the deploy OER application (stage directory)
for example
/u02/app/oracle/middleware/user_projects/domains/soa_domain/servers/gov_server1/stage/oer_11/oer_11.1.1.5.0/oer-app/WEB-INF/classes/


Change host, port and uri
 <sub:endPoint name="ALBPMEndpoint">

                        <!--The host of the Webservice Endpoint -->
                        <sub:host>localhost</sub:host>
                        
                        <!--The port of the Webservice Endpoint -->
                        <sub:port>4001</sub:port>
                        
                        <!--The URI of the Webservice Endpoint -->
                        <!--If you are using ALBPM5.7 uncomment the following line and comment the line after -->
                        <!-- <sub:uri>fuegoServices/ws/StatusChangeEndpointServiceListener</sub:uri> -->
                        <sub:uri>OEREvents-OEREventService-context-root/eventService</sub:uri>
                        
                        <!--Unless a custom WSDL Contract is used, the namepsace should not be changed -->
                        <sub:targetNamespace>http://www.bea.com/infra/events</sub:targetNamespace>



Restart the Weblogic Service hosting OER

then monitor Event Log File for event traffic, using tail -f or some other method

	$wls_domain_home/cmee.log  


Test through OER
================
Search for Asset in OER then open in Asset Editor and approve some tab.

A dispatched event should be visible in cmee.log

The EventService should log the received event to the Server.log file of the WLS
server hosting the web service.

(The Service uses Apache commons logging, directed to the WLS Log implementation).
(Check the WLS console to ensure the log level for the server is set to INFO or DEBUG)


####<Aug 28, 2012 12:26:18 PM PDT> <Notice> <Stdout> <soabpm-server> <AdminServer> 
<[ACTIVE] ExecuteThread: '12' for queue: 'weblogic.kernel.Default (self-tuning)'> 
<<WLS Kernel>> <> <11d1def534ea1be0:-1427c39b:1396e5673cb:-8000-0000000000000218> <1346181978335> <BEA-000000> 
<OER_CUSTOM_EVENT_HANDLER received new event urn:com:bea:aler:events:type:AssetTabApproved>




Here is an example of the Post sent to EventService by OER

POST /EventHandler-EventHandler-context-root/eventService HTTP/1.0
Content-Type: text/xml; charset=utf-8
Accept: application/soap+xml, application/dime, multipart/related, text/*
User-Agent: Axis/1.2.1
Host: 127.0.0.1:4001
Cache-Control: no-cache
Pragma: no-cache
SOAPAction: "http://www.bea.com/aler/events/eventsListenerWsdl/newEventRequestResponse"
Content-Length: 2047

<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
<soapenv:Body>
<newEventRequestResponse xmlns="http://www.bea.com/infra/events">
<eventData>
    <name>urn:com:bea:aler:events:type:AssetTabApproved</name>
    <eventId>c3a6de04-c13e-40f5-9eb8-49a124105a9b</eventId>
    <description>new aler event</description>
    <version>ver1.0</version>
  </eventData><eventSource>
    <location>ALERCore</location>
    <componentName>Aqualogic ALER</componentName>
    <componentType>ALER3.0</componentType>
  </eventSource><ExtendedData xsi:type="even:AssetTabApproved" xmlns:even="http://www.bea.com/aler/events" xmlns="http://www.bea.com/infra/events">
    <even:asset>
      <even:id>567</even:id>
      <even:name>Sample Comm Adapter - Customer Credit Information</even:name>
      <even:version>3.0</even:version>
      <even:description>&lt;B&gt;THIS ASSET IS TO BE USED AS AN EXAMPLE. IT HAS BEEN POPULATED WITH SAMPLE METADATA. ALL FILES AND METADATA ARE TO BE USED FOR TRAINING PURPOSES ONLY.&lt;/B&gt; &lt;P&gt; This adapter retrieves customer credit information and is designed to provide simple, standard access to business objects such as RFC (Remote Function Call) modules, BAPIs (Business Application Programming Interfaces), and IDocs (Intermediate Documents), which are used to support existing business processes. These business objects are available to the adapter as requests of our organization's CRM system.</even:description>
      <even:typeID>151</even:typeID>
      <even:activeStatus>ACTIVE</even:activeStatus>
      <even:community/>
    </even:asset>
    <even:registrarEmail/>
    <even:updatedBy>
      <even:id>99</even:id>
      <even:name>admin</even:name>
      <even:emailAddress>admin@example.com</even:emailAddress>
    </even:updatedBy>
    <even:tabName>Technical</even:tabName>
  </ExtendedData></newEventRequestResponse>
</soapenv:Body>
</soapenv:Envelope>
