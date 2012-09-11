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
            
          //  JAXBElement<AssetRegistered> assetRegJ =  (JAXBElement<AssetRegistered>) event.getExtendedData();
         //   AssetRegistered assetRegistered = assetRegJ.getValue();
            JAXBElement<RelatedAssetRegister> assetReg =  (JAXBElement<RelatedAssetRegister>) event.getExtendedData();
            RelatedAssetRegister relatedAssetEvent = assetReg.getValue();
        
            // Is the Accepted Asset a Service Asset?
            
      //     if(relatedAssetEvent.getRelatedAsset().getTypeID() == 154)
        //    {
            
                try {
                      
                        FlashlineRegistry repoInstance = conn.getConnection();
                        AuthToken authToken = conn.getAuthToken();
                           
                        // Register the related Asset
                    
                        long id = relatedAssetEvent.getRelatedAsset().getId(); 
                        repoInstance.assetRegister(authToken, id);
                        logger.info("Auto Registered " + relatedAssetEvent.getRelatedAsset().getName()  +
                                    " related to newly registered asset " + relatedAssetEvent.getOriginalAsset().getName());
                     
                       // Register related Assets
                    
                        // Retrieve and walk its related Assets
                  //      RelationshipType[] allRelationshipTypes = asset.getRelationshipTypes();
                  //      for (int i = 0; i < allRelationshipTypes.length; i++) {
                  //          System.out.println("Found "+ allRelationshipTypes[i].getName() +" relationship");
                            
                  //          if (allRelationshipTypes[i].getName().equals("Contains Interface")) {
                  //              RelationshipType myRelationshipType = allRelationshipTypes[i];
                   //             long[] secondaryIDs = myRelationshipType.getSecondaryIDs();
                    //            Asset interfaceAsset = repoInstance.assetRead(authToken, secondaryIDs[0]);
                                // Update the Asset
                   //             repoInstance.assetUpdate(authToken, interfaceAsset);
                   //             logger.info("Auto Registered Asset " + interfaceAsset.getLongName());
                   //         }
                   //         else if (allRelationshipTypes[i].getName().equals("Deployed To")) {
                   //             RelationshipType myRelationshipType = allRelationshipTypes[i];
                   //             long[] secondaryIDs = myRelationshipType.getSecondaryIDs();
                    //            Asset portAsset = repoInstance.assetRead(authToken, secondaryIDs[0]);
                                // Update the Asset
                  //              repoInstance.assetUpdate(authToken, portAsset);
                  //              logger.info("Auto Registered Asset " + portAsset.getLongName());
        
                      //      }
                   //     }
                    
                       
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
          //  }
        }

}
