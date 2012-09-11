package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetAccepted;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetAcceptedHandler implements EventHandler {
        
      private Log logger = LogFactory.getLog(AssetAcceptedHandler.class.getName()); // not static since contained in servlet
    
        public AssetAcceptedHandler() {
            super();
          
        }

        /*
        //  If the asset accepted is a Service, set the Version Attribute  based on the suffix of the Service Namespace
        //
        */
        
        public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
            
            
            // Get The Extended Data from the Event
            
            JAXBElement<AssetAccepted> assetAccJ =  (JAXBElement<AssetAccepted>) event.getExtendedData();
            AssetAccepted assetAccepted = assetAccJ.getValue();
        
            // Is the Accepted Asset a Service Asset?
            
            // If Asset is a Service,s Set the Asset Version Attribute
            if(assetAccepted.getAsset().getTypeID() == 154)
            {
            
                try {
                        String repoURL  = props.getProperty("oer.uri");
                        String username = props.getProperty("oer.username"); 
                        String password = props.getProperty("oer.password");
                    
                        // Connect to repository
                        // Servlet Based Web Service, method must be thread safe 
                        URL alerRepURL  = new URL(repoURL);
                  //      FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                  //      AuthToken authToken = repoInstance.authTokenCreate(username, password);
                        FlashlineRegistry repoInstance = conn.getConnection();
                        AuthToken authToken = conn.getAuthToken();
                                                            
                        logger.info("Connected to Repository : " + repoURL);       
              
                         
                       // Retrieve the submitted Asset
                    
                        Asset asset = repoInstance.assetRead(authToken, assetAccepted.getAsset().getId());
                    
                        // get the namespace
                        int offset1 = asset.getLongName().lastIndexOf('}');
                        if(offset1 == -1)
                        {
                            logger.error("Unable to determine namespace for Asset " + asset + " namespace. " +
                                         "Version Metadata will not be set");
                        }
                        else{
                            String namespace = asset.getLongName().substring(1, offset1);
                            logger.info("Asset Name contains Namespace " + namespace);
                            int offset2 = asset.getLongName().lastIndexOf('/');
                            if(offset2 == -1)
                            {
                                logger.error("Unable to determine Version id from Asset " + asset + " namespace. " +
                                             "Version Metadata will not be set");
                            }
                            else {
                                String versionid = namespace.substring(offset2+ 2);    // skip v prefix eg v1
                                logger.info("Asset Name contains versionid " + versionid);
                                // set the namespace and update the asset
                                asset.setVersion(versionid);
                                repoInstance.assetUpdate(authToken, asset);
                                logger.info("Connected to OER, set Version on Accepted Service Asset " +  asset.getDisplayName());
                            }
                        } 
                         
                        repoInstance.authTokenDelete(authToken);
                    }
                    
                
                
                catch(OpenAPIException lEx) {
                        logger.error("ServerCode = "+ lEx.getServerErrorCode());
                        logger.error("Message = "+ lEx.getMessage());
                        logger.error("StackTrace:", lEx);
                       
                        }
                
                catch (RemoteException rEx) {
                    logger.error("OER Functions: Caught Remote Exception", rEx);

                } 
                
                catch(Exception ex)
                {               
                    logger.error("OER Functions caught Exception: ", ex);  
                }
        }
       }

}