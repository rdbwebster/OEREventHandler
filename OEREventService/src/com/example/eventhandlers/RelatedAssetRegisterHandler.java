package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.Event;
import com.example.types.RelatedAssetRegister;
import com.flashline.registry.openapi.base.OpenAPIException;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * A Handler for the OER RelatedAssetRegister Event
 * The Handler registers the related assets of a Service Asset.
 * The Handler instantiated and called by Custom OER Event Service
 * 
 * @author Bob Webster
 */
public class RelatedAssetRegisterHandler implements EventHandler {
        
    private Log logger = LogFactory.getLog(RelatedAssetRegisterHandler.class.getName()); // not static since contained in servlet
    public static final long SERVICE_TYPE = 154;
    
    public RelatedAssetRegisterHandler() {
        super();
        
    }

    /*
    * Performs processing for the OER AssetRegister Event 
    * If the registered Asset is of type Service, Register the associated Asset
    * @param event (required)  - An OER Event Object
    * @param props (required)  - Properties that support the implementation of this handler
    * @param conn  (required)  - An active OER connection object
    */ 
    public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
              
        // Get The Extended Data from the Event
        
        JAXBElement<RelatedAssetRegister> assetReg =  (JAXBElement<RelatedAssetRegister>) event.getExtendedData();
        RelatedAssetRegister relatedAssetEvent = assetReg.getValue();
    
        // Is the Registered Asset a Service Asset?
        
        if(relatedAssetEvent.getOriginalAsset().getTypeID() == SERVICE_TYPE)
        {
            try {
                    long id = relatedAssetEvent.getRelatedAsset().getId(); 
                
                    // Register the related Asset
                
                    conn.getConnection().assetRegister(conn.getAuthToken(), id);
                    logger.info("Auto Registered " + relatedAssetEvent.getRelatedAsset().getName()  +
                                " related to newly registered asset " + relatedAssetEvent.getOriginalAsset().getName());
                               
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
