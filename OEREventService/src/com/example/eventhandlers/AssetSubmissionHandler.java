package com.example.eventhandlers;

import com.example.types.AssetSubmission;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetSubmissionHandler implements EventHandler {
    
    private Log logger = LogFactory.getLog(AssetSubmissionHandler.class.getName()); // not static since contained in servlet
    
    public AssetSubmissionHandler() {
        super();
        
    }

    //
    // Auto Accept new Assets Submitted to the Repository
    // Auto Assign them to an Architect
    //
    public void process(Event event, Properties props) {
        
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
      
                // Get The Extended Data of known subtype
            
                JAXBElement<AssetSubmission> assetSubJ =  (JAXBElement<AssetSubmission>) event.getExtendedData();
                AssetSubmission assetSub = assetSubJ.getValue();
            
                // Retrieve the submitted Asset
                
                Asset asset = repoInstance.assetRead(authToken, assetSub.getAsset().getId());
            
                // Accept the Asset
            
                String acceptor = props.getProperty("event.AssetSubmission.acceptor");  
                asset.setAcceptedByName(acceptor);
                asset.setAcceptedDate(Calendar.getInstance());
            
                // Assign the Asset
                String assignee = props.getProperty("event.AssetSubmission.assignee");  
                asset.setAcceptedByName(assignee);
                asset.setAcceptedDate(Calendar.getInstance());
            
                repoInstance.assetUpdate(authToken, asset);
           
                
                logger.info("Connected to OER, read and accepted new Asset " +  asset.getDisplayName() +
                                    " using user " + assignee);
            
                repoInstance.authTokenDelete(authToken);
            
        
        }
        catch(OpenAPIException lEx) {
                System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                System.out.println("Message = "+ lEx.getMessage());
                System.out.println("StackTrace:");
                lEx.printStackTrace(System.out);
                }
        
        catch (RemoteException lEx) {
            System.out.println("OER Functions: Caught Remote Exception  " + lEx.getMessage());
            lEx.printStackTrace(System.out);
        } 
        
        catch(Exception ex)
        {               
            System.out.println("OER Functions caught Exception: " + ex.getMessage());  
            ex.printStackTrace(System.out);
        }
    }
      
    
}
