package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.Event;
import java.util.Properties;

/*
 * An interface for OER Event Handlers.
 * Each concrete handler should handle a specific OER Event type.
 * 
 * @author Bob Webster
 *
 */
public interface EventHandler {
   
    /*
    * Performs processing for a specific type of OER Event 
    * @param event (required)  - An OER Event Object
    * @param props (required)  - Properties that support the implementation of this handler
    * @param conn  (required)  - An active OER connection object
    */ 
    public void  process(Event event, Properties props, ConnectionPool.OERConnection conn) ;
    
}
