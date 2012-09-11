package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.Event;

import com.flashline.registry.openapi.entity.AuthToken;

import java.util.Properties;

public interface EventHandler {
    
    public void  process(Event event, Properties props, ConnectionPool.OERConnection conn) ;
    
}
