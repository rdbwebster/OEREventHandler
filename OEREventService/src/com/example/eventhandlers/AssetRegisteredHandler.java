package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetRegistered;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.entity.RelationshipType;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetRegisteredHandler implements EventHandler {
        
    private Log logger = LogFactory.getLog(AssetRegisteredHandler.class.getName()); // not static since contained in servlet
    
        public AssetRegisteredHandler() {
            super();
            
        }

        /*
        // If the registered Asset is of type Service
        // Auto Register the associated Interface and Endpoint Assets
        //
        */
        
        public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
            
            
            // Get The Extended Data from the Event
            
            JAXBElement<AssetRegistered> assetRegJ =  (JAXBElement<AssetRegistered>) event.getExtendedData();
            AssetRegistered assetRegistered = assetRegJ.getValue();
        
            // Is the Accepted Asset a Service Asset?
            
            if(assetRegistered.getAsset().getTypeID() == 154)
            {
            
                try {
                        String repoURL  = props.getProperty("oer.uri");
                        String username = props.getProperty("oer.username"); 
                        String password = props.getProperty("oer.password");
                    
                        // Connect to repository
                        // Servlet Based Web Service, method must be thread safe 
                        URL alerRepURL  = new URL(repoURL);
                        FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                        AuthToken authToken = repoInstance.authTokenCreate(username, password);
                                                            
                        System.out.println("Connected to Repository : " + repoURL);       
              
                         
                       // Retrieve the submitted Asset
                    
                        Asset asset = repoInstance.assetRead(authToken, assetRegistered.getAsset().getId());
                    
                        // Retrieve and walk its related Assets
                        RelationshipType[] allRelationshipTypes = asset.getRelationshipTypes();
                        for (int i = 0; i < allRelationshipTypes.length; i++) {
                            System.out.println("Found "+ allRelationshipTypes[i].getName() +" relationship");
                            
                            if (allRelationshipTypes[i].getName().equals("Contains Interface")) {
                                RelationshipType myRelationshipType = allRelationshipTypes[i];
                                long[] secondaryIDs = myRelationshipType.getSecondaryIDs();
                                Asset interfaceAsset = repoInstance.assetRead(authToken, assetRegistered.getAsset().getId());
                               
                            }
                            else if (allRelationshipTypes[i].getName().equals("Deployed To")) {
                                RelationshipType myRelationshipType = allRelationshipTypes[i];
                            }
                        }

                    
                        logger.info("Connected to OER, and auto registered assets related to newly registered service " +  asset.getDisplayName());
                    
                        repoInstance.authTokenDelete(authToken);
                    
                
                }
                catch(OpenAPIException lEx) {
                        logger.error("ServerCode = "+ lEx.getServerErrorCode());
                        logger.error("Message = "+ lEx.getMessage());
                        logger.error("StackTrace:", lEx);
                      
                      
                        }
                
                catch (RemoteException rEx) {
                    logger.error("OER Functions: Caught Remote Exception  ", rEx);
                  
                } 
                
                catch(Exception ex)
                {               
                    logger.error("OER Functions caught Exception: ", ex);  
                   
                }
            }
        }

}
