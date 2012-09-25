package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetSubmission;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * A Handler for the OER AssetSubmission event.
 * This handler is called after an Asset Has been submitted to OER.
 * The Handler accepts the submitted Asset using the OER user name
 * specified by the event.AssetSubmission.acceptor property.
 * The Handler instantiated and called by Custom OER Event Service
 * 
 * @author Bob Webster
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
    /*
    * Performs processing for the OER AssetSubmission Event 
    * Auto Accept new Assets Submitted to the Repository
    * Auto Assign the Asset to the user defined by the event.AssetSubmission.acceptor property
    * @param event (required)  - An OER Event Object
    * @param props (required)  - Properties that support the implementation of this handler
    * @param conn  (required)  - An active OER connection object
    */ 
    public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
        
        try {
             
                                                      
                // Get The Extended Data of known subtype
            
                JAXBElement<AssetSubmission> assetSubJ =  (JAXBElement<AssetSubmission>) event.getExtendedData();
                AssetSubmission assetSub = assetSubJ.getValue();
            
                // Retrieve the submitted Asset
                
                Asset asset = conn.getConnection().assetRead(conn.getAuthToken(), assetSub.getAsset().getId());
                Calendar cal = Calendar.getInstance();
                
                // Accept the Asset
            
                String acceptor = props.getProperty(ASSET_ACCEPTOR);  
                conn.getConnection().assetAccept(conn.getAuthToken(), asset.getID());
                asset.setAcceptedByName(acceptor);
                asset.setAcceptedDate(cal);
                logger.info("Asset " + asset.getDisplayName()  + " Accepted by " + acceptor );
            
                // Update the Asset
                conn.getConnection().assetUpdate(conn.getAuthToken(), asset);
        
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
