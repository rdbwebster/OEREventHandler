package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.Event;
import com.example.types.RelatedAssetRegister;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RelatedAssetRegisterHandler implements EventHandler {
        
    private Log logger = LogFactory.getLog(RelatedAssetRegisterHandler.class.getName()); // not static since contained in servlet
    
        public RelatedAssetRegisterHandler() {
            super();
            
        }

        /*
        // If the registered Asset is of type Service
        // Auto Register the associated Interface and Endpoint Assets
        //
        */
        
        public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
              
            // Get The Extended Data from the Event
            
            JAXBElement<RelatedAssetRegister> assetReg =  (JAXBElement<RelatedAssetRegister>) event.getExtendedData();
            RelatedAssetRegister relatedAssetEvent = assetReg.getValue();
        
            // Is the Registered Asset a Service Asset?
            
            if(relatedAssetEvent.getOriginalAsset().getTypeID() == 154)
            {
            
                try {
                      
                        
                        long id = relatedAssetEvent.getRelatedAsset().getId(); 
                    
                        // Register the related Asset
                    
                        conn.getConnection().assetRegister(conn.getAuthToken(), id);
                        logger.info("Auto Registered " + relatedAssetEvent.getRelatedAsset().getName()  +
                                    " related to newly registered asset " + relatedAssetEvent.getOriginalAsset().getName());
                     
                  
                       
                        // Update the Asset
                        //repoInstance.assetUpdate(authToken, asset);
                 
                }
                catch(OpenAPIException lEx) {
                        logger.error("ServerCode = "+ lEx.getServerErrorCode());
                        logger.error("Message = "+ lEx.getMessage());
                        logger.error("StackTrace:", lEx);
                        }
                
                catch (RemoteException rEx) {
                        logger.error("Caught Remote Exception  ", rEx);
                } 
                
                catch(Exception ex) {               
                        logger.error("Caught Exception: ", ex);  
                }
            }
        }

}
