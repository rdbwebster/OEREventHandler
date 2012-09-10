package com.example.eventhandlers;

import com.example.types.Event;

import java.util.Properties;

public interface EventHandler {
    
    public void  process(Event event, Properties props) ;
    
}
