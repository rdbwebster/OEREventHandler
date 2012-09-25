This Web App is an example of a Custom OER Event Listener Web Service
OER is configured to send events to this web service when certain actions are completed in OER, 
for example work performed through the web interface or by a OER Harvester,

This web service then calls back into the OER using the OER REX interface to configure Assets
etc based on the desired processing for the received event.

http://docs.oracle.com/cd/E23943_01/admin.1111/e16580/oerwf.htm#CJHJADDE

The main service implementation is contained in the EventPorTypeImpl.java file.

Edit the EventHandlerConfig.properties file and set the url to your OER instance
and the username and password for an OER user with Admin Permissions.
These credentials will be used by the Service to call back into OER to implement
the automation associated with a received event.


Build and Deploy
================

Build the OEREventService Project and deploy it to a WLS Server.
The target WLS server may be the same server hosting OER.
Locate the deployed Web Application in the WLS console and record the
service URL for later configuration in OER.
             

OER configuration Dependencies
==============================

Configure OER to the Enable OER Event Manager
---------------------------------------------
Login to OER as Admin

In OER Admin System Settings Tab
set the  'cmee.eventframework.enabled' property to True
Set the  'cmee.eventframework.delivery.sleep'  property value to 5 seconds
then press save button.



Configure OER to call this web Service when events are raised in OER
--------------------------------------------------------------------
Edit the EndPointEventSubscription.xml file in the deploy OER application (stage directory)
for example
/u02/app/oracle/middleware/user_projects/domains/soa_domain/servers/gov_server1/stage/oer_11/oer_11.1.1.5.0/oer-app/WEB-INF/classes/


Change the values for host, port and uri to point to the location of this deployed application
retrieved earlier from the WLS console.

 <sub:endPoint name="ALBPMEndpoint">

                        <!--The host of the Webservice Endpoint -->
                        <sub:host>localhost</sub:host>
                        
                        <!--The port of the Webservice Endpoint -->
                        <sub:port>7001</sub:port>
                        
                        <!--The URI of the Webservice Endpoint -->
                        <!--If you are using ALBPM5.7 uncomment the following line and comment the line after -->
                        <!-- <sub:uri>fuegoServices/ws/StatusChangeEndpointServiceListener</sub:uri> -->
                        <sub:uri>OEREvents-OEREventService-context-root/eventService</sub:uri>
                        


Restart the Weblogic Service hosting OER
Then monitor the cmee event Log File for event traffic, using tail -f or some other method

$wls_domain_home/cmee.log  


Basic Testing of Event Configuration through OER
================================================
Search for an Asset in OER, the open the selected Asset in the Asset editor
and press the Approve button on any tab.
This should cause OER to emit an Event.

A dispatched event should be visible in cmee.log file located in the WLS domain where OER is hosted.
In addition OER pass the event to the OEREVentService.

Here is an example of the Post sent to EventService by OER captured by TCPMon

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

Any event sent by OER to the EventService will be logged by the service to the WLS
Server log file of the server hosting the EventService,
if the debug level for the WLS Server is set to Debug or if the debug level for the
EventService is set to debug.

To change the Logging level for the Service, add the value   com.example=Debug
to the WLS Console 'Server->Logging->Logger Server Properties' field
and restart the Server.

