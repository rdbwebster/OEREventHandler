package com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {
   
   
    private Log logger = null;  // not static since contained in servlet
    
    public SOAPLoggingHandler() {
   
      logger = LogFactory.getLog(ConnectionPool.class);
    
    }

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        logToSystemOut(smc);
        logger.debug("Handle MESSAGE");
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        logger.debug(smc);
        return true;
    }

    // nothing to clean up
    public void close(MessageContext messageContext) {
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context
     * to see if this is an outgoing or incoming message.
     * Write a brief message to the print stream and
     * output the message. The writeTo() method can throw
     * SOAPException or IOException
     */
    private void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean)
            smc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            logger.debug("\nOutbound message:");
        } else {
            logger.debug("\nInbound message:");
        }

        SOAPMessage message = smc.getMessage();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
          
            message.writeTo(ps);
         
            logger.debug(baos.toString("ISO-8859-1")); // e.g. ISO-8859-1
            logger.debug("");     // just to add a newline
       
            ps.close();
            baos.close();
        } catch (Exception e) {
            logger.error("SOAPLoggingHandler: Exception in handler: " + e);
        }
    }
}

