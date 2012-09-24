package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetSubmission;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.entity.RegistryUser;
import com.flashline.registry.openapi.query.UserCriteria;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * This Handler responds to the AssetSubmission OER event.
 * The Hanlder accepts the submitted Asset using the OER user name
 * specified by the event.AssetSubmission.acceptor property.
 * 
 * 
 */
public class AssetSubmissionHandler implements EventHandler {
    
    private Log logger = LogFactory.getLog(AssetSubmissionHandler.class.getName()); // not static since contained in servlet
    public static final String ASSET_ACCEPTOR = "event.AssetSubmission.acceptor";
    
    public AssetSubmissionHandler() {
        super();
        
    }

    //
    // Auto Accept new Assets Submitted to the Repository
    // Auto Assign them to the configured user
    //
    public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
        
        try {
             
                FlashlineRegistry repoInstance = conn.getConnection();
                AuthToken authToken = conn.getAuthToken();
                                                      
                // Get The Extended Data of known subtype
            
                JAXBElement<AssetSubmission> assetSubJ =  (JAXBElement<AssetSubmission>) event.getExtendedData();
                AssetSubmission assetSub = assetSubJ.getValue();
            
                // Retrieve the submitted Asset
                
                Asset asset = repoInstance.assetRead(authToken, assetSub.getAsset().getId());
                Calendar cal = Calendar.getInstance();
                
                // Accept the Asset
            
                String acceptor = props.getProperty(ASSET_ACCEPTOR);  
                repoInstance.assetAccept(authToken, asset.getID());
                asset.setAcceptedByName(acceptor);
                asset.setAcceptedDate(cal);
                logger.info("Asset " + asset.getDisplayName()  + " Accepted by " + acceptor );
            
                // Update the Asset
                repoInstance.assetUpdate(authToken, asset);
        
        }
        catch(OpenAPIException aEx) {
                logger.error("ServerCode = "+ aEx.getServerErrorCode());
                logger.error("Message = "+ aEx.getMessage());
                logger.error("StackTrace:", aEx);
              
                }
        
        catch (RemoteException lEx) {
            logger.error("Remote Exception  ",  lEx);
          
        } 
        
        catch(Exception ex)
        {               
            logger.error("Exception: " , ex);  
          
        }
    }
      
    
}
